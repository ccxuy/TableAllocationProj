package com.tap.ui;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.hexapixel.widgets.generic.Utils;
import com.tap.bizlogic.OrderLogic;
import com.tap.tableordersys.BookOrder;
import com.tap.tableordersys.Guests;
import com.tap.tableordersys.Order;
import com.tap.tableordersys.Table;
import com.tap.tableordersys.WaitingList;

public class OrderModifyBox {
	protected Object result;
	private Text textOrderID;
	private Text textOperatorID;
	private Text textTableID;
	private Text textGusetsID;
	private Text textGusetsAmount;
	private Text textBookTime;
	
	private Order order;
	private OrderLogic ol;
	boolean modifyID=false;

	protected Shell shell;

	public OrderModifyBox() {}
	public OrderModifyBox(Order o,OrderLogic ol) {
		this.order = o;
		this.ol = ol;
	}
	public OrderModifyBox(boolean modifyID) {
		this.modifyID = modifyID;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			OrderModifyBox window = new OrderModifyBox();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(300, 325);
		shell.setText("Modify order");
		Utils.centerDialogOnScreen(shell);
		
		if(null==order)return;
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(33, 39, 61, 17);
		lblNewLabel.setText("ID:");
		
		if(null==order.getOrderID())return;
		textOrderID = new Text(shell, SWT.BORDER);
		textOrderID.setBounds(141, 36, 73, 23);
		textOrderID.setText(order.getOrderID());
		textOrderID.setEnabled(modifyID);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(33, 72, 61, 17);
		lblNewLabel_1.setText("Operator:");
		
		textOperatorID = new Text(shell, SWT.BORDER);
		textOperatorID.setBounds(141, 72, 73, 23);
		if(null!=order.getOperatorID())
			textOperatorID.setText(order.getOperatorID());
		textOperatorID.setEnabled(false);
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(33, 107, 61, 17);
		lblNewLabel_2.setText("Table ID:");
		
		textTableID = new Text(shell, SWT.BORDER);
		textTableID.setBounds(141, 101, 73, 23);
		if(null!=order.getTable())
			textTableID.setText(order.getTable().getId());

		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(33, 139, 61, 17);
		lblNewLabel_3.setText("Guest ID:");
		
		textGusetsID = new Text(shell, SWT.BORDER);
		textGusetsID.setBounds(141, 133, 73, 23);
		textGusetsID.setEnabled(false);
		if(null!=order.getGusets())
			textGusetsID.setText(order.getGusets().getId());

		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setBounds(33, 178, 73, 17);
		lblNewLabel_4.setText("Guest amout:");
		
		textGusetsAmount = new Text(shell, SWT.BORDER);
		textGusetsAmount.setBounds(141, 172, 73, 23);
		if(null!=order.getGusets())
			textGusetsAmount.setText(order.getGusets().getAmountString());
		
		if(order instanceof BookOrder){
			Label lblBookTime = new Label(shell, SWT.NONE);
			lblBookTime.setBounds(33, 215, 73, 17);
			lblBookTime.setText("Book time:");
			
			textBookTime = new Text(shell, SWT.BORDER);
			textBookTime.setBounds(141, 213, 73, 23);
			textBookTime.setText( ((BookOrder)order).getBookTime().toString() );
		}

		Button btnSave = new Button(shell, SWT.NONE);
		btnSave.setBounds(33, 232, 80, 27);
		btnSave.setText("Save");
		btnSave.addSelectionListener(new btSaveListener());
		
		Button btnDelete = new Button(shell, SWT.NONE);
		btnDelete.setBounds(141, 232, 80, 27);
		btnDelete.setText("Check out");
		btnDelete.addSelectionListener(new btCheckOutListener());

	}

	class btSaveListener implements SelectionListener{
		@Override
		public void widgetSelected(SelectionEvent e) {
			Table modifyTable = ol.getRestaurant().findTableByID(textTableID.getText());
			if(modifyTable==null){
				MessageBox msgBox = new MessageBox(shell, 1);
				msgBox.setMessage("No such Table!");
				msgBox.open();
				return;
			}
			Guests g = order.getGusets();
			boolean change = g.setAmountString( textGusetsAmount.getText() );
			if(!change){
				MessageBox msgBox = new MessageBox(shell, 1);
				msgBox.setMessage("Wrong amount input!");
				msgBox.open();
				return;
			}

			order.getTable().deleteOrder(order);
			order.setTable(modifyTable);
			modifyTable.addOrder(order);
			
			ol.saveResturant();
			shell.close();
		}
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {}
	}
	class btCheckOutListener implements SelectionListener{
		@Override
		public void widgetSelected(SelectionEvent e) {
			Order o = ol.getRestaurant().findOrderByID(textOrderID.getText());
			boolean change = false;
			if(null!=o){
				/*if(o instanceof BookOrder){
					BookOrder cOrder = (BookOrder) o;
					change = o.getTable().deleteBookOrder(cOrder);
				}else{*/
					change = o.getTable().deleteOrder(o);
					WaitingList waitlist = ol.getWaitList();
					List<Guests> guestWaitlist = waitlist.getGuestList();
					Guests handleGuests = null;
					for(Guests g:guestWaitlist){
						List<Order> resOrder = ol.newCustomerFromWaiting(g);
						if(null!=resOrder){
							MessageBox msgBox = new MessageBox(shell, 2);
							StringBuffer msgContent = new StringBuffer();
							if(resOrder.size()>0){
								for(Order or:resOrder){
									msgContent.append(" "+or.getTable().getId());
								}
								handleGuests = g;
								msgBox.setMessage("Customer occupied table of "+msgContent);
								msgBox.open();
								break;
							}
						}
					}
					guestWaitlist.remove(handleGuests);
					ol.saveWaitingList();
				//}
			}
			ol.saveResturant();
			shell.close();
		}
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {}
	}
}

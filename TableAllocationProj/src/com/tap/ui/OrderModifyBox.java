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
	
	private MainUI mainUI;
	private Order order;
	private OrderLogic orderLogic;
	boolean modifyID=false;

	protected Shell shell;

	public OrderModifyBox() {}
	public OrderModifyBox(Order o,MainUI mainUI, OrderLogic ol) {
		this.order = o;
		this.mainUI = mainUI;
		this.orderLogic = ol;
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
		lblNewLabel.setBounds(33, 23, 61, 17);
		lblNewLabel.setText("ID:");
		
		if(null==order.getOrderID())return;
		textOrderID = new Text(shell, SWT.BORDER);
		textOrderID.setBounds(141, 20, 73, 23);
		textOrderID.setText(order.getOrderID());
		textOrderID.setEnabled(modifyID);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(33, 58, 61, 17);
		lblNewLabel_1.setText("Operator:");
		
		textOperatorID = new Text(shell, SWT.BORDER);
		textOperatorID.setBounds(141, 55, 73, 23);
		if(null!=order.getOperatorID())
			textOperatorID.setText(order.getOperatorID());
		textOperatorID.setEnabled(false);
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(33, 94, 61, 17);
		lblNewLabel_2.setText("Table ID:");
		
		textTableID = new Text(shell, SWT.BORDER);
		textTableID.setBounds(141, 91, 73, 23);
		if(null!=order.getTable())
			textTableID.setText(order.getTable().getId());

		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(33, 123, 61, 17);
		lblNewLabel_3.setText("Guest ID:");
		
		textGusetsID = new Text(shell, SWT.BORDER);
		textGusetsID.setBounds(141, 120, 73, 23);
		textGusetsID.setEnabled(false);
		if(null!=order.getGusets())
			textGusetsID.setText(order.getGusets().getId());

		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setBounds(33, 152, 73, 17);
		lblNewLabel_4.setText("Guest amout:");
		
		textGusetsAmount = new Text(shell, SWT.BORDER);
		textGusetsAmount.setBounds(141, 149, 73, 23);
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
		btnSave.setBounds(46, 250, 80, 27);
		btnSave.setText("Save");
		btnSave.addSelectionListener(new btSaveListener());
		
		Button btnDelete = new Button(shell, SWT.NONE);
		btnDelete.setBounds(154, 250, 80, 27);
		btnDelete.setText("Check out");
		btnDelete.addSelectionListener(new btCheckOutListener());
		
		Label lblAttentionModificationHere = new Label(shell, SWT.NONE);
		lblAttentionModificationHere.setBounds(20, 195, 254, 17);
		lblAttentionModificationHere.setText("Attention: Modification here is not restrict.");

	}

	class btSaveListener implements SelectionListener{
		@Override
		public void widgetSelected(SelectionEvent e) {
			Table modifyTable = orderLogic.getRestaurant().findTableByID(textTableID.getText());
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


			orderLogic.addLog("order modified "+order);
			order.getTable().deleteOrder(order);
			order.setTable(modifyTable);
			modifyTable.addOrder(order);
			
			orderLogic.saveResturant();
			shell.close();
			mainUI.doRefreshCurrentTableView();
		}
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {}
	}
	class btCheckOutListener implements SelectionListener{
		@Override
		public void widgetSelected(SelectionEvent e) {
			Order o = orderLogic.getRestaurant().findOrderByID(textOrderID.getText());

			int msgBoxResult=0;
			if(null!=o){
				/*if(o instanceof BookOrder){
					BookOrder cOrder = (BookOrder) o;
					change = o.getTable().deleteBookOrder(cOrder);
				}else{*/
					o.getTable().deleteOrder(o);
					orderLogic.addLog("Checkout Order "+o);
					orderLogic.saveResturant();
					
					//Assign table for customer in waiting
					WaitingList waitlist = orderLogic.getWaitList();
					List<Guests> guestWaitlist = waitlist.getGuestList();
					Guests handleGuests = null;
					boolean isAddedWaitCustomerToOrder = false;
					for(Guests g:guestWaitlist){
						List<Order> resOrder = orderLogic.newCustomerFromWaiting(g);
						if(null!=resOrder){
							MessageBox msgBox = new MessageBox(shell, SWT.ICON_QUESTION | SWT.OK);
							StringBuffer msgContent = new StringBuffer();
							if(resOrder.size()>0){
								for(Order or:resOrder){
									msgContent.append(" "+or.getTable().getId());
								}
								handleGuests = g;
								isAddedWaitCustomerToOrder = true;
								orderLogic.addLog("[System]Add waiting customer "+g+" to "+msgContent);
								msgBox.setMessage("Waiting Customer "+g.getId()+" occupied table of "+msgContent+", is that ok?");
								msgBoxResult = msgBox.open();
								break;
							}
						}
					}
					if(isAddedWaitCustomerToOrder)
						guestWaitlist.remove(handleGuests);
					orderLogic.saveWaitingList();
				//}
			}
			//if(msgBoxResult==SWT.OK){
				orderLogic.saveResturant();
			/*}else{
				ol.loadResturant();
			}*/
			shell.close();
			mainUI.doRefreshCurrentTableView();
		}
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {}
	}
}

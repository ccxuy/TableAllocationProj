package com.tap.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.hexapixel.widgets.generic.Utils;
import com.tap.bizlogic.OrderLogic;
import com.tap.tableordersys.BookOrder;
import com.tap.tableordersys.Order;
import com.tap.tableordersys.Table;

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
			Table t = ol.getRestaurant().findTableByID(textOrderID.getText());
			if(null!=t){
				if(order instanceof BookOrder){
					BookOrder saveOrder = (BookOrder) order;
					t.saveBookOrderByID(saveOrder);
				}else{
					t.saveOrderByID(order);
				}
			}
			ol.saveResturant();
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
				if(o instanceof BookOrder){
					BookOrder cOrder = (BookOrder) o;
					change = o.getTable().deleteBookOrder(cOrder);
				}else{
					change = o.getTable().deleteOrder(o);
				}
			}
			System.out.println(change);
			ol.saveResturant();
		}
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {}
	}
}

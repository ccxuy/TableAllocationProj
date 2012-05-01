package com.tap.ui;

import java.util.List;
import java.util.UUID;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import com.hexapixel.widgets.generic.Utils;
import com.tap.bizlogic.OrderLogic;
import com.tap.tableordersys.Guests;
import com.tap.tableordersys.Order;

public class NewCustomerBox {

	private OrderLogic orderLogic;
	protected Shell shell;
	private Text text;
	private Text text_1;
	Button btnAllowSeatTogether;
	private Text text_2;
	private Label lblNewLabel;

	public NewCustomerBox() {}
	public NewCustomerBox(OrderLogic orderLogic) {
		this.orderLogic = orderLogic;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			NewCustomerBox window = new NewCustomerBox();
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
		shell.setSize(340, 286);
		shell.setText("New customer");
		Utils.centerDialogOnScreen(shell);
		
		Label lblGuestId = new Label(shell, SWT.NONE);
		lblGuestId.setBounds(33, 42, 61, 17);
		lblGuestId.setText("Guest ID");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(167, 42, 73, 23);
		text.setText(UUID.randomUUID().toString().substring(0, 3));
		
		Label lblGuestAmount = new Label(shell, SWT.NONE);
		lblGuestAmount.setBounds(33, 71, 94, 17);
		lblGuestAmount.setText("Guest amount");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(167, 71, 73, 23);
		
		Button btnCommit = new Button(shell, SWT.NONE);
		btnCommit.setBounds(103, 211, 80, 27);
		btnCommit.setText("Commit");
		
		btnAllowSeatTogether = new Button(shell, SWT.CHECK);
		btnAllowSeatTogether.setBounds(69, 108, 146, 17);
		btnAllowSeatTogether.setText("Allow seat together");
		
		lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(33, 131, 61, 17);
		lblNewLabel.setText("Additional infomation");
		btnCommit.addSelectionListener(new btnCommitForNewCustomerListener());

		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(33, 154, 207, 23);
	}
	
	class btnCommitForNewCustomerListener implements SelectionListener{
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {}
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			Integer amount = new Integer(0);;
			try{
			amount = new Integer(text_1.getText());
			if(amount<=0){
				MessageBox msgBox = new MessageBox(shell, 1);
				msgBox.setMessage("How can you have so \"MANY\" people?");
				msgBox.open();
				return;
			}
			}catch (NumberFormatException e) {
				System.err.println(e.getMessage());
				MessageBox msgBox = new MessageBox(shell, 1);
				msgBox.setMessage("Wrong amount input!");
				msgBox.open();
				return;
			}
			List<Order> o = orderLogic.newCustomer(new Guests(text.getText(),text_2.getText() ,amount , false==btnAllowSeatTogether.getSelection()));
			
			if(null!=o){
				MessageBox msgBox = new MessageBox(shell, 2);
				StringBuffer msgContent = new StringBuffer();
				for(Order or:o){
					msgContent.append(" "+or.getTable().getId());
				}
				msgBox.setMessage("Customer occupied table of "+msgContent);
				msgBox.open();
			}else{
				MessageBox msgBox = new MessageBox(shell, 2);
				msgBox.setMessage("Customer should wait until table avalable.");
				msgBox.open();
			}
			shell.close();
		}
	}
}

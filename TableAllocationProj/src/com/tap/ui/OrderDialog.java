package com.tap.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.ui.internal.dnd.SwtUtil;

import com.tap.tableordersys.BookOrder;
import com.tap.tableordersys.Order;

public class OrderDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	
	private Order order;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public OrderDialog(Shell parent, Order o) {
		super(parent, SWT.INHERIT_DEFAULT);
		setText("SWT Dialog");
		this.order = o;
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(266, 268);
		shell.setText("Order");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(33, 39, 61, 17);
		lblNewLabel.setText("ID:");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(141, 36, 73, 23);
		text.setText(order.getOrderID());
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(33, 72, 61, 17);
		lblNewLabel_1.setText("Operator:");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(141, 72, 73, 23);
		text_1.setText(order.getOperatorID());
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(33, 107, 61, 17);
		lblNewLabel_2.setText("Table ID:");
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(141, 101, 73, 23);
		text_2.setText(order.getTable().getId());

		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setBounds(33, 139, 61, 17);
		lblNewLabel_3.setText("Guest ID:");
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setBounds(141, 133, 73, 23);
		text_3.setText(order.getGusets().getId());

		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setBounds(33, 178, 73, 17);
		lblNewLabel_4.setText("Guest amout:");
		
		text_4 = new Text(shell, SWT.BORDER);
		text_4.setBounds(141, 172, 73, 23);
		text_4.setText(order.getGusets().getId());
		
		if(order instanceof BookOrder){
			Label lblBookTime = new Label(shell, SWT.NONE);
			lblBookTime.setBounds(33, 215, 73, 17);
			lblBookTime.setText("Book time:");
			
			text_5 = new Text(shell, SWT.BORDER);
			text_5.setBounds(141, 213, 73, 23);
			text_5.setText( ((BookOrder)order).getBookTime().toString() );
		}

		Button btnConfirm = new Button(shell, SWT.NONE);
		btnConfirm.setBounds(83, 250, 80, 27);
		btnConfirm.setText("Confirm");
	}
}

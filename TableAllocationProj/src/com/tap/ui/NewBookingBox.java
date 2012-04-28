package com.tap.ui;

import java.util.UUID;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;

import com.hexapixel.widgets.generic.Utils;
import com.tap.bizlogic.OrderLogic;
import com.tap.tableordersys.BookOrder;
import com.tap.tableordersys.Guests;
import com.tap.tableordersys.Table;
import com.tap.usersys.Operator;

public class NewBookingBox {

	private OrderLogic orderLogic;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	DateTime dateTime;
	DateTime dateTime_1;

	public NewBookingBox(){}
	public NewBookingBox(OrderLogic orderLogic) {
		super();
		this.orderLogic = orderLogic;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			NewBookingBox window = new NewBookingBox();
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
		shell.setSize(502, 316);
		shell.setText("New book order");
		Utils.centerDialogOnScreen(shell);
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("Guest ID");
		label.setBounds(41, 32, 61, 17);
		
		text = new Text(shell, SWT.BORDER);
		text.setText(UUID.randomUUID().toString().substring(0, 3));
		text.setBounds(175, 32, 73, 23);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("Guest amount");
		label_1.setBounds(41, 61, 94, 17);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(175, 61, 73, 23);
		
		Button button = new Button(shell, SWT.CHECK);
		button.setText("Allow seat together");
		button.setBounds(77, 98, 146, 17);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("Additional infomation");
		label_2.setBounds(41, 121, 61, 17);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(41, 144, 207, 23);
		
		Label lblBookTime = new Label(shell, SWT.NONE);
		lblBookTime.setBounds(41, 186, 61, 17);
		lblBookTime.setText("Book time");
		
		dateTime = new DateTime(shell, SWT.BORDER | SWT.TIME | SWT.SHORT);
		dateTime.setBounds(155, 183, 93, 24);
		
		Label lblBookDate = new Label(shell, SWT.NONE);
		lblBookDate.setBounds(267, 20, 61, 17);
		lblBookDate.setText("Book date");
		
		dateTime_1 = new DateTime(shell, SWT.BORDER | SWT.CALENDAR);
		dateTime_1.setBounds(257, 44, 219, 160);
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setText("Commit");
		button_1.setBounds(197, 241, 80, 27);
		button_1.addSelectionListener(new btCommitListener());

	}
	class btCommitListener implements SelectionListener{
		@Override
		public void widgetSelected(SelectionEvent e) {
			System.out.println(dateTime);
			System.out.println(dateTime_1);
			System.out.println(dateTime_1.getMonth());
			System.out.println(dateTime.getHours());
			System.out.println(dateTime.getMinutes());
			hirondelle.date4j.DateTime bookTime = new hirondelle.date4j.DateTime(dateTime_1.getYear(), dateTime_1.getMonth()+1, dateTime_1.getDay(), dateTime.getHours(), dateTime.getMinutes(), 0, 0);
			System.out.println(bookTime);
			BookOrder bo = new BookOrder(new Operator("test", "123"), new Table("123", 12), new Guests("123"), bookTime);
		}
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {}
	}
}

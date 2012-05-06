package com.tap.ui;

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
import com.tap.locinfo.Status;
import com.tap.tableordersys.Table;

public class TableAddBox {
	OrderLogic orderLogic;
	
	private MainUI mainUI;
	protected Shell shell;
	private Text textTableID;
	private Text textCapacity;


	public TableAddBox() {
		super();
	}
	public TableAddBox(MainUI mainUI, OrderLogic orderLogic) {
		this.orderLogic = orderLogic;
		this.mainUI = mainUI;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TableAddBox window = new TableAddBox();
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
		shell.setSize(254, 271);
		shell.setText("Add table");
		Utils.centerDialogOnScreen(shell);
		
		Label lblTableId = new Label(shell, SWT.NONE);
		lblTableId.setBounds(27, 34, 61, 17);
		lblTableId.setText("Table ID");
		
		textTableID = new Text(shell, SWT.BORDER);
		textTableID.setBounds(124, 28, 73, 23);
		textTableID.setText(UUID.randomUUID().toString().substring(0, 3));
		
		Label lblCapacity = new Label(shell, SWT.NONE);
		lblCapacity.setBounds(27, 83, 61, 17);
		lblCapacity.setText("Capacity");
		
		textCapacity = new Text(shell, SWT.BORDER);
		textCapacity.setBounds(124, 83, 73, 23);
		textCapacity.setText("10");
		
		Button btnAdd = new Button(shell, SWT.NONE);
		btnAdd.setBounds(72, 156, 80, 27);
		btnAdd.setText("Add");
		btnAdd.addSelectionListener(new btnAddTableListener());
	}
	class btnAddTableListener implements SelectionListener{
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {}
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			try{
				int capacity = new Integer(textCapacity.getText());
				if(capacity<=0){
					MessageBox msgBox = new MessageBox(shell, 1);
					msgBox.setMessage("Capacity incorrect!");
					msgBox.open();
					return;
				}
				
				Table newTable = new Table(textTableID.getText(), capacity);
				int change = orderLogic.getRestaurant().addTable(newTable);
				orderLogic.saveResturant();
				if(change==Status.SUCCESS.getValue()){
					
				}else{
					MessageBox msgBox = new MessageBox(shell, 1);
					msgBox.setMessage("New table FAIL, dupicate ID!");
					msgBox.open();
				}
			}catch (NumberFormatException e) {
				MessageBox msgBox = new MessageBox(shell, 1);
				msgBox.setMessage("Capacity should be number!");
				msgBox.open();
			}
			shell.close();
			mainUI.doRefreshCurrentTableView();
		}
	}
}

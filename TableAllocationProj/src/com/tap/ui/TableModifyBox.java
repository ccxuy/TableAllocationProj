package com.tap.ui;

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
import com.tap.tableordersys.Table;

public class TableModifyBox {
	private MainUI mainUI;
	private OrderLogic ol;
	private Table cTable;

	protected Shell shell;
	private Text textID;
	private Text textCapacity;

	
	public TableModifyBox() {
		super();
	}

	public TableModifyBox(Table t, MainUI mainUI, OrderLogic ol) {
		super();
		this.ol = ol;
		this.mainUI = mainUI;
		this.cTable = t;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TableModifyBox window = new TableModifyBox();
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
		shell.setSize(285, 280);
		shell.setText("SWT Application");
		Utils.centerDialogOnScreen(shell);
		
		Label lblId = new Label(shell, SWT.NONE);
		lblId.setBounds(42, 46, 61, 17);
		lblId.setText("ID");
		
		textID = new Text(shell, SWT.BORDER);
		textID.setBounds(135, 40, 73, 23);
		textID.setText(cTable.getId());
		
		Label lblCapacity = new Label(shell, SWT.NONE);
		lblCapacity.setBounds(42, 108, 61, 17);
		lblCapacity.setText("Capacity");
		
		textCapacity = new Text(shell, SWT.BORDER);
		textCapacity.setBounds(135, 102, 73, 23);
		textCapacity.setText(cTable.getCapacityString());
		
		Button btnSave = new Button(shell, SWT.NONE);
		btnSave.setBounds(27, 167, 80, 27);
		btnSave.setText("Save");
		
		Button btnDelete = new Button(shell, SWT.NONE);
		btnDelete.setBounds(146, 167, 80, 27);
		btnDelete.setText("Delete");
		btnDelete.addSelectionListener(new btDeleteListener());
	}
	class btSaveListener implements SelectionListener{
		@Override
		public void widgetSelected(SelectionEvent e) {
			Table modifyTable = ol.getRestaurant().findTableByID(textID.getText());
			
			
			if(modifyTable!=null){
				try {
					int capacity = new Integer(textCapacity.getText());
					if(capacity<=0){
						MessageBox msgBox = new MessageBox(shell, 1);
						msgBox.setMessage("Capacity should bigger than 1.");
						msgBox.open();
						return;
					}
					modifyTable.setCapacity(capacity);
				} catch (NumberFormatException e2) {
					MessageBox msgBox = new MessageBox(shell, 1);
					msgBox.setMessage("Capacity input error, not an integer.");
					msgBox.open();
					return;
				}
			}else{
				MessageBox msgBox = new MessageBox(shell, 1);
				msgBox.setMessage("No such Table!");
				msgBox.open();
				return;
			}
			ol.saveResturant();
			shell.close();
			mainUI.doRefreshCurrentTableView();
		}
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {}
	}
	class btDeleteListener implements SelectionListener{
		@Override
		public void widgetSelected(SelectionEvent e) {
			Table modifyTable = ol.getRestaurant().findTableByID(textID.getText());
			if(modifyTable!=null){
				ol.getRestaurant().getTableList().remove(modifyTable);
			}else{
				MessageBox msgBox = new MessageBox(shell, 1);
				msgBox.setMessage("No such Table!");
				msgBox.open();
				return;
			}
			ol.saveResturant();
			shell.close();
			mainUI.doRefreshCurrentTableView();
		}
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {}
	}
}

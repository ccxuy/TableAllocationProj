package com.tap.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import com.hexapixel.widgets.generic.Utils;
import com.tap.bizlogic.OrderLogic;
import com.tap.tableordersys.Table;

public class TableModifyBox {
	private OrderLogic ol;
	private Table cTable;

	protected Shell shell;
	private Text textID;
	private Text textCapacity;

	
	public TableModifyBox() {
		super();
	}

	public TableModifyBox(Table t, OrderLogic ol) {
		super();
		this.ol = ol;
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

	}
}

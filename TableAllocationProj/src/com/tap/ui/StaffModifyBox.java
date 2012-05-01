package com.tap.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.hexapixel.widgets.generic.Utils;
import com.tap.bizlogic.OperatorLogic;
import com.tap.usersys.Operator;
import com.tap.usersys.Operator.Position;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;

public class StaffModifyBox {
	private OperatorLogic ol;
	private Operator cOperator;

	protected Shell shell;
	private Text text;
	private Text text_1;

	public StaffModifyBox() {
		super();
	}

	public StaffModifyBox(Operator cOperator, OperatorLogic ol) {
		super();
		this.ol = ol;
		this.cOperator = cOperator;
	}



	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			StaffModifyBox window = new StaffModifyBox();
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
		shell.setSize(359, 305);
		shell.setText("Staff");
		Utils.centerDialogOnScreen(shell);
		
		Label lblId = new Label(shell, SWT.NONE);
		lblId.setBounds(49, 37, 61, 17);
		lblId.setText("ID");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(175, 37, 104, 23);
		
		Label lblPassword = new Label(shell, SWT.NONE);
		lblPassword.setBounds(49, 86, 61, 17);
		lblPassword.setText("Password");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(175, 83, 104, 23);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(49, 137, 61, 17);
		lblNewLabel.setText("New Label");
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(175, 134, 104, 25);
		
		Button btnSave = new Button(shell, SWT.NONE);
		btnSave.setBounds(49, 201, 80, 27);
		btnSave.setText("Save");
		
		Button btnDelete = new Button(shell, SWT.NONE);
		btnDelete.setBounds(182, 201, 80, 27);
		btnDelete.setText("Delete");
		for(Position p:Operator.Position.values()){
			combo.add(p.getString());
		}

	}
}

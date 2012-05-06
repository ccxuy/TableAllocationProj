package com.tap.ui;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.hexapixel.widgets.generic.Utils;
import com.tap.bizlogic.OperatorLogic;
import com.tap.usersys.Operator;
import com.tap.usersys.Operator.Position;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;

public class NewStaffBox {
	private OperatorLogic ol;

	protected Shell shell;
	private Text textID;
	private Text textPassword;
	private Combo combo;
	private MainUI mainUI;

	public NewStaffBox() {
		super();
	}

	public NewStaffBox(MainUI mainUI, OperatorLogic ol) {
		super();
		this.ol = ol;
		this.mainUI = mainUI;
	}



	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			NewStaffBox window = new NewStaffBox();
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
		
		textID = new Text(shell, SWT.BORDER);
		textID.setBounds(175, 37, 104, 23);
		
		Label lblPassword = new Label(shell, SWT.NONE);
		lblPassword.setBounds(49, 86, 61, 17);
		lblPassword.setText("Password");
		
		textPassword = new Text(shell, SWT.BORDER);
		textPassword.setBounds(175, 83, 104, 23);
		textPassword.setEchoChar('●');
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(49, 137, 61, 17);
		lblNewLabel.setText("New Label");
		
		combo = new Combo(shell, SWT.NONE);
		combo.setBounds(175, 134, 104, 25);
		for(Position p:Operator.Position.values()){
			combo.add(p.getString());
		}
		
		Button btnSave = new Button(shell, SWT.NONE);
		btnSave.setBounds(49, 201, 80, 27);
		btnSave.setText("Add");
		btnSave.addSelectionListener(new btAddListener());
	}
	class btAddListener implements SelectionListener{
		@Override
		public void widgetSelected(SelectionEvent e) {
			Operator mOp = ol.findOperatorByID(textID.getText());
			if(mOp!=null){
				MessageBox msgBox = new MessageBox(shell, 1);
				msgBox.setMessage("Member already exist in system!");
				msgBox.open();
				return;
			}

			String position = combo.getText();
			boolean correctPosition = false;
			for(Position p:Operator.Position.values()){
				if( position.equals(p.getString()) )
					correctPosition=true;
			}
			if(correctPosition==false){
				MessageBox msgBox = new MessageBox(shell, 1);
				msgBox.setMessage("Please select an correct position!");
				msgBox.open();
				return;
			}
			
			ol.register(textID.getText(), textPassword.getText(), position);
			shell.close();
			mainUI.doRefreshCurrentTableView();
		}
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {}
	}
}

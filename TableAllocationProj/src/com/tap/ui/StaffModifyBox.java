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

public class StaffModifyBox {
	private OperatorLogic ol;
	private Operator cOperator;

	protected Shell shell;
	private Text textID;
	private Text textPassword;
	private Combo combo;
	private MainUI mainUI;

	public StaffModifyBox() {
		super();
	}

	public StaffModifyBox(Operator cOperator,MainUI mainUI, OperatorLogic ol) {
		super();
		this.ol = ol;
		this.mainUI = mainUI;
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
		
		textID = new Text(shell, SWT.BORDER);
		textID.setBounds(175, 37, 104, 23);
		textID.setText(cOperator.getId());
		
		Label lblPassword = new Label(shell, SWT.NONE);
		lblPassword.setBounds(49, 86, 61, 17);
		lblPassword.setText("Password");
		
		textPassword = new Text(shell, SWT.BORDER);
		textPassword.setBounds(175, 83, 104, 23);
		textPassword.setEchoChar('●');
		textPassword.setText(cOperator.getPassword());
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(49, 137, 61, 17);
		lblNewLabel.setText("New Label");
		
		combo = new Combo(shell, SWT.NONE);
		combo.setBounds(175, 134, 104, 25);
		int select = 0;
		int i = 0;
		for(Position p:Operator.Position.values()){
			combo.add(p.getString());
			if(cOperator.getPosition().equals(p.getString()))
				select = i;
			i++;
		}
		combo.select(select);
		
		Button btnSave = new Button(shell, SWT.NONE);
		btnSave.setBounds(49, 201, 80, 27);
		btnSave.setText("Save");
		btnSave.addSelectionListener(new btSaveListener());
		
		Button btnDelete = new Button(shell, SWT.NONE);
		btnDelete.setBounds(182, 201, 80, 27);
		btnDelete.setText("Delete");
		btnDelete.addSelectionListener(new btDeleteListener());
	}
	class btSaveListener implements SelectionListener{
		@Override
		public void widgetSelected(SelectionEvent e) {
			Operator mOp = ol.findOperatorByID(textID.getText());
			if(mOp==null){
				MessageBox msgBox = new MessageBox(shell, 1);
				msgBox.setMessage("No such member exist in system!");
				msgBox.open();
				return;
			}

			ol.deleteOperator(mOp.getId());
			mOp.setId(textID.getText());
			mOp.setPassword(textPassword.getText());
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
			mOp.setPosition(combo.getText());
			ol.register(mOp.getId(), mOp.getPassword(), mOp.getPosition());
			shell.close();
			mainUI.doRefreshCurrentTableView();
		}
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {}
	}
	class btDeleteListener implements SelectionListener{
		@Override
		public void widgetSelected(SelectionEvent e) {
			Operator mOp = ol.findOperatorByID(textID.getText());
			if(mOp==null){
				MessageBox msgBox = new MessageBox(shell, 1);
				msgBox.setMessage("No such member exist in system!");
				msgBox.open();
				return;
			}

			ol.deleteOperator(mOp.getId());
			shell.close();
			mainUI.doRefreshCurrentTableView();
		}
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {}
	}
}

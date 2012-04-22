package com.tap.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class NewTableDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public NewTableDialog(Shell parent, int style) {
		super(parent, style);
		setText("New Table Dialog");
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
		shell.setSize(223, 233);
		shell.setText(getText());
		
		Label lblId = new Label(shell, SWT.NONE);
		lblId.setBounds(22, 33, 61, 17);
		lblId.setText("ID:");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(122, 30, 73, 23);
		
		Label lblCapacity = new Label(shell, SWT.NONE);
		lblCapacity.setBounds(22, 73, 61, 17);
		lblCapacity.setText("Capacity:");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(122, 70, 73, 23);
		
		Button btnNewTable = new Button(shell, SWT.NONE);
		btnNewTable.setBounds(65, 142, 80, 27);
		btnNewTable.setText("New table");

	}

}

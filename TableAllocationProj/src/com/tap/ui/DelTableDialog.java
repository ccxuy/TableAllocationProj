package com.tap.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class DelTableDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DelTableDialog(Shell parent, int style) {
		super(parent, style);
		setText("Delete Table Dialog");
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
		shell.setSize(235, 196);
		shell.setText(getText());
		
		Label lblTableId = new Label(shell, SWT.NONE);
		lblTableId.setBounds(35, 36, 61, 17);
		lblTableId.setText("Table ID:");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(134, 30, 73, 23);
		
		Button btnDeleteThisTable = new Button(shell, SWT.NONE);
		btnDeleteThisTable.setBounds(74, 102, 80, 27);
		btnDeleteThisTable.setText("Delete this Table");

	}

}

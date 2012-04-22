package com.tap.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.hexapixel.widgets.ribbon.RibbonShell;

public class OrderModifyDialog extends Dialog {

	protected Object result;
	//protected Shell shell;
	RibbonShell shell = null;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public OrderModifyDialog(Shell parent, int style) {
		super(parent, style);
		setText("Order Modify Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		//shell.layout();
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
		shell = new RibbonShell(getParent(), SWT.INHERIT_DEFAULT);
		shell.setSize(450, 300);
		shell.setText(getText());

	}

}

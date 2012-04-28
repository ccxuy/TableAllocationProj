package com.tap.ui;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

public class TestBox {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TestBox window = new TestBox();
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
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		CoolBar coolBar = new CoolBar(shell, SWT.FLAT);
		coolBar.setBounds(22, 40, 150, 30);
		
		CoolItem coolItem = new CoolItem(coolBar, SWT.NONE);
		
		CoolItem coolItem_1 = new CoolItem(coolBar, SWT.NONE);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(69, 165, 80, 27);
		btnNewButton.setText("New Button");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(227, 91, 61, 17);
		lblNewLabel.setText("New Label");
		lblNewLabel.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent arg0) {}
			@Override
			public void mouseDown(MouseEvent arg0) {}
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				System.out.println("dc");
			}
		});

	}
}

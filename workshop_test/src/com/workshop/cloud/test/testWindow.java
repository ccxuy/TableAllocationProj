package com.workshop.cloud.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

/**
 * @author james
 *
 */
public class testWindow {
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Label lblUsername;
	private Label lblPassword;
	
	private Button btnNewButton;
	private Button btnNewButton_1;
	private Button btnOrder;
	private Button btnSearchOrder;
	
	private TableColumn tblclmnNewColumn;
	private TabItem tbtmNewItem_1;
	
	private Composite composite;
	private Composite composite_1;
	private Composite composite_2;
	private Composite composite_3;
	
	private Table table_1;
	private Table table;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			testWindow window = new testWindow();
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
		shell.setSize(746, 488);
		shell.setText("SWT Application");
		shell.setLayout(new FormLayout());
		//set table view
		
		
		composite_1 = new Composite(shell, SWT.NONE);
		FormData fd_composite_1 = new FormData();
		fd_composite_1.bottom = new FormAttachment(0, 151);
		fd_composite_1.right = new FormAttachment(0, 312);
		fd_composite_1.top = new FormAttachment(0, 10);
		fd_composite_1.left = new FormAttachment(0, 94);
		composite_1.setLayoutData(fd_composite_1);
		
		text = new Text(composite_1, SWT.BORDER);
		text.setBounds(120, 10, 73, 23);
		
		text_1 = new Text(composite_1, SWT.BORDER);
		text_1.setBounds(120, 59, 73, 23);
		
		lblUsername = new Label(composite_1, SWT.NONE);
		lblUsername.setBounds(10, 10, 61, 17);
		lblUsername.setText("username");
		
		lblPassword = new Label(composite_1, SWT.NONE);
		lblPassword.setBounds(10, 59, 61, 17);
		lblPassword.setText("password");
		
		//login
		btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				System.out.print("Login");
			}
		});
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton.setBounds(10, 99, 80, 27);
		btnNewButton.setText("Login");
		
		
		btnNewButton_1 = new Button(composite_1, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton_1.setBounds(113, 99, 80, 27);
		btnNewButton_1.setText("Logout");
		
		composite_2 = new Composite(shell, SWT.NONE);
		FormData fd_composite_2 = new FormData();
		fd_composite_2.bottom = new FormAttachment(0, 151);
		fd_composite_2.right = new FormAttachment(0, 555);
		fd_composite_2.top = new FormAttachment(0, 10);
		fd_composite_2.left = new FormAttachment(0, 328);
		composite_2.setLayoutData(fd_composite_2);
		
		btnOrder = new Button(composite_2, SWT.NONE);
		btnOrder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			System.out.print("1111111111111");
			}
		});
		btnOrder.setBounds(10, 10, 80, 27);
		btnOrder.setText("Order");
		
		btnSearchOrder = new Button(composite_2, SWT.NONE);
		btnSearchOrder.setBounds(10, 56, 80, 27);
		btnSearchOrder.setText("Search Order");
		
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		FormData fd_tabFolder = new FormData();
		fd_tabFolder.bottom = new FormAttachment(composite_1, 245, SWT.BOTTOM);
		fd_tabFolder.top = new FormAttachment(composite_1, 24);
		fd_tabFolder.left = new FormAttachment(0, 53);
		fd_tabFolder.right = new FormAttachment(0, 646);
		tabFolder.setLayoutData(fd_tabFolder);
		
		//order list
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("Order List");
		composite = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite);
		table = addTableToComposite(table,tbtmNewItem);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
			System.out.print(table.getSelectionCount());
			}
		});
		
		//waiting list
		tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("Waiting List");
		composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_1.setControl(composite_3);
		table_1 = addTableToComposite(table_1,tbtmNewItem_1);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
			System.out.print(table_1.getSelectionCount());
			}
		});
	}
	
	/**
	 * @param t
	 * @param tbtmNewItem
	 * @return
	 */
	public Table addTableToComposite(Table t,TabItem tbtmNewItem){
		TableColumnLayout tcl_composite = new TableColumnLayout();
		String check = tbtmNewItem.getText();
		Composite tmpcomposite = null;
		if(check=="Order List"){
			tmpcomposite = composite;
		}else if(check == "Waiting List"){
			tmpcomposite = composite_3;
		}
		tmpcomposite.setLayout(tcl_composite);
		t = new Table(tmpcomposite, SWT.BORDER | SWT.FULL_SELECTION);
		t.setTouchEnabled(true);
		t.setHeaderVisible(true);
		t.setLinesVisible(true);
		setTableColumns(t,tcl_composite,tbtmNewItem);
		setTable(t,tbtmNewItem);
		return t;
	}
	
	/**
	 * @param tcl_composite
	 */
	public void setTableColumns(Table t,TableColumnLayout tcl_composite,TabItem tbtmNewItem){
		int i = 0;
		String check = tbtmNewItem.getText();
		if(check=="Order List"){
			i=0;
		}else if(check == "Waiting List"){
			i=2;
		}
		for(;i<5;i++){
			tblclmnNewColumn = new TableColumn(t, SWT.NONE);
			tcl_composite.setColumnData(tblclmnNewColumn, new ColumnPixelData(150, true, true));
			tblclmnNewColumn.setText(String.valueOf(i));
			}
	}
	
	/**
	 * item is added to table directly
	 */
	public void setTable(Table t,TabItem tbtmNewItem){
		int i = 0;
		String check = tbtmNewItem.getText();
		if(check=="Order List"){
			i=0;
		}else if(check == "Waiting List"){
			i=2;
		}
		TableItem[] tableItemarray = new TableItem[3];
		for(;i<=2;i++){
			tableItemarray[i] = new TableItem(t, SWT.NONE);
			tableItemarray[i].setText(new String[]{String.valueOf(i),String.valueOf(i),String.valueOf(i),String.valueOf(i)});} 
}
}
package com.tap.ui;
/*******************************************************************************
 * Copyright (c) Emil Crumhorn - Hexapixel.com - emil.crumhorn@gmail.com
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    emil.crumhorn@gmail.com - initial API and implementation
 *******************************************************************************/ 

import java.awt.Dialog;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TypedListener;
import org.eclipse.wb.swt.SWTResourceManager;

import swing2swt.layout.FlowLayout;

import com.hexapixel.widgets.generic.ColorCache;
import com.hexapixel.widgets.generic.ImageCache;
import com.hexapixel.widgets.generic.Utils;
import com.hexapixel.widgets.ribbon.ButtonSelectGroup;
import com.hexapixel.widgets.ribbon.QuickAccessShellToolbar;
import com.hexapixel.widgets.ribbon.RibbonButton;
import com.hexapixel.widgets.ribbon.RibbonButtonGroup;
import com.hexapixel.widgets.ribbon.RibbonCheckbox;
import com.hexapixel.widgets.ribbon.RibbonGroup;
import com.hexapixel.widgets.ribbon.RibbonGroupSeparator;
import com.hexapixel.widgets.ribbon.RibbonShell;
import com.hexapixel.widgets.ribbon.RibbonTab;
import com.hexapixel.widgets.ribbon.RibbonTabFolder;
import com.hexapixel.widgets.ribbon.RibbonToolbar;
import com.hexapixel.widgets.ribbon.RibbonToolbarGrouping;
import com.hexapixel.widgets.ribbon.RibbonTooltip;
import com.tap.bizlogic.OperatorLogic;
import com.tap.bizlogic.OrderLogic;
import com.tap.tableordersys.BookOrder;
import com.tap.tableordersys.Guests;
import com.tap.tableordersys.Order;
import com.tap.tableordersys.Restaurant;
import com.tap.tableordersys.WaitingList;
import com.tap.usersys.Operator;

public class MainUI {

	RibbonShell shell = null;
	TableViewer tableViewer = null;
	Table table = null;
	TableCursor tCursor;
	MessageBox msgBox = null;
	//Home tab
	RibbonTab ftHome = null;
	RibbonGroup tabGroupAccount = null;
	RibbonButtonGroup userSub = null;
	RibbonButton userID = null;
	RibbonButton userPostion = null;
	RibbonButton btLogoutBut = null;
	RibbonGroup tabGroupLogin = null;
	RibbonButton btLoginBut = null;
	Text loginIDText = null;
	Text loginPWText = null;
	RibbonGroup tabGroupQuickLaunch = null;
	//Order tab
	RibbonTab ftOrder = null;
	RibbonTab ftTable = null;
	
	//=======Logic stuff========
	private OperatorLogic opLogic = new OperatorLogic();
	private OrderLogic orderLogic = new OrderLogic();
	
    /**
     * @param args
     */
    public void show() {
        Display display = new Display();
        shell = new RibbonShell(display, SWT.INHERIT_DEFAULT);
        shell.setButtonImage(ImageCache.getImage("desk_32x32.ico"));
        //Shell shell = new Shell(display);
        
        shell.setText("Table Allocation Prgram");
        shell.setSize(800, 500);
        
        msgBox = new MessageBox(shell.getShell());
        
        //shell.getShell().setLayout(new FillLayout());
        /*shell.getShell().setLayout(new FlowLayout(swing2swt.layout.FlowLayout.CENTER, 5, 5));*/
        //Text text = new Text(shell.getShell(), SWT.TOP);

        //System.out.println(shell.getShell().getLayout());
        //Layout layout = shell.getShell().getLayout();
        
        doGenerateTableOfTest(shell.getShell());
        
        //shell.getShell().setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        /*MainViewComposite mainComposite = new MainViewComposite(shell.getShell(), SWT.BORDER);
        //Composite mainComposite = new Composite(shell.getShell(), SWT.BORDER);

        mainComposite.setBackground(new Color(null, 200, 200, 200));
        mainComposite.setEnabled(false);
        //mainComposite.setSize(600, 700);
        //mainComposite.setBounds(1, 1, 100, 700);
       // mainComposite.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        //mainComposite.setLayout(new FillLayout(SWT.BORDER));
        //mainComposite.setLayout(null);
        
        
        doGenerateFormView(mainComposite.getShell());
        mainComposite.setContent(table);*/
        
        doGenerateToolbar(shell);
        
        
//        shell.setLayout(new FillLayout());
//        Composite inner = new Composite(shell, SWT.None);
//        inner.setLayout(new FillLayout(SWT.VERTICAL)); 
//        inner.setBackground(ColorCache.getInstance().getColor(182, 206, 238));        
        
        // Tab folder
        //final RibbonTabFolder ftf = new RibbonTabFolder(inner, SWT.NONE);
        RibbonTabFolder ftf = shell.getRibbonTabFolder();
        ftf.setHelpImage(ImageCache.getImage("questionmark.gif"));
        ftf.getHelpButton().setToolTip(new RibbonTooltip("Help", "Get Help Using Whatever This Is"));
        
        //ftf.setDrawEmptyTabs(false);
        // Tabs
        ftHome = new RibbonTab(ftf, "Home");
        ftOrder = new RibbonTab(ftf, "Ordering");
        ftTable = new RibbonTab(ftf, "Table, Staff and History");
        
        // Tooltip
        RibbonTooltip toolTip = new RibbonTooltip("Some Action Title", "This is content text that\nsplits over\nmore than one\nline\n\\b\\c255000000and \\xhas \\bdifferent \\c000000200look \\xand \\bfeel.", ImageCache.getImage("tooltip.jpg"), ImageCache.getImage("questionmark.gif"), "Press F1 for more help"); 

        // Group

        doGenerateHomeTab(ftHome);
              
        doGenerateOrderingTab(ftOrder);
        
        doGenerateTableStaffHistTab(ftTable);
        
        //Actions for the UI
        doRefreshFunctionsInUI();
        
        Utils.centerDialogOnScreen(shell.getShell());
        shell.open();
        while (!shell.isDisposed ()) {
            if (!display.readAndDispatch ()) display.sleep ();
        }
        display.dispose ();
    }

	private void doGenerateToolbar(final RibbonShell shell) {
		QuickAccessShellToolbar mtb = shell.getToolbar();
        RibbonButton mtbtb1 = new RibbonButton(mtb, ImageCache.getImage("gear_ok_16.gif"), null, SWT.NONE);
        shell.setBigButtonTooltip(new RibbonTooltip("Account", "Click here to login or logout your account"));
        mtb.setArrowTooltip(new RibbonTooltip("No more things", ""));
        
        Menu shellMenu = shell.getBigButtonMenu();
        MenuItem miQuit = new MenuItem(shellMenu, SWT.POP_UP);
        miQuit.setText("Quit");
        
        shell.addBigButtonListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
            }

            public void widgetSelected(SelectionEvent e) {
                System.err.println("Clicked big button");
                shell.showBigButtonMenu();
            }
            
        });
	}

	private void doGenerateFormView(final Shell shell) {
		//System.out.println(shell.getShell().getBounds());
		//diplay Form view
        // Order Composite
		/*ScrolledComposite scrolledComposite = new ScrolledComposite(shell.getShell(), SWT.BORDER|SWT.V_SCROLL|SWT.H_SCROLL);
		scrolledComposite.setAlwaysShowScrollBars(true);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setSize(scrolledComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.setBackground(new Color(null, 250, 250, 250));
		scrolledComposite.setSize(640, 460);
		scrolledComposite.setBounds(0, 0, 640, 460);
		scrolledComposite.setLayout(new FlowLayout());//*/
		
		 doGenerateTableOfTest(shell);
	        
	        
        //doAddTableToForm(scrolledComposite);
		// end Oder Composite
	}

	private void doGenerateTableOfTest(final Shell shell) {
		doGenerateNewTableView(shell);

		doGenerateTableColumn(new String[]{"id","order"});
		doGenerateTableItem(new String[]{"id1","order2"});
	}

	private void doGenerateNewTableView(final Shell shell) {
		if(null!=table){
			table.dispose();
			//System.out.println("Create new Table.");
		}
		tableViewer = new TableViewer(shell, SWT.CHECK | SWT.MULTI
				| SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		tCursor = new TableCursor(table, SWT.NONE);
		
		table.addListener(SWT.Resize, new Listener() {
			public void handleEvent(Event event) {

				// table.setLocation(shell.getLocation());
				// System.out.println("setLocation"+table.getLocation());
				// System.out.println("size:"+table.getSize());
				computeFormSizeForItem(shell);
			}
		});
	}
	protected void computeFormSizeForItem(final Shell shell) {
		int top = 143;
		int left = 0;
		int maxHight = shell.getBounds().height-143;
		Control[] items = shell.getChildren();
		for (int i = 1; i < items.length; i++) {
			Control item = items[i];
			item.setLocation(left, top);
			/*if(item.getSize().y<maxHight)
				item.setSize(shell.getBounds().width, item.getSize().y);
			else*/
				item.setSize(shell.getBounds().width, maxHight);
				
			top += item.getBounds().height;
			//System.out.println(i + " top " + top);
		}
		//System.out.println("computeFormSizeForItem : "+top);
	}
	
	private void doGenerateTableItem(String[] itemStrings) {
		TableItem newTableItem = new TableItem(table, SWT.NONE);
		newTableItem.setText(itemStrings);
	}

	/**
	 * @param boxTypeOfListener
	 * @param ti
	 */
	//[FIXME] ModifyBoxDCListenerForTable
	private void doAddModifyBoxDCListenerToTable(String boxTypeOfListener) {
		if(boxTypeOfListener.equalsIgnoreCase("Order")){
			tCursor.addMouseListener(new tiModifyBoxListener());
		}
		
	}

	private void doGenerateTableColumn(String[] columnStrings) {
		for(String s: columnStrings){
			final TableColumn newColumnTableColumn = new TableColumn(table,
					SWT.NONE);
			newColumnTableColumn.setWidth(100);
			newColumnTableColumn.setText(s);
		}
	}

	private void doAddTableToForm(ScrolledComposite scrolledComposite) {
		Composite comp = new Composite(scrolledComposite.getShell(), SWT.BORDER);

		comp.setBackground(new Color(null, 240, 240, 240));
		comp.setSize(450, 300);
		comp.setBounds(65, 7, 120, 60);
		
		Image headImage = new Image(null, "image/dinner_table.png");
        Label headImageLable = new Label(comp, 0);
        headImageLable.setBackgroundImage(headImage);
        headImageLable.setBounds(headImageLable.getBounds().x+7, headImageLable.getBounds().y+7, 48, 48);
		Label l = new Label(comp, SWT.NONE);
		l.setText("123123123");
		l.setBounds(65, 7, 150, 60);
		l.setBackground(new Color(null, 255,255,255));
		scrolledComposite.setContent(comp);
	}

	private void doGenerateTableStaffHistTab(RibbonTab ftTable) {
		//Tab Table, Staff and History (only for admin)
        //Group Table Management
        RibbonGroup rgTable = new RibbonGroup(ftTable, "Table Management");
        //new Image(null, "newOder.gif")
        RibbonButton rbTableView = new RibbonButton(rgTable, ImageCache.getImage("olb_picture.gif"), "Table View", RibbonButton.STYLE_NO_DEPRESS);
        rbTableView.addSelectionListener(new btTableViewListener());
        new RibbonGroupSeparator(rgTable);
        RibbonButtonGroup tableSub = new RibbonButtonGroup(rgTable);
        RibbonButton rbNewTable = new RibbonButton(tableSub, ImageCache.getImage("olb_picture.gif"), "New Table", RibbonButton.STYLE_NO_DEPRESS);
        RibbonButton rbModifyTable = new RibbonButton(tableSub, ImageCache.getImage("olb_picture.gif"), "Modify Table", RibbonButton.STYLE_NO_DEPRESS);
        RibbonButton rbDelTable = new RibbonButton(tableSub, ImageCache.getImage("olb_picture.gif"), "Delete Table", RibbonButton.STYLE_NO_DEPRESS);
        rbNewTable.addSelectionListener(new btAddTableListener());
        //end Booking group
        //Group Staff Management
        RibbonGroup rgStaff = new RibbonGroup(ftTable, "Staff Management");
        //new Image(null, "newOder.gif")
        RibbonButton rbStaffView = new RibbonButton(rgStaff, ImageCache.getImage("olb_picture.gif"), "Staff View", RibbonButton.STYLE_NO_DEPRESS);
        rbStaffView.addSelectionListener(new btStaffViewListener());
        new RibbonGroupSeparator(rgTable);
        RibbonButtonGroup staffSub = new RibbonButtonGroup(rgStaff);
        RibbonButton rbNewStaff = new RibbonButton(staffSub, ImageCache.getImage("olb_picture.gif"), "New Staff", RibbonButton.STYLE_NO_DEPRESS);
        RibbonButton rbModifyStaff = new RibbonButton(staffSub, ImageCache.getImage("olb_picture.gif"), "Modify Staff", RibbonButton.STYLE_NO_DEPRESS);
        RibbonButton rbDelStaff = new RibbonButton(staffSub, ImageCache.getImage("olb_picture.gif"), "Delete Staff", RibbonButton.STYLE_NO_DEPRESS);
        //end Booking group
        //Group Staff Management
        RibbonGroup rgHistory = new RibbonGroup(ftTable, "History");
        //new Image(null, "newOder.gif")
        RibbonButton rbHistoryView = new RibbonButton(rgHistory, ImageCache.getImage("olb_picture.gif"), "History View", RibbonButton.STYLE_NO_DEPRESS);
        //end Booking group
	}

	private void doGenerateOrderingTab(RibbonTab ftOrder) {
		//Ordering
        //[DONE] Ordering
        //Order group 
        RibbonGroup rgOrder = new RibbonGroup(ftOrder, "Current");
        //new Image(null, "newOder.gif")
        RibbonButton btOrderView = new RibbonButton(rgOrder, ImageCache.getImage("olb_picture.gif"), "Order View", RibbonButton.STYLE_NO_DEPRESS);
        btOrderView.addSelectionListener(new btOrderViewListener());
        new RibbonGroupSeparator(rgOrder);
        RibbonButtonGroup orderSub = new RibbonButtonGroup(rgOrder);
        RibbonButton rbNewOrder = new RibbonButton(orderSub, ImageCache.getImage("olb_picture.gif"), "New Order", RibbonButton.STYLE_NO_DEPRESS);
        RibbonButton rbModifyOrder = new RibbonButton(orderSub, ImageCache.getImage("olb_picture.gif"), "Modify Order", RibbonButton.STYLE_NO_DEPRESS);
        RibbonButton rbDelOrder = new RibbonButton(orderSub, ImageCache.getImage("olb_picture.gif"), "Delete Order", RibbonButton.STYLE_NO_DEPRESS);
        rbNewOrder.addSelectionListener(new btNewCustomerListener());
        //end Order group
        
        //Booking group 
        RibbonGroup rgBook = new RibbonGroup(ftOrder, "Booking");
        //new Image(null, "newOder.gif")
        RibbonButton rbNewBookView = new RibbonButton(rgBook, ImageCache.getImage("olb_picture.gif"), "Booking View", RibbonButton.STYLE_NO_DEPRESS);
        rbNewBookView.addSelectionListener(new btBookingViewListener());
        new RibbonGroupSeparator(rgBook);
        RibbonButtonGroup bookSub = new RibbonButtonGroup(rgBook);
        RibbonButton rbNewBook = new RibbonButton(bookSub, ImageCache.getImage("olb_picture.gif"), "New Book", RibbonButton.STYLE_NO_DEPRESS);
        RibbonButton rbModifyBook = new RibbonButton(bookSub, ImageCache.getImage("olb_picture.gif"), "Modify Book", RibbonButton.STYLE_NO_DEPRESS);
        RibbonButton rbDelBook = new RibbonButton(bookSub, ImageCache.getImage("olb_picture.gif"), "Delete Book", RibbonButton.STYLE_NO_DEPRESS);
        rbNewBook.addSelectionListener(new btNewBookingListener());
        //end Booking group
        
        //Wating List group 
        RibbonGroup rgWait = new RibbonGroup(ftOrder, "Wating List");
        //new Image(null, "newOder.gif")
        RibbonButton rbNewWaitView = new RibbonButton(rgWait, ImageCache.getImage("olb_picture.gif"), "Waiting View", RibbonButton.STYLE_NO_DEPRESS);
        rbNewWaitView.addSelectionListener(new btWaitingViewListener());
        new RibbonGroupSeparator(rgWait);
        RibbonButtonGroup waitSub = new RibbonButtonGroup(rgWait);
        RibbonButton rbNewWait = new RibbonButton(waitSub, ImageCache.getImage("olb_picture.gif"), "New wait guest", RibbonButton.STYLE_NO_DEPRESS);
        RibbonButton rbModifyWait = new RibbonButton(waitSub, ImageCache.getImage("olb_picture.gif"), "Modify wait guest", RibbonButton.STYLE_NO_DEPRESS);
        RibbonButton rbDelWait = new RibbonButton(waitSub, ImageCache.getImage("olb_picture.gif"), "Delete wait guest", RibbonButton.STYLE_NO_DEPRESS);
        //end Wating List group
        //end Order tab
	}

	private void doGenerateHomeTab(RibbonTab ftHome) {
		// toolbar group
        //[DONE] Account UI
        tabGroupAccount = new RibbonGroup(ftHome, "Account");
        //Image loginImage = new Image(null, "image/loginButton.png");
        Image userImage = new Image(null, "image/user.png");
        //RibbonButton rbLoginBut = new RibbonButton(tbGroup, loginImage, null, RibbonButton.STYLE_TWO_LINE_TEXT | RibbonButton.STYLE_NO_DEPRESS);//RibbonButton.STYLE_ARROW_DOWN_SPLIT);
        //rbLoginBut.setToolTip(new RibbonTooltip("Login", "Click here to login"));
		RibbonButton userImg = new RibbonButton(tabGroupAccount, userImage, null, 0);
		userImg.setEnabled(false);
		RibbonGroupSeparator rgsAccount = new RibbonGroupSeparator(tabGroupAccount);
		userSub = new RibbonButtonGroup(tabGroupAccount);
		new Text(tabGroupAccount, SWT.BORDER);
		userID = new RibbonButton(userSub, null, "ID : 0903000039", RibbonButton.STYLE_NO_DEPRESS);
		userPostion = new RibbonButton(userSub, null, "Postion : Admin", RibbonButton.STYLE_NO_DEPRESS);
		btLogoutBut = new RibbonButton(userSub, null, "Logout", RibbonButton.STYLE_NO_DEPRESS);
		btLogoutBut.addSelectionListener(new btLogoutListener());
		//rgsAccount.dispose();
		/*userImg.dispose();
		userID.dispose();
		userPostion.dispose();*/
		// end toolbar group
		
		// Login group
        //[DONE] Login UI
        tabGroupLogin = new RibbonGroup(ftHome, "Login");
        GridLayout glLogin = new GridLayout(1, false);
        glLogin.marginHeight = 7;
        glLogin.marginLeft = 170;
        glLogin.marginRight = 0;
        glLogin.verticalSpacing = 1;
        glLogin.horizontalSpacing = 0;
        glLogin.marginBottom = 7;
        tabGroupLogin.setLayout(glLogin);
        Image loginImage = new Image(null, "image/loginButton.png");
        btLoginBut = new RibbonButton(tabGroupLogin, loginImage,null, RibbonButton.STYLE_NO_DEPRESS);//RibbonButton.STYLE_ARROW_DOWN_SPLIT);
        btLoginBut.addSelectionListener(new btLoginListener());
        
        btLoginBut.setToolTip(new RibbonTooltip("Login", "Click here to login"));
		userImg.setEnabled(false);
		RibbonGroupSeparator rgsLogin = new RibbonGroupSeparator(tabGroupLogin);
		RibbonButtonGroup loginSub = new RibbonButtonGroup(tabGroupLogin);
		RibbonButton takeSpaceID = new RibbonButton(loginSub, null, "ID :                         ", RibbonButton.STYLE_NO_DEPRESS);
		RibbonButton takeSpacePW = new RibbonButton(loginSub, null, "Password :                   ", RibbonButton.STYLE_NO_DEPRESS);
		takeSpaceID.setEnabled(false);
		takeSpacePW.setEnabled(false);
		loginIDText = new Text(tabGroupLogin, SWT.BORDER);
		loginPWText = new Text(tabGroupLogin, SWT.BORDER);
		loginPWText.setEchoChar('●');
		//loginIDText.setBounds(loginIDText.getBounds().x+120, loginIDText.getBounds().y, 0, 20);
		//rgsAccount.dispose();
		// end Login group
        
		//Quick lunch
		//[DONE]Quick lunch
        tabGroupQuickLaunch = new RibbonGroup(ftHome, "Quick launch");
        // Button
        RibbonButton rbNewCutomer = new RibbonButton(tabGroupQuickLaunch, ImageCache.getImage("olb_picture.gif"), "New customer", RibbonButton.STYLE_TWO_LINE_TEXT | RibbonButton.STYLE_NO_DEPRESS);
        rbNewCutomer.addSelectionListener(new btNewCustomerListener());
        rbNewCutomer.setToolTip(new RibbonTooltip("Add new customer", "System will automatically allocate seat for the customer"));
        RibbonButton rbNewBooking = new RibbonButton(tabGroupQuickLaunch, ImageCache.getImage("olb_picture.gif"), "New booking", RibbonButton.STYLE_TWO_LINE_TEXT | RibbonButton.STYLE_NO_DEPRESS);
        rbNewBooking.addSelectionListener(new btNewBookingListener());
        rbNewBooking.setToolTip(new RibbonTooltip("Add new booking order", "System will automatically preserve the table for this order."));
        //end Quick lunch
        
        //end Home tab
	}

	private void doRefreshFunctionsInUI(){
		doCleanUserLoginInfo();
		//System.out.println("login?"+this.opLogic.isLogined());
		if(this.opLogic.isLogined()){
			userID.dispose();
			userPostion.dispose();
			btLogoutBut.dispose();
			userID = new RibbonButton(userSub, null, "ID : "+this.opLogic.getCurrentOperator().getId()
					, RibbonButton.STYLE_NO_DEPRESS);
			userPostion = new RibbonButton(userSub, null, "Postion : "+this.opLogic.getCurrentOperator().getPosition()
					, RibbonButton.STYLE_NO_DEPRESS);
			btLogoutBut = new RibbonButton(userSub, null, "Logout"
					, RibbonButton.STYLE_NO_DEPRESS);
			btLogoutBut.addSelectionListener(new btLogoutListener());
			this.tabGroupLogin.setVisible(false);
			this.tabGroupAccount.setVisible(true);
			this.tabGroupQuickLaunch.setVisible(true);
			
			/*this.ftOrder.enable();
			this.ftTable.enable();*/
			this.ftOrder.setVisible(true);
			if(opLogic.isAdmin()){
				this.ftTable.setVisible(true);
			}
			doFuckingRefresh();
			//OrderModifyDialog omDialog = new OrderModifyDialog(shell.getShell(), SWT.INHERIT_DEFAULT);
			//omDialog.open();
			
			//System.out.println("Login tabGroupLogin "+this.tabGroupLogin.getVisible());
			//System.out.println("tabGroupAccount Visiable "+this.tabGroupAccount.getVisible());
		}else{
			this.tabGroupLogin.setVisible(true);
			this.tabGroupAccount.setVisible(false);
			this.tabGroupQuickLaunch.setVisible(false);
			this.ftOrder.setVisible(false);
			this.ftTable.setVisible(false);
			/*this.ftOrder.disable();
			this.ftTable.disable();*/
		}
	}

	private void doCleanUserLoginInfo() {
		btLoginBut.setChecked(false);
		btLogoutBut.setChecked(false);
		loginIDText.setText("");
		loginPWText.setText("");
	}

	private void doFuckingRefresh() {
		//System.out.println("shell.getShell().getSize()"+shell.getShell().getSize());
		shell.setSize(shell.getShell().getSize().x+1, shell.getShell().getSize().y);
		shell.redrawContents();
		shell.setSize(shell.getShell().getSize().x-1, shell.getShell().getSize().y);
		shell.redrawContents();
		//System.out.println("shell.getShell().getSize()"+shell.getShell().getSize());
	}
	
	
	class btLoginListener implements SelectionListener{
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {}
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			Operator op = null;
			try {
				op = opLogic.login(loginIDText.getText(), loginPWText.getText());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(opLogic.isLogined()){
				orderLogic.setOperator(op);
				msgBox.setMessage("Welcome back, "+opLogic.getCurrentOperator().getId());
				msgBox.open();
			}else{
				msgBox.setMessage("Login failed, wrong id or password?");
				msgBox.open();
			}
			//System.out.println("login button"+loginIDText.getText());
			
			doRefreshFunctionsInUI();
		}
	}
	class btLogoutListener implements SelectionListener{
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {}
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			try {
				opLogic.logout();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(false==opLogic.isLogined()){
				msgBox.setMessage("Logout success!");
				msgBox.open();
			}else{
				msgBox.setMessage("Something went wrong.....");
				msgBox.open();
			}
			//System.out.println("login button"+loginIDText.getText());
			
			doRefreshFunctionsInUI();
		}
	}
	class btOrderViewListener implements SelectionListener{
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {}
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			doGenerateNewTableView(shell.getShell());
			doGenerateTableColumn(new String[]{"Order","ID","Operator","Table ID","Guest ID","Guest amount"});
			List<Order> orderList = orderLogic.getOrdersOfResturant();
			if(null!=orderList){
				for(Order order:orderList){
					String[] row = new String[]{"",order.getOrderID(),order.getOperatorID()
							,order.getTable().getId(),order.getGusets().getId()
							,order.getGusets().getAmountString()};
					doGenerateTableItem(row);
					/*for(String s: row)
						System.out.print(s);
					System.out.println();*/
				}
				doAddModifyBoxDCListenerToTable("Order");
			}
			
			computeFormSizeForItem(shell.getShell());
			doFuckingRefresh();
		}
	}
	class btBookingViewListener implements SelectionListener{
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {}
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			doGenerateNewTableView(shell.getShell());
			doGenerateTableColumn(new String[]{"Book Order","ID","Operator","Table ID","Guest ID","Guest amout","Book time"});
			List<BookOrder> orderList = orderLogic.getBookOrdersOfResturant();
			if(null!=orderList)
				for(BookOrder order:orderList){
					String[] row = new String[]{"",order.getOrderID(),order.getOperatorID()
							,order.getTable().getId(),order.getGusets().getId()
							,order.getGusets().getId(),order.getBookTime().toString()};
					doGenerateTableItem(row);
					/*for(String s: row)
						System.out.print(s);
					System.out.println();*/
				}
			computeFormSizeForItem(shell.getShell());
			doFuckingRefresh();
		}
	}
	class btWaitingViewListener implements SelectionListener{
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {}
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			doGenerateNewTableView(shell.getShell());
			doGenerateTableColumn(new String[]{"Waiting guest","ID","guest amount","allow to seat alone","addtional infomation"});
			WaitingList re = orderLogic.getWaitingListOfResturant();
			if(null!=re){
				List<Guests> opList = re.getGuestList();
				for(Guests op:opList){
					String[] row = new String[]{"",op.getId(),op.getAmountString(),op.getSeatAloneString().toString(),op.getAddtionalInfo()};
					doGenerateTableItem(row);
					/*for(String s: row)
						System.out.print(s);
					System.out.println();*/
				}
			}
			computeFormSizeForItem(shell.getShell());
			doFuckingRefresh();
		}
	}
	class btTableViewListener implements SelectionListener{
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {}
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			doGenerateNewTableView(shell.getShell());
			doGenerateTableColumn(new String[]{"Table","ID","capacity"});
			List<com.tap.tableordersys.Table> re = orderLogic.getTablesOfResturant();
			if(null!=re)
				for(com.tap.tableordersys.Table op:re){
					String[] row = new String[]{"",op.getId(),op.getCapacityString()};
					doGenerateTableItem(row);
					/*for(String s: row)
						System.out.print(s);
					System.out.println();*/
				}
			computeFormSizeForItem(shell.getShell());
			doFuckingRefresh();
		}
	}
	class btStaffViewListener implements SelectionListener{
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {}
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			doGenerateNewTableView(shell.getShell());
			doGenerateTableColumn(new String[]{"Member","ID","Position"});
			List<Operator> opList = opLogic.getAllOperator();
			if(null!=opList)
				for(Operator op:opList){
					String[] row = new String[]{"",op.getId(),op.getPosition()};
					doGenerateTableItem(row);
					/*for(String s: row)
						System.out.print(s);
					System.out.println();*/
				}
			computeFormSizeForItem(shell.getShell());
			doFuckingRefresh();
		}
	}
	class btAddCustomerListener implements SelectionListener{
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {}
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			
		}
	}
	class btAddTableListener implements SelectionListener{
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {}
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			TestBox ntd = new TestBox();
			ntd.open();
		}
	}
	class btNewCustomerListener implements SelectionListener{
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {}
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			NewCustomerBox ntd = new NewCustomerBox(orderLogic);
			ntd.open();
		}
	}
	class btNewBookingListener implements SelectionListener{
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {}
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			NewBookingBox ntd = new NewBookingBox(orderLogic);
			ntd.open();
		}
	}
	class btModifyOrderListener implements SelectionListener{
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {}
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			NewBookingBox ntd = new NewBookingBox(orderLogic);
			ntd.open();
		}
	}
	/**
	 * @author Andrew
	 */
	class tiModifyBoxListener implements MouseListener{
		@Override
		public void mouseDoubleClick(MouseEvent e) {
			System.err.println("Double clicked!");
			TableItem ti = tCursor.getRow();
			OrderModifyBox ntd = new OrderModifyBox(new Order(ti.getText(1)));
			ntd.open();
		}
		@Override
		public void mouseDown(MouseEvent e) {}
		@Override
		public void mouseUp(MouseEvent e) {}
	}
}
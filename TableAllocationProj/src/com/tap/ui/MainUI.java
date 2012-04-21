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

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
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

public class MainUI {

    /**
     * @param args
     */
    public void show() {
        Display display = new Display();
        final RibbonShell shell = new RibbonShell(display, SWT.INHERIT_DEFAULT);
        shell.setButtonImage(ImageCache.getImage("desk_32x32.ico"));
        //Shell shell = new Shell(display);
        
        shell.setText("Table Allocation Prgram");
        shell.setSize(800, 500);
        
        //shell.getShell().setLayout(new FillLayout());
        /*shell.getShell().setLayout(new FlowLayout(swing2swt.layout.FlowLayout.CENTER, 5, 5));*/
        //Text text = new Text(shell.getShell(), SWT.TOP);

        //System.out.println(shell.getShell().getLayout());
        //Layout layout = shell.getShell().getLayout();
        
        //shell.getShell().setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        Composite mainComposite = new Composite(shell.getShell(), SWT.BORDER);

        mainComposite.setBackground(new Color(null, 200, 200, 200));
        mainComposite.setSize(600, 700);
        //mainComposite.setBounds(1, 1, 100, 700);
        mainComposite.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        //mainComposite.setLayout(new FillLayout(SWT.BORDER));
        //mainComposite.setLayout(null);
        
        doGenerateFormView(mainComposite.getShell());
        
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
        RibbonTab ftHome = new RibbonTab(ftf, "Home");
        RibbonTab ftOrder = new RibbonTab(ftf, "Ordering");
        RibbonTab ftTable = new RibbonTab(ftf, "Table, Staff and History");
        
        // Tooltip
        RibbonTooltip toolTip = new RibbonTooltip("Some Action Title", "This is content text that\nsplits over\nmore than one\nline\n\\b\\c255000000and \\xhas \\bdifferent \\c000000200look \\xand \\bfeel.", ImageCache.getImage("tooltip.jpg"), ImageCache.getImage("questionmark.gif"), "Press F1 for more help"); 

        // Group

        doGenerateHomeTab(ftHome);
              
        doGenerateOrderingTab(ftOrder);
        
        doGenerateTableStaffHistTab(ftTable);
        
        
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
		//scrolledComposite.setAlwaysShowScrollBars(true);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setSize(scrolledComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.setBackground(new Color(null, 250, 250, 250));
		scrolledComposite.setSize(640, 460);
		scrolledComposite.setBounds(0, 0, 640, 460);
		scrolledComposite.setLayout(new FlowLayout());*/
		
		 final TableViewer tableViewer = new TableViewer(shell, SWT.CHECK|SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER|SWT.V_SCROLL|SWT.H_SCROLL);
	        
		 	Table table = tableViewer.getTable();
	        table.setLinesVisible(true);
	        table.setHeaderVisible(true);
	        table.setBounds(97, 79, 373, 154);

	        final TableColumn newColumnTableColumn = new TableColumn(table, SWT.NONE);
	        newColumnTableColumn.setWidth(80);
	        newColumnTableColumn.setText("ID");

	        final TableColumn newColumnTableColumn_1 = new TableColumn(table, SWT.NONE);
	        newColumnTableColumn_1.setWidth(200);
	        newColumnTableColumn_1.setText("Order amount");
	        
	        final TableColumn newColumnTableColumn_2 = new TableColumn(table, SWT.NONE);
	        newColumnTableColumn_2.setWidth(200);
	        newColumnTableColumn_2.setText("Booked amount");

	        final TableColumn newColumnTableColumn_3 = new TableColumn(table, SWT.NONE);
	        newColumnTableColumn_3.setWidth(150);
	        newColumnTableColumn_3.setText("Capacity");

	        final TableColumn newColumnTableColumn_4 = new TableColumn(table, SWT.NONE);
	        newColumnTableColumn_4.setWidth(150);
	        newColumnTableColumn_4.setText("Guest amount");
        //doAddTableToForm(scrolledComposite);
		// end Oder Composite
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
        new RibbonGroupSeparator(rgTable);
        RibbonButtonGroup tableSub = new RibbonButtonGroup(rgTable);
        RibbonButton rbNewTable = new RibbonButton(tableSub, ImageCache.getImage("olb_picture.gif"), "New Table", RibbonButton.STYLE_NO_DEPRESS);
        RibbonButton rbModifyTable = new RibbonButton(tableSub, ImageCache.getImage("olb_picture.gif"), "Modify Table", RibbonButton.STYLE_NO_DEPRESS);
        RibbonButton rbDelTable = new RibbonButton(tableSub, ImageCache.getImage("olb_picture.gif"), "Delete Table", RibbonButton.STYLE_NO_DEPRESS);
        //end Booking group
        //Group Staff Management
        RibbonGroup rgStaff = new RibbonGroup(ftTable, "Staff Management");
        //new Image(null, "newOder.gif")
        RibbonButton rbStaffView = new RibbonButton(rgStaff, ImageCache.getImage("olb_picture.gif"), "Staff View", RibbonButton.STYLE_NO_DEPRESS);
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
        //[TODO] Ordering
        //Order group 
        RibbonGroup rgOrder = new RibbonGroup(ftOrder, "Current");
        //new Image(null, "newOder.gif")
        RibbonButton rbNewOrderView = new RibbonButton(rgOrder, ImageCache.getImage("olb_picture.gif"), "Order View", RibbonButton.STYLE_NO_DEPRESS);
        new RibbonGroupSeparator(rgOrder);
        RibbonButtonGroup orderSub = new RibbonButtonGroup(rgOrder);
        RibbonButton rbNewOrder = new RibbonButton(orderSub, ImageCache.getImage("olb_picture.gif"), "New Order", RibbonButton.STYLE_NO_DEPRESS);
        RibbonButton rbModifyOrder = new RibbonButton(orderSub, ImageCache.getImage("olb_picture.gif"), "Modify Order", RibbonButton.STYLE_NO_DEPRESS);
        RibbonButton rbDelOrder = new RibbonButton(orderSub, ImageCache.getImage("olb_picture.gif"), "Delete Order", RibbonButton.STYLE_NO_DEPRESS);
        //end Order group
        
        //Booking group 
        RibbonGroup rgBook = new RibbonGroup(ftOrder, "Booking");
        //new Image(null, "newOder.gif")
        RibbonButton rbNewBookView = new RibbonButton(rgBook, ImageCache.getImage("olb_picture.gif"), "Booking View", RibbonButton.STYLE_NO_DEPRESS);
        new RibbonGroupSeparator(rgBook);
        RibbonButtonGroup bookSub = new RibbonButtonGroup(rgBook);
        RibbonButton rbNewBook = new RibbonButton(bookSub, ImageCache.getImage("olb_picture.gif"), "New Book", RibbonButton.STYLE_NO_DEPRESS);
        RibbonButton rbModifyBook = new RibbonButton(bookSub, ImageCache.getImage("olb_picture.gif"), "Modify Book", RibbonButton.STYLE_NO_DEPRESS);
        RibbonButton rbDelBook = new RibbonButton(bookSub, ImageCache.getImage("olb_picture.gif"), "Delete Book", RibbonButton.STYLE_NO_DEPRESS);
        //end Booking group
        
        //Wating List group 
        RibbonGroup rgWait = new RibbonGroup(ftOrder, "Wating List");
        //new Image(null, "newOder.gif")
        RibbonButton rbNewWaitView = new RibbonButton(rgWait, ImageCache.getImage("olb_picture.gif"), "Waiting View", RibbonButton.STYLE_NO_DEPRESS);
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
        //[TODO] Account UI
        RibbonGroup tbGroup = new RibbonGroup(ftHome, "Account");
        //Image loginImage = new Image(null, "image/loginButton.png");
        Image userImage = new Image(null, "image/user.png");
        //RibbonButton rbLoginBut = new RibbonButton(tbGroup, loginImage, null, RibbonButton.STYLE_TWO_LINE_TEXT | RibbonButton.STYLE_NO_DEPRESS);//RibbonButton.STYLE_ARROW_DOWN_SPLIT);
        //rbLoginBut.setToolTip(new RibbonTooltip("Login", "Click here to login"));
		RibbonButton userImg = new RibbonButton(tbGroup, userImage, null, 0);
		userImg.setEnabled(false);
		RibbonGroupSeparator rgsAccount = new RibbonGroupSeparator(tbGroup);
		RibbonButtonGroup userSub = new RibbonButtonGroup(tbGroup);
		new Text(tbGroup, SWT.BORDER);
		RibbonButton userID = new RibbonButton(userSub, null, "ID : 0903000039", RibbonButton.STYLE_NO_DEPRESS);
		RibbonButton userPostion = new RibbonButton(userSub, null, "Postion : Admin", RibbonButton.STYLE_NO_DEPRESS);
		//rgsAccount.dispose();
		/*userImg.dispose();
		userID.dispose();
		userPostion.dispose();*/
		// end toolbar group
		
		// Login group
        //[TODO] Login UI
        RibbonGroup tbGroupLogin = new RibbonGroup(ftHome, "Login");
        GridLayout glLogin = new GridLayout(1, false);
        glLogin.marginHeight = 7;
        glLogin.marginLeft = 170;
        glLogin.marginRight = 0;
        glLogin.verticalSpacing = 1;
        glLogin.horizontalSpacing = 0;
        glLogin.marginBottom = 7;
        tbGroupLogin.setLayout(glLogin);
        Image loginImage = new Image(null, "image/loginButton.png");
        RibbonButton rbLoginBut = new RibbonButton(tbGroupLogin, loginImage, null, RibbonButton.STYLE_TWO_LINE_TEXT | RibbonButton.STYLE_NO_DEPRESS);//RibbonButton.STYLE_ARROW_DOWN_SPLIT);
        rbLoginBut.setToolTip(new RibbonTooltip("Login", "Click here to login"));
		userImg.setEnabled(false);
		RibbonGroupSeparator rgsLogin = new RibbonGroupSeparator(tbGroupLogin);
		RibbonButtonGroup loginSub = new RibbonButtonGroup(tbGroupLogin);
		RibbonButton takeSpaceID = new RibbonButton(loginSub, null, "ID :                         ", RibbonButton.STYLE_NO_DEPRESS);
		RibbonButton takeSpacePW = new RibbonButton(loginSub, null, "Password :                   ", RibbonButton.STYLE_NO_DEPRESS);
		takeSpaceID.setEnabled(false);
		takeSpacePW.setEnabled(false);
		Text loginIDText = new Text(tbGroupLogin, SWT.BORDER);
		Text loginPWText = new Text(tbGroupLogin, SWT.BORDER);
		//loginIDText.setBounds(loginIDText.getBounds().x+120, loginIDText.getBounds().y, 0, 20);
		//rgsAccount.dispose();
		// end Login group
        
		//Quick lunch
		//[XXX]Quick lunch
        RibbonGroup ftg = new RibbonGroup(ftHome, "Quick lunch");
        // Button
        RibbonButton rbNewCutomer = new RibbonButton(ftg, ImageCache.getImage("olb_picture.gif"), "New customer", RibbonButton.STYLE_TWO_LINE_TEXT | RibbonButton.STYLE_NO_DEPRESS);
        rbNewCutomer.setToolTip(new RibbonTooltip("Add new customer", "System will automatically allocate seat for the customer"));
        //end Quick lunch
        
        //end Home tab
	}

}
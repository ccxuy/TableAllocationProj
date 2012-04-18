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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

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

//DONE: Make tooltip pulling a callback if the set tooltip is null, much easier than defining one for each button (or change so only text/image is set and the class is created by us
//DONE: Toolbars can have 3 rows in compressed mode so we should support it
//DONE: Mouse resize cursor on left side in group area doesn't change past it's bounds
//DONE: [HIGH]: Maximized shell covers windows taskbar (get active display's bounds and set it to that and fake a maximized boolean?)
//DONE: [HIGH]: Tooltip on big button and toolbar and arrow button to the right of it
//DONE: [HIGH]: Doubleclicking the toolbar in the shell and some other stuff causes maximize events, which is wrong

//TODO [LOW]: Couldn't Toolbar extend what all others do? would save some code in the onMouseMove etc in RibbonGroup where it checks hover states etc
//TODO [LOW]: A lot of the getBounds() calculations in RibbonGroup could be moved to the respective "parent" of the buttons, such as the toolbar etc, less mess!
//TODO [MEDIUM]: Checkboxes only change checked state on mouse up, not mouse down
//TODO [LOW]: Auto-compress on not-enough-horizontal space (?)
//TODO [MEDIUM]: I don't think buttons on toolbars have centered images, try with something really small, like 4x4 and see where it ends up
//TODO [HIGH]: Action-callbacks on big button click, arrow, toolbar, help button and group buttons too?
//TODO [LOW]: Grayed out toolbar on menubar grays out too on shell-not-active state (ugh, new images?)
//TODO [LOW]: Tooltips don't dispose on "ALT" press, maybe listen in the tooltip?
//TODO [HIGH]: Button accelerators (also go into tooltips), we need a global key listener or something
//               Also draw the same little cute boxes Office does to show them        
//TODO [LOW]: Option to create a RibbonShell without a toolbar on the menu
//TODO [MEDIUM+]: Right click shell menu.. how how how? (fake shell off-screen and make ours a simple modal? hmm, weird probably)
//TODO [LOW]: Semi Fixed, redraws less. Resize shouldn't redraw the entire shell, just the new part (seems impossible to intercept)
//TODO [HIGH+]: Color schemes, also, images for toolbar corners on shell need to be drawn by hand I think...

public class MainUI {

    /**
     * @param args
     */
    public void show() {
        Display display = new Display();
        final RibbonShell shell = new RibbonShell(display);
        shell.setButtonImage(ImageCache.getImage("desk_32x32.ico"));
        //Shell shell = new Shell(display);
        
        shell.setText("Table Allocation Prgram");
        shell.setSize(800, 500);
        
        Text text = new Text(shell.getShell(), SWT.TOP);
        
        QuickAccessShellToolbar mtb = shell.getToolbar();
        RibbonButton mtbtb1 = new RibbonButton(mtb, ImageCache.getImage("gear_ok_16.gif"), null, SWT.NONE);
        shell.setBigButtonTooltip(new RibbonTooltip("Account", "Click here to login or logout your account"));
        mtb.setArrowTooltip(new RibbonTooltip("No more things", ""));
        
        Menu shellMenu = shell.getBigButtonMenu();
        MenuItem miLogin = new MenuItem(shellMenu, SWT.POP_UP);
        miLogin.setText("Login");
        MenuItem miLogout = new MenuItem(shellMenu, SWT.POP_UP);
        miLogout.setText("Logout");
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
        RibbonTab ftTable = new RibbonTab(ftf, "Table View");
        RibbonTab ftOrder = new RibbonTab(ftf, "Order View");
        RibbonTab ftBook = new RibbonTab(ftf, "Book View");
        RibbonTab ftWatingList = new RibbonTab(ftf, "Wating List View");
        RibbonTab ftStaffMgnt = new RibbonTab(ftf, "Staff management View");
        RibbonTab ftHistory = new RibbonTab(ftf, "History View");
        
        // Tooltip
        RibbonTooltip toolTip = new RibbonTooltip("Some Action Title", "This is content text that\nsplits over\nmore than one\nline\n\\b\\c255000000and \\xhas \\bdifferent \\c000000200look \\xand \\bfeel.", ImageCache.getImage("tooltip.jpg"), ImageCache.getImage("questionmark.gif"), "Press F1 for more help"); 

        // Group

        // toolbar group
        RibbonGroup tbGroup = new RibbonGroup(ftHome, "Toolbar Here");
        Text rbgTextID = new Text(tbGroup, 1);
        rbgTextID.setText("LoginID : ");
        rbgTextID.setVisible(true);
        RibbonToolbar toolbar = new RibbonToolbar(tbGroup, RibbonToolbar.STYLE_BORDERED, 2);
        RibbonToolbarGrouping rtg = new RibbonToolbarGrouping(toolbar, 1);        
        RibbonToolbarGrouping rtg2 = new RibbonToolbarGrouping(toolbar, 1);
        RibbonToolbarGrouping rtg3 = new RibbonToolbarGrouping(toolbar, 1);
        
        RibbonToolbarGrouping rtg4 = new RibbonToolbarGrouping(toolbar, 2);
        
        RibbonButton rbTb1 = new RibbonButton(rtg, ImageCache.getImage("books_16.gif"), null, RibbonButton.STYLE_ARROW_DOWN_SPLIT | RibbonButton.STYLE_TOGGLE);
        RibbonButton rbTb2 = new RibbonButton(rtg2, ImageCache.getImage("gear_ok_16.gif"), null, SWT.NONE);
        RibbonButton rbTb3 = new RibbonButton(rtg2, ImageCache.getImage("gantt_16.gif"), null, RibbonButton.STYLE_ARROW_DOWN);
        RibbonButton rbTb4 = new RibbonButton(rtg3, ImageCache.getImage("gantt_16.gif"), null, RibbonButton.STYLE_ARROW_DOWN_SPLIT);
        
        RibbonButton rbTb5 = new RibbonButton(rtg4, ImageCache.getImage("enabled_small.gif"), null, RibbonButton.STYLE_NO_DEPRESS);
        RibbonButton rbTb6 = new RibbonButton(rtg4, ImageCache.getImage("selection_recycle_16.gif"), null, RibbonButton.STYLE_ARROW_DOWN_SPLIT);
        
        rbTb4.setEnabled(false);
        // end toolbar group
        
        RibbonGroup ftg = new RibbonGroup(ftHome, "Account", toolTip);
        // Button
        RibbonButton rbLogin = new RibbonButton(ftg, ImageCache.getImage("olb_picture.gif"), "Login", RibbonButton.STYLE_TWO_LINE_TEXT | RibbonButton.STYLE_NO_DEPRESS);//RibbonButton.STYLE_ARROW_DOWN_SPLIT);
        RibbonButton rbLogout = new RibbonButton(ftg, ImageCache.getImage("olb_picture.gif"), "Logout", RibbonButton.STYLE_TWO_LINE_TEXT | RibbonButton.STYLE_NO_DEPRESS);
        RibbonButton rb2 = new RibbonButton(ftg, ImageCache.getImage("olb_picture.gif"), "I'm split\ntoggle", RibbonButton.STYLE_ARROW_DOWN_SPLIT | RibbonButton.STYLE_TOGGLE | RibbonButton.STYLE_TWO_LINE_TEXT);
        rb2.setBottomOrRightToolTip(toolTip);
        MenuItem test = new MenuItem(rb2.getMenu(), SWT.POP_UP);
        test.setText("Testing a menu");

        rb2.addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(SelectionEvent e) {
                
            }

            public void widgetSelected(SelectionEvent e) {
                final RibbonButton rb = (RibbonButton) e.data;
                if (rb.isTopSelected()) 
                    System.err.println("Top clicked");
                else {
                    System.err.println("Bottom clicked");
                    rb.showMenu();                    
                }
            }            
        });
        // Empty group
        RibbonGroup cb = new RibbonGroup(ftTable, "Checkboxes", toolTip);
        RibbonButtonGroup cbg = new RibbonButtonGroup(cb);
        
        RibbonCheckbox rc = new RibbonCheckbox(cbg, "I'm checked", SWT.NONE);
        rc.setSelected(true);
        new RibbonCheckbox(cbg, "I'm not", SWT.NONE);
        RibbonCheckbox rc3 = new RibbonCheckbox(cbg, "I'm disabled", SWT.NONE);
        new RibbonCheckbox(cbg, "Also a checkbox", SWT.NONE);
        new RibbonCheckbox(cbg, "Many, aren't we?", SWT.NONE);
        rc3.setEnabled(false);
        RibbonTooltip cbTip = new RibbonTooltip("Title", "Checkbox description\n\\b\\c255000000Some bold and red \\xand\nSome not!", null, ImageCache.getImage("questionmark.gif"), "Press F1 for more help");
        rc3.setToolTip(cbTip);

        rbLogin.setToolTip(toolTip);
        //TODO: Check when a dialog opens as a result of clicking this to see if this button does not redraw for some reason or think it's still selected
        new RibbonButton(ftg, ImageCache.getImage("olb_picture.gif"), "I am longer and do not depress", RibbonButton.STYLE_NO_DEPRESS);

        RibbonGroup ftg2 = new RibbonGroup(ftTable, "Group 1");
        RibbonButton rb1 = new RibbonButton(ftg2, ImageCache.getImage("olb_picture2.gif"), "Button 1", SWT.NONE);
        //RibbonButton rb2 = new RibbonButton(ftg2, ImageCache.getImage("olb_picture3.gif"), "Button 2", SWT.NONE);

        RibbonGroup ftg3 = new RibbonGroup(ftTable, "Group 2");
        RibbonButton rb3 = new RibbonButton(ftg3, ImageCache.getImage("olb_picture4.gif"), "Button 3", SWT.NONE);
        RibbonButton rb4 = new RibbonButton(ftg3, ImageCache.getImage("olb_picture6.gif"), "Button 4", SWT.NONE);
        rb4.setToolTip(toolTip);

        ButtonSelectGroup group = new ButtonSelectGroup();
        
        // native controls example
        RibbonGroup ftg4 = new RibbonGroup(ftTable, "Native");
        GridLayout gl = new GridLayout(1, false);
        gl.marginHeight = 7;
        gl.marginLeft = 0;
        gl.marginRight = 0;
        gl.verticalSpacing = 1;
        gl.horizontalSpacing = 0;
        gl.marginBottom = 7;
        ftg4.setLayout(gl);
        Combo foo = new Combo(ftg4, SWT.READ_ONLY);
        foo.setText("Testing");
        foo.add("Testing 2");
        foo.add("Testing 3");
        foo.add("Testing 4");
        Button b = new Button(ftg4, SWT.PUSH);
        b.setText("Test");
                
        // create sub button containing 3 buttons inside it
        new RibbonGroupSeparator(ftg);
        
        RibbonButtonGroup sub = new RibbonButtonGroup(ftg);
        RibbonButton sub1 = new RibbonButton(sub, ImageCache.getImage("enabled_small.gif"), ImageCache.getImage("disabled_small.gif"), "Disabled", SWT.NONE);
        sub1.setEnabled(false);
        new RibbonCheckbox(sub, "I'm mixed in", SWT.NONE);

        // make arrow down
        RibbonButton rb5 = new RibbonButton(sub, ImageCache.getImage("olb_small2.gif"), "I am toggle split", RibbonButton.STYLE_TOGGLE | RibbonButton.STYLE_ARROW_DOWN_SPLIT);
        RibbonButton rb6 = new RibbonButton(sub, ImageCache.getImage("olb_small3.gif"), "I am a quite long button", SWT.NONE);
        RibbonButton rb7 = new RibbonButton(sub, ImageCache.getImage("olb_small3.gif"), "I split normal", RibbonButton.STYLE_ARROW_DOWN_SPLIT);
        RibbonButton rb8 = new RibbonButton(sub, ImageCache.getImage("olb_small3.gif"), "I am arrowed", RibbonButton.STYLE_ARROW_DOWN);

        MenuItem test2 = new MenuItem(rb8.getMenu(), SWT.POP_UP);
        test2.setText("Testing an arrow down menu");

        rb8.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
            }

            public void widgetSelected(SelectionEvent e) {
                final RibbonButton rb = (RibbonButton) e.data;
                rb.showMenu();                    
            }
            
        });
        
        rb1.setButtonSelectGroup(group);
        rb2.setButtonSelectGroup(group);
        rb3.setButtonSelectGroup(group);
        rb4.setButtonSelectGroup(group);
        rb5.setButtonSelectGroup(group);
        rb6.setButtonSelectGroup(group);
                    
        Utils.centerDialogOnScreen(shell.getShell());

        shell.open();
        
        while (!shell.isDisposed ()) {
            if (!display.readAndDispatch ()) display.sleep ();
        }
        display.dispose ();
    }

}
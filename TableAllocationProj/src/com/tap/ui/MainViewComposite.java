/**
 * 
 */
package com.tap.ui;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;

import com.hexapixel.widgets.ribbon.RibbonGroup;
import com.hexapixel.widgets.ribbon.RibbonShell;

/**
 * @author Andrew
 *
 */
public class MainViewComposite extends ScrolledComposite implements ControlListener{
	private List<Label> forms;
	Composite parent;

	public MainViewComposite(Composite parent, int style){
		this(parent, style, null);
	}
	public MainViewComposite(final Composite parent, int style, RibbonShell shell){
		super(parent, style | SWT.DOUBLE_BUFFERED);
		
		forms = new LinkedList<Label>();
		this.parent = parent;
		setLayout(new MainViewLayout());
		this.addListener(SWT.Resize, new Listener() {

	        public void handleEvent(Event event) {

	            Rectangle rect = parent.getClientArea();

	            int top = 1;
	            int left = 0;
	            int spacer = 2;
	            Control [] items = parent.getChildren();

	            for (int i = 0; i < items.length; i++) {
	                Control item = items[i];
	                item.setLocation(left, top);
	                //item.setSize(parent.getBounds().width, item.getSize().y);
	                top += item.getBounds().height;
	                System.out.println(i+" top "+top);
	            }
	            
	            Control item = items[0];
	            item.setSize(parent.getBounds().width, item.getSize().y);

	        }

	    });
		layout(true);
	}
	
	@Override
	public Rectangle getBounds() {
		checkWidget();
		Rectangle bounds = parent.getBounds();
		bounds.height -= 149;
			
		return bounds;
	}

	@Override
	public Point getSize() {
		/*Rectangle bounds = getBounds();
		return new Point(bounds.width, bounds.height);*/
		checkWidget();
		Rectangle bounds = getBounds();
		return new Point(bounds.width, bounds.height);
	}
	
	public void controlResized(ControlEvent e) {
		Rectangle rect = parent.getClientArea();

        int top = 1;
        int left = 0;
        int spacer = 2;
        Control [] items = parent.getChildren();

        for (int i = 0; i < items.length; i++) {
            Control item = items[i];
            item.setLocation(left, top);
            //item.setSize(parent.getBounds().width, item.getSize().y);
            top += item.getBounds().height;
        }
		
		//not needed 
		// redrawContents();		
	}

	@Override
	public void controlMoved(ControlEvent arg0) {
		
	}
	
	@Override
	public void dispose() {
		for (int i = 0; i < forms.size(); i++) 
			forms.get(i).dispose();
		
		forms.clear();

		super.dispose();
	}
	
	class MainViewLayout extends Layout{
		private Point sizes [];
		
		private void init(Composite composite) {
			Control [] children = composite.getChildren();
			sizes = new Point[children.length];
			for (int i = 0; i < children.length; i++) {
				sizes[i] = children[i].computeSize(SWT.DEFAULT, SWT.DEFAULT);
			}
		}
		
		@Override
		protected Point computeSize(Composite composite, int hint, int hint2, boolean flushCache) {
			if (flushCache) {
				init(composite);
			}
			return getSize();
		}

		@Override
		protected void layout(final Composite aComposite, boolean flushCache) {
			int top = 1;
            int left = 0;
            int spacer = 2;
            Control [] items = aComposite.getChildren();

            for (int i = 0; i < items.length; i++) {
                Control item = items[i];
                item.setBounds(left, top, parent.getBounds().width, parent.getBounds().height);
                top += item.getBounds().height;
                if (item instanceof RibbonGroup) {
                	RibbonGroup fg = (RibbonGroup) item;
                	if (!fg.isVisible())
                		continue;
                	
                    Rectangle groupBounds = fg.getBounds();
                    
                    item.setBounds(left, top, groupBounds.width, groupBounds.height);
                    left += groupBounds.width + spacer;                	
                }
            }
		}
		
	}

}

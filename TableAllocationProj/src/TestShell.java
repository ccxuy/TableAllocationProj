import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Canvas;
import swing2swt.layout.FlowLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;
import swing2swt.layout.BoxLayout;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.Realm;


public class TestShell extends Shell {
	private DataBindingContext m_bindingContext;
	private Table table;
	private TableItem tableItem;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		Display display = Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				try {
					Display display = Display.getDefault();
					TestShell shell = new TestShell(display);
					shell.open();
					shell.layout();
					while (!shell.isDisposed()) {
						if (!display.readAndDispatch()) {
							display.sleep();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public TestShell(Display display) {
		super(display, SWT.SHELL_TRIM);
		setLayout(null);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(3, 3, 300, 200);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
		tblclmnId.setWidth(100);
		tblclmnId.setText("id");
		
		TableColumn tblclmnName = new TableColumn(table, SWT.NONE);
		tblclmnName.setWidth(100);
		tblclmnName.setText("name");
		
		tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText("123123");
		
		TableItem tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText(new String[] {"123", "312", "1123", "32"});
		
		TableColumn tblclmnSome = new TableColumn(table, SWT.NONE);
		tblclmnSome.setWidth(100);
		tblclmnSome.setText("some");
		scrolledComposite.setContent(table);
		scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		m_bindingContext = initDataBindings();
		
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(450, 300);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue tableItemObserveTextObserveWidget = SWTObservables.observeText(tableItem);
		IObservableValue getIMETextObserveValue = PojoObservables.observeValue(getIME(), "text");
		bindingContext.bindValue(tableItemObserveTextObserveWidget, getIMETextObserveValue, null, null);
		//
		return bindingContext;
	}
}

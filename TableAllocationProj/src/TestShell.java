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


public class TestShell extends Shell {
	private Text text;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
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

	/**
	 * Create the shell.
	 * @param display
	 */
	public TestShell(Display display) {
		super(display, SWT.SHELL_TRIM);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setImage(SWTResourceManager.getImage(TestShell.class, "/tooltip.jpg"));
		lblNewLabel.setText("New Label");
		
		text = new Text(this, SWT.BORDER);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		Canvas canvas = new Canvas(this, SWT.NONE);
		
		Label label = new Label(this, SWT.NONE);
		label.setText("21");
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setText("41");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(groupLayout.createSequentialGroup()
							.add(5)
							.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
								.add(groupLayout.createSequentialGroup()
									.add(lblNewLabel)
									.add(5)
									.add(text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.add(5)
									.add(table, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.add(5)
									.add(canvas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.add(label_1)))
						.add(groupLayout.createSequentialGroup()
							.add(36)
							.add(label, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(131, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(5)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(groupLayout.createSequentialGroup()
							.add(23)
							.add(lblNewLabel))
						.add(groupLayout.createSequentialGroup()
							.add(20)
							.add(text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.add(groupLayout.createSequentialGroup()
							.add(9)
							.add(table, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.add(canvas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.add(17)
					.add(label)
					.add(15)
					.add(label_1))
		);
		setLayout(groupLayout);
		createContents();
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
}

import org.eclipse.swt.SWT;

import com.tap.bizlogic.OperatorLogic;
import com.tap.ui.OrderModifyDialog;
import com.tap.usersys.Operator;


public class TestMain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// [TODO] Auto-generated method stub
		OperatorLogic opLogic = new OperatorLogic();
		System.out.println( opLogic.register("", "", OperatorLogic.PO_ADMIN));
		System.out.println( opLogic.login("admin", "admin").toString() );

		
	}

}

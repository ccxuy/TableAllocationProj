import com.tap.bizlogic.OperatorLogic;
import com.tap.usersys.Operator;


public class TestMain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// [TODO] Auto-generated method stub
		System.out.println( OperatorLogic.register("admin", "admin", OperatorLogic.PO_ADMIN));
		System.out.println( OperatorLogic.login("admin", "admin").toString() );

	}

}

import com.tap.bizlogic.OperatorLogic;
import com.tap.usersys.Operator;


public class TestMain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// [TODO] Auto-generated method stub
		OperatorLogic opLogic = new OperatorLogic();
		System.out.println( opLogic.register("staff", "staff", OperatorLogic.PO_STAFF));
		System.out.println( opLogic.login("admin", "admin").toString() );

	}

}

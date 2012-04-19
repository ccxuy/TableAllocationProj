import com.tap.bizlogic.OperatorLogic;
import com.tap.usersys.Operator;


public class TestMain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// [TODO] Auto-generated method stub
		System.out.println( OperatorLogic.register("123", "123", OperatorLogic.PO_ADMIN));
		System.out.println( OperatorLogic.login("123", "1234").toString() );

	}

}

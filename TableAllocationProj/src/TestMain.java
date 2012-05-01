import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import hirondelle.date4j.DateTime;

import org.eclipse.swt.SWT;

import com.tap.bizlogic.OperatorLogic;
import com.tap.bizlogic.OrderLogic;
import com.tap.bizlogic.RestaurantLogic;
import com.tap.locinfo.Setting;
import com.tap.tableordersys.BookOrder;
import com.tap.tableordersys.Guests;
import com.tap.tableordersys.Order;
import com.tap.tableordersys.Restaurant;
import com.tap.tableordersys.Table;
import com.tap.ui.OrderDialog;
import com.tap.ui.OrderModifyDialog;
import com.tap.usersys.Operator;


public class TestMain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		OperatorLogic opLogic = new OperatorLogic();
		/*Operator o = opLogic.login("admin", "admin");
		OrderLogic orderLogic = new OrderLogic(o);*/
		
		/*System.out.println( opLogic.register("", "", OperatorLogic.PO_ADMIN));
		opLogic.register("admin", "admin", OperatorLogic.PO_ADMIN);
		opLogic.register("staff", "staff", OperatorLogic.PO_STAFF);
		RestaurantLogic.createRestaurant(Setting.resturantName);//*/
		RestaurantLogic.createRestaurant(Setting.resturantName);
		Operator op = opLogic.login("admin", "admin");

		Restaurant r = RestaurantLogic.getRestaurant(Setting.resturantName);
		RestaurantLogic.saveRestauant(r);
		
		
	}

}

import com.tap.bizlogic.OperatorLogic;
import com.tap.bizlogic.RestaurantLogic;
import com.tap.locinfo.Setting;
import com.tap.tableordersys.Restaurant;
import com.tap.usersys.Operator;


public class InitDefaultData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OperatorLogic opLogic = new OperatorLogic();
		opLogic.register("admin", "admin", OperatorLogic.PO_ADMIN);
		RestaurantLogic.createRestaurant(Setting.resturantName);

		Restaurant r = RestaurantLogic.getRestaurant(Setting.resturantName);
		RestaurantLogic.saveRestauant(r);
	}

}

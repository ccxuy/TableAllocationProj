import com.db4o.ObjectContainer;
import com.tap.bizlogic.OperatorLogic;
import com.tap.bizlogic.RestaurantLogic;
import com.tap.datastorage.DataControl;
import com.tap.locinfo.Setting;
import com.tap.tableordersys.Restaurant;
import com.tap.usersys.Operator;


public class Init {

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

import org.eclipse.swt.SWT;

import com.tap.bizlogic.OperatorLogic;
import com.tap.bizlogic.RestaurantLogic;
import com.tap.locinfo.Setting;
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
		// [TODO] Auto-generated method stub
		/*OperatorLogic opLogic = new OperatorLogic();
		System.out.println( opLogic.register("", "", OperatorLogic.PO_ADMIN));
		opLogic.register("admin", "admin", OperatorLogic.PO_ADMIN);
		opLogic.register("staff", "staff", OperatorLogic.PO_STAFF);
		RestaurantLogic.createRestaurant(Setting.resturantName);//*/
		
		/*Operator op = opLogic.login("admin", "admin");

		Restaurant r = RestaurantLogic.getRestaurant(Setting.resturantName);
		r.emptyTableList();
		Table a1Table = new Table("A1",12);
		Guests g = new Guests("guest1","", 10, false);
		Order o =new Order("1", op, a1Table, g);
		System.out.println(o);
		a1Table.addOrder(o);
		a1Table.addOrder(new Order("2", op, a1Table, new Guests("guest2","", 13, true)));
		a1Table.addOrder(new Order("3", op, a1Table, new Guests("3群魂淡","", 4, false)));
		r.addTable(a1Table);
		a1Table.setId("A2");
		r.addTable(a1Table);
		a1Table.setId("A3");
		r.addTable(a1Table);
		RestaurantLogic.saveRestauant(r);*/
		
		
		
		
	}

}

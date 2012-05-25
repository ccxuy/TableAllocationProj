package com.tap.test;
import com.db4o.ObjectContainer;
import com.tap.bizlogic.OperatorLogic;
import com.tap.bizlogic.RestaurantLogic;
import com.tap.datastorage.DataControl;
import com.tap.locinfo.Setting;
import com.tap.tableordersys.Restaurant;
import com.tap.usersys.Operator;


public class Util {

	/**
	 * @param args
	 */
	public static void initData() {
		ObjectContainer db = DataControl.getOCDB(Restaurant.class);
		db.delete(new Object());
		
		OperatorLogic opLogic = new OperatorLogic();
		opLogic.register("admin", "admin", OperatorLogic.PO_ADMIN);
		RestaurantLogic.createRestaurant(Setting.resturantName);

		Restaurant r = RestaurantLogic.getRestaurant(Setting.resturantName);
		RestaurantLogic.saveRestauant(r);
	}

}

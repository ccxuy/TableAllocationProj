package com.tap.bizlogic;

import java.util.List;

import com.db4o.ObjectContainer;
import com.tap.datastorage.DataControl;
import com.tap.tableordersys.Order;
import com.tap.tableordersys.Restaurant;
import com.tap.usersys.Operator;

public class RestaurantLogic {
	public static boolean createRestaurant(String restaurantName){
		ObjectContainer db = DataControl.getOCDB(Restaurant.class);
		try{
			Restaurant r = new Restaurant(restaurantName);
			List<Restaurant> oplist = db.queryByExample( r );
			if(0<oplist.size()){
				//throw new Exception("Database have dupilicate entities for login user!");
				System.err.println("Exist following restaurant:");
				for(Restaurant o:oplist){
					System.err.println("\t"+o);
				}
				return false;
			}else{
				System.out.println("New: "+r);
				db.store(r);
				return true;
			}
		}finally{
			db.close();
		}
	}
	public static boolean saveRestauant(Restaurant r){
		ObjectContainer db = DataControl.getOCDB(Restaurant.class);
		try{
			List<Restaurant> oplist = db.queryByExample( new Restaurant(r.getName()) );
			if(1==oplist.size()){
				//throw new Exception("Database have dupilicate entities for login user!");
				System.out.println("saved: "+r);
				Restaurant restaurant = oplist.get(0);
				restaurant.copy(r);
				db.store(restaurant);
				return true;
			}else if(0==oplist.size()){
				System.err.println("Error in saveRestauant : Restaurant not found!");
				return false;
			}else{
				System.err.println("Error in saveRestauant");
				return false;
			}
		}finally{
			db.close();
		}
	}
	public static Restaurant getRestaurant(String restaurantName){
		ObjectContainer db = DataControl.getOCDB(Restaurant.class);
		try{
			List<Restaurant> oplist = db.queryByExample( new Restaurant(restaurantName) );
			if(1==oplist.size()){
				return oplist.get(0);
			}else{
				System.err.println("Error in getRestaurant: "+restaurantName+" found:"+oplist.size());
				return null;
			}
		}finally{
			db.close();
		}
	}
}

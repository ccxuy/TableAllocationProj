package com.workshop.clould.test;

import com.workshop.cloud.db.dao.impl.RestaurantDaoImpl;
import com.workshop.cloud.vo.Restaurant;

public class test_db_restaurant {
	/*test plan:
	 * 1.restaurant name检查duplicated问题
	 * 
	 */
	
	public static void main(String[] args) {
		testAdd();
		//testDelete();
		testList();
	}
	public static void testAdd(){
		RestaurantDaoImpl rdi = new RestaurantDaoImpl();
		Restaurant obj = new Restaurant("33");
		System.out.print(rdi.addRestaurant(obj));
	}
	
	public static void testList(){
		RestaurantDaoImpl rdi = new RestaurantDaoImpl();
		System.out.print(rdi.getRestaurantList());
	}
	public static void testDelete(){
		RestaurantDaoImpl rdi = new RestaurantDaoImpl();
		System.out.print(rdi.deleteRestaurantByID(1));
	}
}

package com.workshop.clould.test;

import com.workshop.cloud.db.dao.impl.BookOrderDaoImpl;
import com.workshop.cloud.vo.BookOrder;

public class test_db_bookorder {
	public static void main(String[] args) {
		testAddOrder();
		//testGetOrderList();
		testDeleteOrderByOid();
	}
	
	public static void testAddOrder(){
		BookOrderDaoImpl odi = new BookOrderDaoImpl();
		BookOrder order = new BookOrder(10,"11",11,12,"12",13,0,1);
		System.out.print(odi.addAnOrder(order));
	}
	
	public static void testGetOrderList(){
		BookOrderDaoImpl odi = new BookOrderDaoImpl();
		System.out.print(odi.getOrderList().size());
	}
	public static void testDeleteOrderByOid(){
		BookOrderDaoImpl odi = new BookOrderDaoImpl();
		System.out.print(odi.deleteAnOrderByID(2));
	}
	
}

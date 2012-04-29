package com.workshop.clould.test;

import com.workshop.cloud.db.dao.impl.CustomerDaoImpl;
import com.workshop.cloud.vo.Customer;

public class test_db_customer {
	public static void main(String[] args) {
		testAdd();
		testdelete();
	}
	
	public static void testAdd(){
		CustomerDaoImpl cdi = new CustomerDaoImpl();
		Customer obj = new Customer("james","11",22);
		System.out.print(cdi.addCustomer(obj));
	}
	public static void testdelete(){
		CustomerDaoImpl cdi = new CustomerDaoImpl();
		System.out.print(cdi.deleteCustoemrByName("james"));
	}
}

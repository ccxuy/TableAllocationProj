package com.workshop.clould.test;

import com.workshop.cloud.db.dao.impl.WalkinOrderDaoImpl;
import com.workshop.cloud.vo.WalkinOrder;

public class test_db_walkinorder {
	public static void main(String[] args) {
		testAdd();
		testDelete();
		testList();
	}
	public static void testAdd(){
		WalkinOrderDaoImpl wdi = new WalkinOrderDaoImpl();
		WalkinOrder obj = new WalkinOrder(11,"11",1,2);
		System.out.print(wdi.addWalkinOrder(obj));
	}
	
	public static void testList(){
		WalkinOrderDaoImpl wdi = new WalkinOrderDaoImpl();
		System.out.print(wdi.getWalkinOrderList());
	}
	public static void testDelete(){
		WalkinOrderDaoImpl wdi = new WalkinOrderDaoImpl();
		System.out.print(wdi.deleteWalkinOrderByID(1));
	}
}

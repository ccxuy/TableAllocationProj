package com.tap.test;


import org.junit.BeforeClass;
import org.junit.Test;

import com.tap.bizlogic.OperatorLogic;
import com.tap.bizlogic.RestaurantLogic;
import com.tap.locinfo.Setting;
import com.tap.usersys.Operator;

public class TestStaff {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Util.initData();
	}
	@Test public void login(){
		OperatorLogic opLogic = new OperatorLogic();
		Operator op = opLogic.login("admin", "admin");
		//System.out.println(op);
	}

	@Test public void logout(){
		OperatorLogic opLogic = new OperatorLogic();
		Operator op = opLogic.login("admin", "admin");
		opLogic.logout();
	}
}

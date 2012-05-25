/**
 * 
 */
package com.tap.test;

import hirondelle.date4j.DateTime;

import java.util.List;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;

import com.tap.bizlogic.BookOrderLogic;
import com.tap.bizlogic.OperatorLogic;
import com.tap.bizlogic.OrderLogic;
import com.tap.tableordersys.BookOrder;
import com.tap.tableordersys.Guests;
import com.tap.tableordersys.Order;
import com.tap.usersys.Operator;


/**
 * @author Andrew
 *
 */
public class TestOrder {
	OperatorLogic opLogic;
	OrderLogic orderLogic;
	
	List<Order> oList;
	List<BookOrder> boList;
	@Before public void setUpBeforeClass() throws Exception {
		opLogic = new OperatorLogic();
		orderLogic = new OrderLogic();
		orderLogic.emptyWaitingList();
		Operator op = opLogic.login("admin", "admin");
		orderLogic.setOperator(op);
	}
	
	@Test public void NewCustomer(){
		Guests newGuests = new Guests("123", "abc", "5", true); 
		oList = orderLogic.newCustomer(newGuests);
		System.out.println("1------"+oList);
		assert oList!=null;
	}
	@Test public void checkin(){
		assert oList!=null;
		Order o;
		if(null!=oList)
			for(int i=0;i<oList.size();i++){
				o = oList.get(i);
				o.checkIn();
			}
	}
	@Test public void checkout(){
		Order o;
		if(null!=oList)
		for(int i=0;i<oList.size();i++){
			o = oList.get(i);
			o.checkOut();
		}
	}
	@Test public void bookOrder(){
		Guests newGuests = new Guests("123", "abc", "5", true);
		DateTime bookTime = DateTime.now(TimeZone.getDefault());
		boList = orderLogic.newBooking("123", newGuests, bookTime);
	}
	@Test public void checkoutBookOrder(){
		if(null!=oList)
		for(Order o:boList)
			o.checkOut();
	}
}

package com.tap.tableordersys;

import java.util.Date;

import com.tap.usersys.Operator;

public class BookOrder extends Order {

	//protected int timeBegin;
	protected Date timeBegin; 

	public BookOrder(String orderID, Operator operator, Table table,
			Guests gusets, Date timeBegin) {
		super(orderID, operator, table, gusets);
		this.timeBegin = timeBegin;
	}

	public BookOrder(Operator operator, Table table, Guests gusets, Date timeBegin) {
		super(operator, table, gusets);
		this.timeBegin = timeBegin;
	}
	
	public static BookOrder bookTable(Operator o, Table t, Guests g) {
		return null;
	}
	 
	public boolean isOutOfTime() {
		return false;
	}
	 
	public int cancelBookOrder() {
		this.state = STATE_CANCELED;
		return SUCCESS;
	}
	
	
}
 

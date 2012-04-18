package com.tap.tableordersys;

import com.tap.usersys.Operator;

public class BookOrder extends Order {
 
	private int timeBegin;
	 
	public static BookOrder bookTable(Operator o, Table t, Guests g) {
		return null;
	}
	 
	public boolean isOutOfTime() {
		return false;
	}
	 
	public int cancelBookOrder() {
		return 0;
	}
	 
}
 

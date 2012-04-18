package com.tap.tableordersys;

import com.tap.usersys.Operator;

public class Order {
 
	private int state;
	 
	private Operator operator;
	 
	private Table table;
	 
	private Guests gusets;
	 
	public static Order checkIn(Operator o, Table t, Guests g) {
		return null;
	}
	 
	public static Order checkIn(Operator o, Guests g) {
		return null;
	}
	 
	public int checkOut() {
		return 0;
	}
	 
	public void addToWaitingList() {
	 
	}
	 
}
 

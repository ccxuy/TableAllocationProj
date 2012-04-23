package com.tap.tableordersys;

import hirondelle.date4j.DateTime;
import hirondelle.date4j.DateTime.DayOverflow;

import java.util.Date;
import java.util.TimeZone;

import com.tap.locinfo.Status;
import com.tap.usersys.Operator;

public class BookOrder extends Order {

	transient public final int EXPIRE_TIME_HOUR = 2;
	protected DateTime bookTime; 

	public BookOrder(String orderID, Operator operator, Table table,
			Guests gusets, DateTime bookTime) {
		super(orderID, operator, table, gusets);
		this.state = STATE_INIT;
		this.bookTime = bookTime;
	}

	public BookOrder(Operator operator, Table table, Guests gusets, DateTime bookTime) {
		super(operator, table, gusets);
		this.state = STATE_INIT;
		this.bookTime = bookTime;
	}
	
	public static BookOrder bookTable(Operator o, Table t, Guests g) {
		return null;
	}
	
	public boolean canBookTime(DateTime time){
		DateTime tmp = new DateTime(null);
		tmp.plus(0, 0, 0, -2, 0, 0, DayOverflow.Spillover);
		if( 0<tmp.compareTo(bookTime) ){
			return true;
		}
		return false;
	}
	 
	public boolean isOutOfTime() {
		DateTime now = DateTime.now(TimeZone.getDefault());
		if( 0<now.compareTo(bookTime)){
			return true;
		}
		return false;
	}
	
	
	 
	public DateTime getBookTime() {
		return bookTime;
	}

	public void setBookTime(DateTime bookTime) {
		this.bookTime = bookTime;
	}

	/**
	 * @return Status of this command, ok if Status.SUCCESS.getValue()
	 */
	public int cancelBookOrder() {
		this.state = STATE_CANCELED;
		return Status.SUCCESS.getValue();
	}

	@Override
	public String toString() {
		return "BookOrder [ orderID=" + orderID
				+ ", state=" + state + ",bookTime=" + bookTime + ", operator=" + operatorID + ", table="
				+ table + ", gusets=" + gusets + "]";
	}
	
	
}
 

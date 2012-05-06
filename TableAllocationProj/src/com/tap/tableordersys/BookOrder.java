package com.tap.tableordersys;

import hirondelle.date4j.DateTime;
import hirondelle.date4j.DateTime.DayOverflow;

import java.util.Date;
import java.util.TimeZone;

import com.tap.locinfo.Setting;
import com.tap.locinfo.Status;
import com.tap.usersys.Operator;

public class BookOrder extends Order {

	transient public final int EXPIRE_TIME_HOUR = 2;
	protected DateTime bookTime; 
	
	public BookOrder(String orderID){
		super(orderID);
	}
	
	public BookOrder(Order o, DateTime bookTime){
		this.orderID = o.orderID;
		this.gusets = o.gusets;
		this.operatorID = o.operatorID;
		this.state = o.state;
		this.table = o.table;
		this.bookTime = bookTime;
	}

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
		DateTime headTime = new DateTime(bookTime.toString());
		DateTime tailTime = new DateTime(bookTime.toString());
		headTime = headTime.minus(0, 0, 0, +2, 0, 0, DayOverflow.Spillover);
		tailTime = tailTime.plus(0, 0, 0, +2, 0, 0, DayOverflow.Spillover);
		if( false== (headTime.compareTo(time)<=0 &&
				tailTime.compareTo(time)>=0) ){
			return true;
		}
		return false;
	}

	public boolean canCheckInTime(DateTime now) {
		DateTime headTime = new DateTime(bookTime.toString());
		DateTime tailTime = new DateTime(bookTime.toString());
		headTime = headTime.minus(0, 0, 0, 0, 15, 0, DayOverflow.Spillover);
		tailTime = tailTime.plus(0, 0, 0, 0, 15, 0, DayOverflow.Spillover);
		System.out.println("canCheckInTime: "+headTime+" <  "+now+"  < "+tailTime);
		if( true== (headTime.compareTo(now)<=0 &&
				tailTime.compareTo(now)>=0) ){
			return true;
		}
		return false;
	}
	 
	public boolean isOutOfTime() {
		DateTime obsoleteTime = DateTime.now(TimeZone.getDefault());
		obsoleteTime = obsoleteTime.minus(0, 0, 0, 0, Setting.obsoleteMinitesOfBookOrder, 0
				, DayOverflow.Spillover);
		if( obsoleteTime.compareTo(bookTime)>0 ){
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
 

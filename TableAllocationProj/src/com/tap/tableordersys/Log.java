package com.tap.tableordersys;

import java.util.TimeZone;

import hirondelle.date4j.DateTime;

import com.tap.usersys.Operator;

public class Log {
	private String operatorID;
	private DateTime time;
	private String message;
	
	public Log() {
		super();
	}
	public Log(Operator operator, String message) {
		super();
		this.time = DateTime.now(TimeZone.getDefault());
		this.operatorID = operator.getId();
		this.message = message;
	}
	
	
	
	public String getOperatorID() {
		return operatorID;
	}
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}
	public DateTime getTime() {
		return time;
	}
	public void setTime(DateTime time) {
		this.time = time;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Log [operator=" + operatorID + ", time=" + time + ", message="
				+ message + "]";
	}
	
}

package com.tap.tableordersys;

import hirondelle.date4j.DateTime;

import com.tap.usersys.Operator;

public class Log {
	private Operator operator;
	private DateTime time;
	private String message;
	
	public Log() {
		super();
	}
	public Log(Operator operator, String message) {
		super();
		this.operator = operator;
		this.message = message;
	}
	
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
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
		return "Log [operator=" + operator + ", time=" + time + ", message="
				+ message + "]";
	}
	
}

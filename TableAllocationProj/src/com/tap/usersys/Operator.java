package com.tap.usersys;

import com.tap.tableordersys.Order;
import com.tap.tableordersys.Restaurant;

public class Operator {
 
	private String id;
	 
	private String password;
	 
	private String position;

	//final transient static enum CHOICE_POSOTION = {"admin","staff"}; 
	
	public Operator() {
		super();
	}

	public Operator(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

	public Operator(String id, String password, String position) {
		super();
		this.id = id;
		this.password = password;
		this.position = position;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	
	 
}
 

package com.tap.usersys;

import com.tap.tableordersys.Order;
import com.tap.tableordersys.Restaurant;

public class Operator {
 
	private String id;
	 
	private String password;
	 
	private String position;
	
	public enum Position {
		admin("admin"),
		staff("staff");
		String position;
		Position(String s){
			position = s;
		}
		public String getString(){
			return position;
		}
	}

	//final transient static enum CHOICE_POSOTION = {"admin","staff"}; 
	
	public Operator() {
		super();
	}

	public Operator(String id) {
		super();
		this.id = id;
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

	public boolean setPosition(String position) {
		if( true == checkValidPosition(position) ){
			this.position = position;
			return true;
		}else{
			return false;
		}
	}

	public boolean checkValidPosition(String p){
		boolean result = false;
		for(Position po:Position.values()){
			if(p.equals(po.getString()))
				result = true;
		}
		return result;
	}
	@Override
	public String toString() {
		return "Operator [id=" + id + ", password=" + password + ", position="
				+ position + "]";
	}
	
	
	 
}
 

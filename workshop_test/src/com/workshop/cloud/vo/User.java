package com.workshop.cloud.vo;

import com.workshop.cloud.db.dao.impl.UserDaoImpl;

public class User {
	private int uid;
	
	private String username;
	private String passwd;
	private int priority;
	private int rid;
	
	public User(){}
	
	public  User(String username,String passwd,int priority,int rid){
		this.username =username;
		this.passwd =passwd;
		this.priority =priority;
		this.rid =rid;
	}
	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}
	
	public boolean Exist(){
		UserDaoImpl udi = new UserDaoImpl();
		if(udi.validate_user(username, passwd)){return true;};
		return false;
	}
}

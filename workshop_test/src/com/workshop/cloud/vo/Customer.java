package com.workshop.cloud.vo;

public class Customer {
	private int cid;
	private String name;
	private String phonenumber;
	private int rid;

	public Customer(){}
	
	public Customer(String name,String phonenumber,int rid){
		this.name =name;
		this.phonenumber =phonenumber;
		this.rid =rid;
	}
	

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

}

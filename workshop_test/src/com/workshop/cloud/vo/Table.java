package com.workshop.cloud.vo;

public class Table {
	private int tid;
	private String number;
	private String place;
	private int rid;
	
	public Table(){}
	
	public Table(String number,String place,int rid){
		this.number =number;
		this.place= place;
		this.rid= rid;
	}
	
	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

}

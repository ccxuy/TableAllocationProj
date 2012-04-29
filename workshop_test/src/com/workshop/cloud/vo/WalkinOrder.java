package com.workshop.cloud.vo;

public class WalkinOrder {
	private int wid;
	private int covers;
	private String time;
	private int tid;
	private int rid;
	public int withseat;

	public WalkinOrder() {

	}

	public WalkinOrder(int covers, String time, int tid, int rid) {
		this.covers = covers;
		this.time = time;
		this.tid = tid;
		this.rid = rid;
	}

	public int getWithseat() {
		return withseat;
	}

	public void setWithseat(int withseat) {
		this.withseat = withseat;
	}


	

	public int getWid() {
		return wid;
	}

	public void setWid(int wid) {
		this.wid = wid;
	}

	public int getCovers() {
		return covers;
	}

	public void setCovers(int covers) {
		this.covers = covers;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

}

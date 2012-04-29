package com.workshop.cloud.vo;

public class BookOrder {
	private int oid;
	private int covers;
	private String time;
	private int tid;
	private int cid;
	private String arrivetime;
	private int capacity;
	private int withothers;
	private int rid;

	public BookOrder() {
	}

	// oid 是自增主键，不用设置
	public BookOrder(int covers, String time, int tid, int cid,
			String arrivetime, int capacity, int withothers, int rid) {
		this.covers = covers;
		this.time = time;
		this.tid = tid;
		this.cid = cid;
		this.arrivetime = arrivetime;
		this.capacity = capacity;
		this.withothers = withothers;
		this.rid = rid;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
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

	public String getArrivetime() {
		return arrivetime;
	}

	public void setArrivetime(String arrivetime) {
		this.arrivetime = arrivetime;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getRid() {
		return rid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getWithothers() {
		return withothers;
	}

	public void setWithothers(int withothers) {
		this.withothers = withothers;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}
}

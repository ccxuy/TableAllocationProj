package com.tap.tableordersys;

import java.util.List;

import com.tap.locinfo.Status;

public class Table {
 
	private String id;
	
	@Deprecated
	private String name;
	 
	private List<Order> orderList;
	 
	private List<BookOrder> bookOrderList;
	 
	private int capacity;
	 
	public Table(String id) {
		super();
		this.id = id;
	}

	public Table(String id, int capacity) {
		super();
		this.id = id;
		this.capacity = capacity;
	}

	public Table(String id, List orderList, List bookOrderList, int capacity) {
		super();
		this.id = id;
		this.orderList = orderList;
		this.bookOrderList = bookOrderList;
		this.capacity = capacity;
	}
	 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int addOrder(Order o) {
		this.orderList.add(o);
		return Status.SUCCESS.getValue();
	}
	 
	public int cancelOrder(Order order) {
		int res = Status.FAIL.getValue();
		for(Order o: this.orderList){
			if( o.equals(order) ){
				o.cancel();
				res = Status.SUCCESS.getValue();
			}
		}
		return res;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public List<BookOrder> getBookOrderList() {
		return bookOrderList;
	}

	public void setBookOrderList(List<BookOrder> bookOrderList) {
		this.bookOrderList = bookOrderList;
	}

	public int getCapacity() {
		return capacity;
	}

	/**
	 * Add up all order that are ordered in this table
	 * @return how many guest in this table
	 */
	public int countGuestNumberInTable(){
		int num = 0;
		for(Order o:this.orderList){
			if( Status.ORDER_STATE_ORDERD.getValue()==o.getState() ){
				num+=o.getGusetNumber();
			}
		}
		return num;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int doEmptyOrderList() {
		this.orderList.clear();
		return Status.SUCCESS.getValue();
	}
	
	public int doCleanUselessInfo(){
		for(Order o:this.orderList){
			if( Status.ORDER_STATE_ORDERD.getValue()!=o.getState() ){
				this.orderList.remove(o);
			}
		}
		for(Order o:this.bookOrderList){
			if( Status.ORDER_STATE_ORDERD.getValue()!=o.getState() ){
				this.orderList.remove(o);
			}
		}
		return Status.SUCCESS.getValue();
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Table? this.id.equals( ((Table)obj).id ) : false;
	}

	@Override
	public String toString() {
		// [TODO] Auto-generated method stub
		return super.toString();
	}
}
 

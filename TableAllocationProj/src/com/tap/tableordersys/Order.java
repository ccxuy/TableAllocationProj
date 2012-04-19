package com.tap.tableordersys;

import com.tap.locinfo.Status;
import com.tap.usersys.Operator;

/**
 * @author Andrew
 *
 */
public class Order {
	transient public final static int SUCCESS = 0;
	transient public final static int FAIL = 1;

	transient public final static int STATE_CANCELED = 10;
	transient public final static int STATE_INIT = 11;
	transient public final static int STATE_ORDERD = 12;
	transient public final static int STATE_FINISHED = 13;
 
	protected String orderID;
	
	protected int state;
	 
	protected Operator operator;
	 
	protected Table table;
	 
	protected Guests gusets;
	
	
	
	public Order(String orderID, Operator operator, Table table, Guests gusets) {
		super();
		this.orderID = orderID;
		this.operator = operator;
		this.table = table;
		this.gusets = gusets;
	}


	public Order(Operator operator, Table table, Guests gusets) {
		super();
		this.operator = operator;
		this.table = table;
		this.gusets = gusets;
		this.state = Status.STATE_INIT.getValue();
	}

	
	
	/**
	 * change state to STATE_ORDERD
	 * (only if state is in STATE_INIT or STATE_ORDERD)
	 * @return
	 */
	public int checkIn() {
		if(STATE_INIT == this.state
				||STATE_ORDERD== this.state){
			this.state = Status.STATE_ORDERD.getValue();
			return Status.SUCCESS.getValue();
		}
		return Status.FAIL.getValue();
	}
	 
	/**
	 * change state to STATE_FINISHED
	 * (only if state is in STATE_ORDERD)
	 * @return
	 */
	public int checkOut() {
		if(STATE_ORDERD== this.state){
			this.state = Status.STATE_FINISHED.getValue();
			return Status.SUCCESS.getValue();
		}
		return Status.FAIL.getValue();
	}
	
	public int cancel(){
		this.state = Status.STATE_CANCELED.getValue();
		return Status.SUCCESS.getValue();
	}
	
	/**
	 * if can not allocate table then, addCustomerToWaitingList
	 * use WaitingList.addGuests() instead
	 * @deprecated wrong method
	 */
	public void addCustomerToWaitingList() {
		
	}

	

	@Override
	public boolean equals(Object obj) {
		if( obj instanceof Order){
			Order o = (Order) obj;
			return this.orderID == o.orderID;
		}
		return false;
	}

	

	@Override
	public int hashCode() {
		return this.orderID.hashCode();
	}


	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", state=" + state + ", operator="
				+ operator + ", table=" + table + ", gusets=" + gusets + "]";
	}

	
	
	
}
 

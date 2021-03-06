package com.tap.tableordersys;

import java.util.UUID;

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
	 
	protected String operatorID;
	 
	protected Table table;
	
	private String guestID;
	protected Guests gusets;
	
	public Order() {
		super();
	}
	
	public Order(String orderID) {
		super();
		this.orderID = orderID;
	}
	
	public Order(Operator operator, Table table, Guests gusets, boolean ordered){
		super();
		this.orderID = UUID.randomUUID().toString().substring(0,5);
		this.operatorID = operator.getId();
		this.table = table;
		this.guestID = gusets.getId();
		this.gusets = gusets;
		if(ordered)
			this.state = Status.ORDER_STATE_ORDERD.getValue();
		else
			this.state = Status.ORDER_STATE_INIT.getValue();
	}
	
	public Order(String orderID, Operator operator, Table table, Guests gusets) {
		super();
		this.orderID = orderID;
		this.operatorID = operator.getId();
		this.table = table;
		this.guestID = gusets.getId();
		this.gusets = gusets;
		this.state = Status.ORDER_STATE_ORDERD.getValue();
	}


	public Order(Operator operator, Table table, Guests gusets) {
		super();
		this.orderID = UUID.randomUUID().toString().substring(0,5);
		this.operatorID = operator.getId();
		this.table = table;
		this.guestID = gusets.getId();
		this.gusets = gusets;
		this.state = Status.ORDER_STATE_ORDERD.getValue();
	}

	
	
	public String getOrderID() {
		return orderID;
	}


	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}


	public String getOperatorID() {
		return operatorID;
	}


	public void setOperator(Operator operator) {
		this.operatorID = operator.getId();
	}


	public Table getTable() {
		return table;
	}


	public void setTable(Table table) {
		this.table = table;
	}


	public Guests getGusets() {
		return gusets;
	}


	public void setGusets(Guests gusets) {
		if(gusets!=null)
			this.guestID = gusets.getId();
		this.gusets = gusets;
	}


	public String getGuestID() {
		return guestID;
	}

	public void setGuestID(String guestID) {
		this.guestID = guestID;
	}

	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	/**
	 * change state to STATE_ORDERD
	 * (only if state is in STATE_INIT or STATE_ORDERD)
	 * @return
	 */
	public int checkIn() {
		if(STATE_INIT == this.state
				||STATE_ORDERD== this.state){
			this.state = Status.ORDER_STATE_ORDERD.getValue();
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
			this.state = Status.ORDER_STATE_FINISHED.getValue();
			return Status.SUCCESS.getValue();
		}
		return Status.FAIL.getValue();
	}
	
	public int cancel(){
		this.state = Status.ORDER_STATE_CANCELED.getValue();
		return Status.SUCCESS.getValue();
	}
	
	
	
	public int getState() {
		return state;
	}


	/**
	 * if can not allocate table then, addCustomerToWaitingList
	 * use WaitingList.addGuests() instead
	 * @deprecated wrong method
	 */
	public void addCustomerToWaitingList() {
		
	}

	public int getGusetNumber(){
		return this.gusets.getAmount();
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
				+ operatorID + ", table=" + table.getId() + ", gusets=" + gusets.getId() + "]";
	}
	
	
}
 

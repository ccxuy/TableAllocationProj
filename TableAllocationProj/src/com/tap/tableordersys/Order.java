package com.tap.tableordersys;

import com.tap.usersys.Operator;

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
		this.state = Order.STATE_INIT;
	}

	
	
	/**
	 * change state to STATE_ORDERD
	 * (only if state is in STATE_INIT or STATE_ORDERD)
	 * @return
	 */
	public int checkIn() {
		if(STATE_INIT == this.state
				||STATE_ORDERD== this.state){
			this.state = STATE_ORDERD;
			return SUCCESS;
		}
		return FAIL;
	}
	 
	/**
	 * change state to STATE_FINISHED
	 * (only if state is in STATE_ORDERD)
	 * @return
	 */
	public int checkOut() {
		if(STATE_ORDERD== this.state){
			this.state = STATE_FINISHED;
			return SUCCESS;
		}
		return FAIL;
	}
	 
	public void addToWaitingList() {
	 
	}



	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", state=" + state + ", operator="
				+ operator + ", table=" + table + ", gusets=" + gusets + "]";
	}

	
	
	
}
 

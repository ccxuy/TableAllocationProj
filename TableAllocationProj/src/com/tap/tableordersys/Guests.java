package com.tap.tableordersys;

public class Guests {
 
	private String id;
	 
	private String addtionalInfo;
	 
	private int amount;
	 
	private boolean seatAlone;

	public Guests(String id) {
		super();
		this.id = id;
	}

	public Guests(String id, int amount, boolean seatAlone) {
		super();
		this.id = id;
		this.addtionalInfo = "";
		this.amount = amount;
		this.seatAlone = seatAlone;
	}
	
	public Guests(String id, String addtionalInfo, int amount, boolean seatAlone) {
		super();
		this.id = id;
		this.addtionalInfo = addtionalInfo;
		this.amount = amount;
		this.seatAlone = seatAlone;
	}

	public String getAddtionalInfo() {
		return addtionalInfo;
	}

	public void setAddtionalInfo(String addtionalInfo) {
		this.addtionalInfo = addtionalInfo;
	}

	public int getAmount() {
		return amount;
	}
	public String getAmountString() {
		return (new Integer(getAmount())).toString();
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public boolean isSeatAlone() {
		return seatAlone;
	}
	
	public boolean getSeatAlone(){
		return seatAlone;
	}

	public String getSeatAloneString(){
		return (new Boolean(getSeatAlone())).toString();
	}
	
	public void setSeatAlone(boolean seatAlone) {
		this.seatAlone = seatAlone;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Guests [id=" + id + ", addtionalInfo=" + addtionalInfo
				+ ", amount=" + amount + ", seatAlone=" + seatAlone + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if( obj instanceof Guests){
			Guests o = (Guests) obj;
			return this.id.equals(o.id);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
	
}
 

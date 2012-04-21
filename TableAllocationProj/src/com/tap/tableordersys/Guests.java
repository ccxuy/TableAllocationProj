package com.tap.tableordersys;

public class Guests {
 
	private int id;
	 
	private String addtionalInfo;
	 
	private int amount;
	 
	private boolean seatAlone;
	 
	/*
	 * begin
	 * james add
	 */
	public boolean getSeatAlone(){
		return seatAlone;
	}
	
	public void setSeatAlone(boolean b){
		this.seatAlone = b;
	} 
	
	public int getAmount(){
		return this.amount;
	}
	
	public void setAmount(int amount){
		this.amount = amount;
	}
	/*
	 * end
	 * james add
	 */
	public int Gusets(int amount) {
		return 0;
	}
	 
	public int Guests(int amount, boolean seatAlone, String add) {
		return 0;
	}
	 
}
 

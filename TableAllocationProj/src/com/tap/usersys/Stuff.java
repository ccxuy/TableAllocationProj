package com.tap.usersys;

import com.tap.tableordersys.Table;

public abstract class Stuff extends Operator implements AdminFunction, StuffFunction {
 
	/**
	 * @see com.tap.usersys.AdminFunction#setPostionToStuff(int)
	 */
	public int setPostionToStuff(int String) {
		return 0;
	}
	 
	/**
	 * @see com.tap.usersys.AdminFunction#newTable(com.tap.tableordersys.Table)
	 */
	public int newTable(Table table) {
		return 0;
	}
	 
	/**
	 * @see com.tap.usersys.AdminFunction#deleteTable(int)
	 */
	public int deleteTable(int tableID) {
		return 0;
	}
	 
	/**
	 * @see com.tap.usersys.AdminFunction#deleteTable(com.tap.tableordersys.Table)
	 */
	public int deleteTable(Table table) {
		return 0;
	}
	 
	/**
	 * @see com.tap.usersys.AdminFunction#changeTableCapacity(int)
	 */
	public int changeTableCapacity(int capacity) {
		return 0;
	}
	 
	/**
	 * @see com.tap.usersys.StuffFunction#login(string, string)
	 */
	public int login(String id, String pw) {
		return 0;
	}
	 
	/**
	 * @see com.tap.usersys.StuffFunction#logout()
	 */
	public void logout() {
	 
	}
	 
	/**
	 * @see com.tap.usersys.StuffFunction#doOrderCheckin()
	 */
	public void doOrderCheckin() {
	 
	}
	 
	/**
	 * @see com.tap.usersys.StuffFunction#doOrderCheckout()
	 */
	public void doOrderCheckout() {
	 
	}
	 
}
 

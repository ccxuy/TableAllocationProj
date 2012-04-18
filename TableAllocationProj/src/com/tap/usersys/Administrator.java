package com.tap.usersys;

import com.tap.tableordersys.Table;

public abstract class Administrator extends Operator implements AdminFunction {
 
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

	@Override
	public int login(String id, String pw) {
		// [TODO] Auto-generated method stub
		return 0;
	}

	@Override
	public void logout() {
		// [TODO] Auto-generated method stub
		
	}

	@Override
	public void doOrderCheckin() {
		// [TODO] Auto-generated method stub
		
	}

	@Override
	public void doOrderCheckout() {
		// [TODO] Auto-generated method stub
		
	}
	 
}
 

package com.tap.usersys;

import com.tap.tableordersys.Table;

public interface AdminFunction extends StuffFunction {
 
	public abstract int setPostionToStuff(int String);
	public abstract int newTable(Table table);
	public abstract int deleteTable(int tableID);
	public abstract int deleteTable(Table table);
	public abstract int changeTableCapacity(int capacity);
}
 

package com.tap.tableordersys;

import java.util.Iterator;
import java.util.List;

import com.tap.locinfo.Status;
import com.tap.usersys.Operator;

public class Restaurant{
 
	private String name;
	 
	private List<Table> tableList;

	public Restaurant(String name, List<Table> tableList) {
		super();
		this.name = name;
		this.tableList = tableList;
	}

	public int addTable(Table t) {
		if( false == this.tableList.contains(t) ){
			this.tableList.add(t);
			return Status.SUCCESS.getValue();
		}
		return Status.FAIL.getValue();
	}
	 
	/**
	 * @warning it use equals method!
	 * @param t table that you want to delete
	 * @return how many table deleted
	 */
	public int delTable(Table t) {
		int res = 0;
		while( this.tableList.remove(t) ){
			res++;
		}
		return res;
	}
	 
	public void setName(String name) {
		this.name = name;
	}
	 
	public String getName() {
		return this.name;
	}
	 
	public int emptyTableList() {
		this.tableList.clear();
		return Status.SUCCESS.getValue();
	}
	 
	public List<Table> getTableList() {
		return tableList;
	}
}
 

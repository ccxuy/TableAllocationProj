package com.tap.tableordersys;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.tap.locinfo.Status;
import com.tap.usersys.Operator;

public class Restaurant{
 
	private String name;
	 
	private List<Table> tableList;
	
	public Restaurant(String name) {
		super();
		this.name = name;
		this.tableList = new LinkedList<Table>();
	}

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
	
	public Guests findGuestInResturant(String guestsID){
		Guests g;
		for(Table t:this.tableList){
			g = t.findGuestInTable(guestsID);
			if(null!=g)
				return g;
		}
		return null;
	}
	 
	public int emptyTableList() {
		this.tableList.clear();
		return Status.SUCCESS.getValue();
	}
	 
	public List<Table> getTableList() {
		return tableList;
	}
	
	public Table findTableByID(String tid){
		for(Table t:this.tableList){
			if(t.getId().equals(tid)){
				return t;
			}
		}
		return null;
	}
	
	/**
	 * copy except name
	 * @param r
	 */
	public void copy(Restaurant r){
		this.tableList = r.tableList;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Restaurant){
			Restaurant r = (Restaurant) obj;
			return this.name.equals(r);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

	@Override
	public String toString() {
		return "Restaurant [name=" + name + ", tableList=" + tableList + "]";
	}

	public Order findOrderByID(String id) {
		for(Table t: this.tableList){
			for(Order o:t.getOrderList()){
				if(o.getOrderID().equals(id)){
					return o;
				}
			}
		}
		return null;
	}
	public BookOrder findBookOrderByID(String id) {
		for(Table t: this.tableList){
			for(BookOrder o:t.getBookOrderList()){
				if(o.getOrderID().equals(id)){
					return o;
				}
			}
		}
		return null;
	}
	
}
 

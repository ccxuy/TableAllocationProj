package com.tap.tableordersys;

import java.util.Iterator;
import java.util.List;

import com.tap.usersys.Operator;

public class Restaurant extends Table {
 
	public Restaurant(String name, int cpacity) {
		super(name, cpacity);
		// TODO Auto-generated constructor stub
	}

	private String name;
	 
	private List<Table> tableList;
	 
	public int addTable(Table t) {
		try{
			this.tableList.add(t);
			return 1;
			}catch(Exception e){
				e.printStackTrace();
				System.out.print("\n"+"addTable(Table t) error");
			}
		return 0;
	}
	 
	public int delTable(Table t) {
		Iterator<Table> it = tableList.iterator();
		try{
			Table obj = null;
		while(it.hasNext()){
			obj = it.next();
			if(obj.equals(t)){
				tableList.remove(obj);
			}
		}
		return 1;	
		}catch(Exception e){
			e.printStackTrace();
			System.out.print("\n"+"delTable(Table t) error");
		}
		return 0;
	}
	 
	public void setName(String name) {
		this.name = name;
	}
	 
	public String getName() {
		return this.name;
	}
	 
	public int emptyTableList() {
		Iterator<Table> it = tableList.iterator();
		try{
			Table obj = null;
		while(it.hasNext()){
			obj = it.next();	
			tableList.remove(obj);
		}
		return 1;	
		}catch(Exception e){
			e.printStackTrace();
			System.out.print("\n"+"emptyTableList error");
		}
		return 0;
	}
	 
	public List getTableList() {
		if(tableList.size()>0){
			return tableList;
		}else{
			return null;
		}
	}
}
 

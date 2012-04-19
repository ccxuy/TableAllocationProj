package com.tap.tableordersys;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Table extends Guests {
 
	private String id;
	 
	private String name;
	 
	private List<Order> orderList;
	 
	private List bookOrderList;
	 
	private int capacity;
	
	/*
	 *constructor 
	 */
	public  Table(int capacity) {
		this.capacity = capacity;	
	}
	 
	/*
	 *constructor
	 *set capacity of each table differing by table name
	 *如果return 值是1 则正确
	 */
	public Table(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;		
	}
	
	/*
	 * add order to a table
	 */
	public int addOrder(Order o) {
		try{
			this.orderList.add(o);
			return 1;
			}catch(Exception e){
				e.printStackTrace();
				System.out.print("\n"+"addOrder(Order o) error");
			}
		return 0;
	}
	 
	/*
	 * cancel order from a table
	 */
	public int cancelOrder(Order o) {
		Iterator<Order> it = orderList.iterator();
		try{
		Order obj = null;
		while(it.hasNext()){
			obj = it.next();
			if(obj.equals(o)){
				orderList.remove(obj);
			}
		}
		return 1;	
		}catch(Exception e){
			e.printStackTrace();
			System.out.print("\n"+"cancelOrder(Order o) error");
		}
		
		return 0;
	}

	 
	/*
	 *get order list 
	 */
	public List<Order> getOrderList() {
		if(orderList.size()>0){
			return orderList;
		}else{
			return null;
		}
	}
	 
	/*
	 *?? 这个方法是干什么用的？
	 */
	public int setOrderList() {
		
		return 0;
	}
	
	/*
	 *get capacity of a table 
	 */
	public int getCapacity() {
		return this.capacity;
	}
	 
	/*
	 *delete all elements of a order list 
	 */
	public int emptyOrderList() {
		Iterator<Order> it = orderList.iterator();
		try{
		Order obj = null;
		while(it.hasNext()){
			obj = it.next();	
			orderList.remove(obj);
		}
		return 1;	
		}catch(Exception e){
			e.printStackTrace();
			System.out.print("\n"+"cancelOrder(Order o) error");
		}
		return 0;
	}
	 
}
 

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
	 *james加上 
	 */
	private int currentNumOfPeople;
	
	public void setCurrentNumOfPeople(int num){
		this.currentNumOfPeople = num;
	}
	public int getCurrentNumOfPeople(){
		return this.currentNumOfPeople;
	}
	
	public String getTableId(){
		return this.id;
	}
	
	
	/*
	 *这个constructor是为了避免在restaurant 里没有空constructor可用
	 */
	public Table(){
		
	}
	
	/*
	 *constructor 
	 */
	public  Table(int capacity) {
		this.capacity = capacity;	
	}
	 
	/*
	 *constructor
	 *
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
	 *这个方法拿来做什么？？
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
 

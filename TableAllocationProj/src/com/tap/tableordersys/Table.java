package com.tap.tableordersys;

import java.util.LinkedList;
import java.util.List;

import com.tap.locinfo.Status;

public class Table {
 
	private String id;
	
	@Deprecated
	private String name;
	 
	private List<Order> orderList;
	 
	private List<BookOrder> bookOrderList;
	 
	private int capacity;
	 
	/**
	 * Only can use for DB!
	 * @param id
	 */
	public Table(String id) {
		super();
		this.id = id;
	}

	public Table(String id, int capacity) {
		super();
		this.id = id;
		this.orderList = new LinkedList<Order>();
		this.bookOrderList = new LinkedList<BookOrder>();
		this.capacity = capacity;
	}

	public Table(String id, List orderList, List bookOrderList, int capacity) {
		super();
		this.id = id;
		this.orderList = orderList;
		this.bookOrderList = bookOrderList;
		this.capacity = capacity;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int addOrder(Order o) {
		if(null!=o){
			this.orderList.add(o);
			return Status.SUCCESS.getValue();
		}else{
			return Status.FAIL.getValue();
		}
	}
	
	public int addBookOrder(BookOrder o) {
		if(null!=o){
			this.bookOrderList.add(o);
			return Status.SUCCESS.getValue();
		}else{
			return Status.FAIL.getValue();
		}
	}

	public boolean deleteOrder(Order order) {
		return this.orderList.remove(order);
	}
	public boolean deleteBookOrder(BookOrder bookorder) {
		return this.bookOrderList.remove(bookorder);
	}
	public int cancelOrder(Order order) {
		int res = Status.FAIL.getValue();
		for(Order o: this.orderList){
			if( o.equals(order) ){
				o.cancel();
				res = Status.SUCCESS.getValue();
			}
		}
		/*for(Order o: this.bookOrderList){
			if( o.equals(order) ){
				o.cancel();
				res = Status.SUCCESS.getValue();
			}
		}*/
		return res;
	}
	public int cancelBookOrder(BookOrder order) {
		int res = Status.FAIL.getValue();
		for(BookOrder o: this.bookOrderList){
			if( o.equals(order) ){
				o.cancel();
				res = Status.SUCCESS.getValue();
			}
		}
		return res;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public List<BookOrder> getBookOrderList() {
		return bookOrderList;
	}

	public void setBookOrderList(List<BookOrder> bookOrderList) {
		this.bookOrderList = bookOrderList;
	}

	public int getCapacity() {
		return capacity;
	}
	public String getCapacityString() {
		return (new Integer(capacity)).toString();
	}

	public Guests findGuestInTable(String guestsID){
		for(Order o:this.orderList){
			if(o.getGusets().getId().equals(guestsID)
					||o.getGuestID().equals(guestsID)){
				return o.gusets;
			}
		}
		return null;
	}
	
	/**
	 * Add up all order that are ordered in this table
	 * @return how many guest in this table
	 */
	public int countGuestNumberInTable(){
		int num = 0;
		for(Order o:this.orderList){
			if( Status.ORDER_STATE_ORDERD.getValue()==o.getState() ){
				num+=o.getGusetNumber();
			}
		}
		return num;
	}
	public void setCapacity(int capacity) {
		if(capacity<=0){
			System.err.println("Table setCapacity <= 0");
		}
		this.capacity = capacity;
	}

	public Order findOrderByID(String oid){
		for(Order o:this.orderList){
			if(o.getOrderID().equals(oid)){
				return o;
			}
		}
		return null;
	}
	
	public BookOrder findBookOrderByID(String oid){
		for(BookOrder o:this.bookOrderList){
			if(o.getOrderID().equals(oid)){
				return o;
			}
		}
		return null;
	}
	
	public boolean saveOrderByID(Order saveOrder){
		Order o = findOrderByID(saveOrder.getOrderID());
		if( o!=null ){
			this.orderList.remove(o);
			this.orderList.add(saveOrder);
			return true;
		}
		return false;
	}
	
	public boolean saveBookOrderByID(BookOrder saveBookOrder){
		BookOrder o = findBookOrderByID(saveBookOrder.getOrderID());
		if( o!=null ){
			this.bookOrderList.remove(o);
			this.bookOrderList.add(saveBookOrder);
			return true;
		}
		return false;
	}
	
	public int doEmptyOrderList() {
		this.orderList.clear();
		return Status.SUCCESS.getValue();
	}
	
	public int doCleanUselessInfo(){
		for(Order o:this.orderList){
			if( Status.ORDER_STATE_ORDERD.getValue()!=o.getState() ){
				this.orderList.remove(o);
			}
		}
		for(Order o:this.bookOrderList){
			if( Status.ORDER_STATE_ORDERD.getValue()!=o.getState() ){
				this.orderList.remove(o);
			}
		}
		return Status.SUCCESS.getValue();
	}
	

	@Override
	public Table clone(){
		Table newTable = new Table(id, orderList, bookOrderList, capacity);
		return newTable;
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Table? this.id.equals( ((Table)obj).id ) : false;
	}

	@Override
	public String toString() {
		return "Table [id=" + id + ", name=" + name + ", orderListSize="
				+ orderList.size() + ", bookOrderListSize=" + bookOrderList.size()
				+ ", capacity=" + capacity + "]";
	}
}
 

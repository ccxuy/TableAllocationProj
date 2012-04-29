package com.workshop.cloud.db.dao;

import java.util.LinkedList;

import com.workshop.cloud.vo.BookOrder;

public interface BookOrderDao {
	public boolean addAnOrder(BookOrder order);

	public LinkedList<BookOrder> getOrderList();

	public boolean deleteAnOrderByID(int oid);

	/*
	 * can be modified later
	 */
	public boolean deleteOrderByGuest(String guestName);
}

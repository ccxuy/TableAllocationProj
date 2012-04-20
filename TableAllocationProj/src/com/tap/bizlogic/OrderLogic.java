package com.tap.bizlogic;

import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.tap.tableordersys.Guests;
import com.tap.tableordersys.Order;
import com.tap.tableordersys.Restaurant;
import com.tap.tableordersys.Table;
import com.tap.usersys.Operator;

public class OrderLogic {
	/*
	 * 向固定饭店的某个restaurant 添加order
	 * param需要提前定义好
	 */
	/**
	 * @param operator
	 * @param gusets
	 * @param restaurant
	 * @return
	 */
	public boolean addAnOrder(Operator operator, Guests gusets,
			Restaurant restaurant) {
		ObjectContainer db = Db4oEmbedded.openFile(
				Db4oEmbedded.newConfiguration(), "auto.db");
		// 这里开始调用添加order的logic
		// table需要提前设置好
		//添加到waitinglist部分待完善waitinglist
		//order需要一个可以将id初始化的constructor或者id直接被自动初始化
		Table tableCheck = checkNewTableExist(restaurant);
		if (tableCheck != null) {
			// assign table
			Order o = new Order(operator, tableCheck, gusets);
			//TODO
		} else {
			// waiting list
			//TODO
		}
		return true;
	}

	/*
	 * 不用拼桌的时候 查看是否有剩余的没人的桌子 返回table
	 */
	/**
	 * @param restaurant
	 * @return
	 */
	public Table checkNewTableExist(Restaurant restaurant) {
		List<Table> tablelist = restaurant.getTableList();
		for (int i = 0; i < tablelist.size(); i++) {
			if (tablelist.get(i).getCurrentNumOfPeople() == 0) {
				return tablelist.get(i);
			}
		}
		return null;
	}
	
	/*
	 * 到现场之后查询order
	 */
	/**
	 * @return
	 */
	public Order searchOrder(){
		//查询到之后return order
		//TODO
		return null;
	}

	/*
	 * 修改Order在数据库中的状态
	 */
	/**
	 * @param o
	 * @param orderState
	 * @return
	 */
	public boolean modifyAnOrderState(Order o, int orderState) {
		//查询到之后直接存储成功return true
		//TODO
		return false;
	}

	/*
	 * 从数据库里删除一个Order order cancel_int db.delete()
	 */
	/**
	 * @return
	 */
	public boolean cancelAnOrders() {
		//查询数据库找到即可删除
		//TODO
		return false;
	}
}

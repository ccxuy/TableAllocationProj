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
		// table需要提前设置好
		//添加到waitinglist部分待完善waitinglist
		//order需要一个可以将id初始化的constructor或者id直接被自动初始化
		int numOfPeople = gusets.getAmount();
		//如果不需要拼桌
		if(gusets.getSeatAlone()==false){
			Table tableCheck = checkNewTableExist(restaurant);
			// assign table
			while(numOfPeople>0&&tableCheck!=null){			
			//设置这个table的guest
			if(numOfPeople>tableCheck.getCapacity()){
			gusets.setAmount(tableCheck.getCapacity());
			}else{gusets.setAmount(numOfPeople);}
			Order o = new Order(operator, tableCheck, gusets);
			//减去已经放进一个talbe里的人数
			numOfPeople = numOfPeople-tableCheck.getCapacity();
			//order 存入数据库
			//TODO
			//存好一次后马上再check一次有没有新的table可以sign 
			if(numOfPeople>0){tableCheck = checkNewTableExist(restaurant);}
		}//while
		if(numOfPeople>0){
			gusets.setAmount(numOfPeople);
			//加入到waitinglist
		}
		}//如果拼桌
		else{
			
			
		}
		//default
		return false;
	}

	/*
	 * 不用拼桌的时候 查看是否有剩余的没人的桌子 返回table
	 * table list 需要提前设置好
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
	 * 需要拼桌的时候
	 * 得到当前剩余最多位置的table
	 */
	public Table getTableWiehMaxCapacity(Restaurant restaurant){
		List<Table> tablelist = restaurant.getTableList();
		Table t;
		int maxIndex = 0;
		int maxNum = 0;
		for (int i = 0; i < tablelist.size(); i++) {
			t = tablelist.get(i);
			//这个table还有剩余的空位
			if (t.getCurrentNumOfPeople() <t.getCapacity()) {
				//获取空位最多的一个table
				if(maxNum<(t.getCapacity()-t.getCurrentNumOfPeople())){
				maxIndex = i;
				maxNum = t.getCapacity()-t.getCurrentNumOfPeople();
				}			
			}
		}
		if(maxNum!=0){return tablelist.get(maxIndex);}
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

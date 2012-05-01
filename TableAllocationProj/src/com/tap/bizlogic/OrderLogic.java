package com.tap.bizlogic;

import hirondelle.date4j.DateTime;
import hirondelle.date4j.DateTime.DayOverflow;

import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.tap.datastorage.DataControl;
import com.tap.locinfo.Setting;
import com.tap.locinfo.Status;
import com.tap.tableordersys.BookOrder;
import com.tap.tableordersys.Guests;
import com.tap.tableordersys.Order;
import com.tap.tableordersys.Restaurant;
import com.tap.tableordersys.Table;
import com.tap.tableordersys.WaitingList;
import com.tap.usersys.Operator;

public class OrderLogic {
	public boolean change = false;
	Operator operator;
	Restaurant restaurant;
	WaitingList waitList;
	List<Order> lastOrders;

	public OrderLogic() {
		this.restaurant = new Restaurant(Setting.resturantName);
		loadResturant(restaurant.getName());
		loadWaitingList(restaurant.getName());
		lastOrders = new LinkedList<Order>();
	}
	
	public OrderLogic(Operator o) {
		this.restaurant = new Restaurant(Setting.resturantName);
		this.operator = o;
		loadResturant(restaurant.getName());
		loadWaitingList(restaurant.getName());
		lastOrders = new LinkedList<Order>();
	}
	
	public OrderLogic(Restaurant restaurant, Operator o) {
		this.restaurant = restaurant;
		this.operator = o;
		loadResturant(restaurant.getName());
		loadWaitingList(restaurant.getName());
		lastOrders = new LinkedList<Order>();
	}
	
	
	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator o) {
		this.operator = o;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public WaitingList getWaitList() {
		return waitList;
	}

	public void setWaitList(WaitingList waitList) {
		this.waitList = waitList;
	}

	/**
	 * 向固定饭店的某个restaurant 添加order
	 * param需要提前定义好
	 * @param operator
	 * @param guests
	 * @param restaurant
	 * @return
	 */
	public boolean addOrder(Guests guests) {
		lastOrders.clear();
		int numOfPeople = guests.getAmount();
		Table tableCheck = checkNewTableExist(restaurant);
		
		System.out.println("assigning table lastOrders:"+lastOrders.size());
		Order o;
		while (numOfPeople > 0 && tableCheck != null && tableCheck.getCapacity()>0) {
			System.out.println("numOfPeople: "+numOfPeople+"\t"+tableCheck);
			Guests orderGuests = new Guests(guests.getId(),guests.getAddtionalInfo(),guests.getAmount(),guests.getSeatAlone());
			if (numOfPeople > tableCheck.getCapacity()) {
				orderGuests.setAmount(tableCheck.getCapacity());
				numOfPeople = numOfPeople - tableCheck.getCapacity();
			} else {
				orderGuests.setAmount(numOfPeople);
				numOfPeople = 0;
			}
			o = new Order(operator, tableCheck, orderGuests);
			o.checkIn();
			this.lastOrders.add(o);
			tableCheck.getOrderList().add(o);
			
			if (numOfPeople > 0) 
				tableCheck = checkNewTableExist(restaurant);
		}
		
		System.out.println("dispatching table");
		if (numOfPeople > 0 && guests.isSeatAlone() == false) {
			
			tableCheck = getTableWithMaxCapacity(restaurant);
			while (numOfPeople > 0 && tableCheck!=null
					&& tableCheck.countGuestNumberInTable() < tableCheck.getCapacity()) {
				Guests orderGuests = new Guests(guests.getId(),guests.getAddtionalInfo(),guests.getAmount(),guests.getSeatAlone());
				if (numOfPeople > (tableCheck.getCapacity() - tableCheck.countGuestNumberInTable())) {
					orderGuests.setAmount(tableCheck.getCapacity() - tableCheck.countGuestNumberInTable());
					numOfPeople = numOfPeople - (tableCheck.getCapacity() - tableCheck.countGuestNumberInTable());
				} else {
					orderGuests.setAmount(numOfPeople);
					numOfPeople = 0;
				}
				o = new Order(operator, tableCheck, orderGuests);
				o.checkIn();
				this.lastOrders.add(o);
				tableCheck.getOrderList().add(o);

				numOfPeople = numOfPeople
						- (tableCheck.getCapacity() - tableCheck
								.countGuestNumberInTable());
				
				
				if (numOfPeople > 0) {
					tableCheck = getTableWithMaxCapacity(restaurant);
				}
			}
		}
		RestaurantLogic.saveRestauant(restaurant);
		
		if(numOfPeople>=0){
			guests.setAmount(numOfPeople);
			this.waitList.addGuests(guests);
			saveWaitingList();
		}
		
		if(this.lastOrders.size()>=0)
			return true;
		else
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
			if (tablelist.get(i).countGuestNumberInTable() == 0) {
				return tablelist.get(i);
			}
		}
		return null;
	}
	
	/**
	 * 需要拼桌的时候
	 * 得到当前剩余最多位置的table
	 * @param restaurant
	 * @return
	 */
	public Table getTableWithMaxCapacity(Restaurant restaurant){
		List<Table> tablelist = restaurant.getTableList();
		Table t;
		int maxIndex = 0;
		int maxNum = 0;
		for (int i = 0; i < tablelist.size(); i++) {
			t = tablelist.get(i);
			//这个table还有剩余的空位
			if (t.countGuestNumberInTable() <t.getCapacity()) {
				//获取空位最多的一个table
				if(maxNum<(t.getCapacity()-t.countGuestNumberInTable())){
				maxIndex = i;
				maxNum = t.getCapacity()-t.countGuestNumberInTable();
				}			
			}
		}
		if(maxNum!=0){return tablelist.get(maxIndex);}
		return null;
	}
	
	/**
	 * 到现场之后查询order
	 * @return
	 */
	public Order searchOrder(Order o){
		//查询到之后return order
		ObjectContainer db = DataControl.getOCDB();
		try{
			List<Order> oplist = db.queryByExample( o );
			if( 1==oplist.size() ){
				//throw new Exception("Database have dupilicate entities for login user!");
				return oplist.get(0);
			}else{
				System.err.println("No Order in DB like :"+o);
				return null;
			}
		}finally{
			db.close();
		}
	}

	/**
	 * 修改Order在数据库中的状态
	 * @param o
	 * @param orderState
	 * @return
	 */
	public boolean createOrModifyOrderState(Order o, int orderState) {
		//查询到之后直接存储成功return true
		ObjectContainer db = DataControl.getOCDB();
		try{
			List<Order> oplist = db.queryByExample( o );
			if(1==oplist.size()){
				System.out.println("Saved: "+o);
				return true;
			}else if(1<oplist.size()){
				//throw new Exception("Database have dupilicate entities for login user!");
				System.err.println("Exist following Order:");
				for(Order order:oplist){
					System.err.println("\t"+order);
				}
				return false;
			}else{
				System.out.println("New: "+o);
				db.store(o);
				return true;
			}
		}finally{
			db.close();
		}
	}

	/**
	 * 从数据库里删除一个Order order cancel_int db.delete()
	 * @return
	 */
	public boolean cancelOrder(Order o) {
		//查询数据库找到即可删除
		ObjectContainer db = DataControl.getOCDB(Restaurant.class);
		try{
			List<Order> oplist = db.queryByExample( new Order(o.getOrderID()) );
			if(0<oplist.size()){
				System.out.println("The following order deleted:");
				for(Order order: oplist){
					db.delete(order);
				}
				System.out.println(oplist);
				return true;
			}else{
				System.err.println("No matched Order found!");
				return false;
			}
		}finally{
			db.close();
		}
	}
	public List<Table> getTablesOfResturant(){
		return getTablesOfResturant(this.restaurant.getName());
	}
	public List<Table> getTablesOfResturant(String resturantName){
		ObjectContainer db = DataControl.getOCDB(Restaurant.class);
		try{
			List<Restaurant> oplist = db.queryByExample( new Restaurant(resturantName) );
			if(1==oplist.size()){
				Restaurant r = oplist.get(0);
				return r.getTableList();
			}else{
				System.err.println("Error on getOrdersOfResturant!");
				return null;
			}
		}finally{
			db.close();
		}
	}
	
	public void loadResturant(String restaurantName){
		this.restaurant = RestaurantLogic.getRestaurant(restaurantName);
	}
	public void saveResturant(){
		RestaurantLogic.saveRestauant(restaurant);
	}
	public void loadWaitingList(String restaurantName) {
		ObjectContainer db = DataControl.getOCDB(WaitingList.class);
		try{
			List<WaitingList> oplist = db.queryByExample( new WaitingList(restaurantName) );
			if(1==oplist.size()){
				this.waitList = oplist.get(0);
				return;
			}else if( oplist.size()<=0 ){
				System.err.println("loadWaitingList can't load, use NEW one instead: "+restaurantName+" amount of waitingList:"+oplist.size());
				this.waitList = new WaitingList(this.restaurant.getName());
				return;
			}else{
				System.err.println("loadWaitingList dupicate, auto use first one: "+restaurantName+" amount of waitingList:"+oplist.size());
				this.waitList = oplist.get(0);
				return;
			}
		}finally{
			db.close();
		}
	}
	public void saveWaitingList(){
		saveWaitingList(this.restaurant.getName());
	}
	public void saveWaitingList(String restaurantName) {
		ObjectContainer db = DataControl.getOCDB(WaitingList.class);
		try{
			List<WaitingList> oplist = db.queryByExample( new WaitingList(restaurantName) );
			WaitingList wList;
			if(1==oplist.size()){
				wList = oplist.get(0);
				wList.setGuestList(this.waitList.getGuestList());
				db.store(wList);
				System.out.println("Save waiting list "+wList);
				return;
			}else if( oplist.size()<=0 ){
				db.store(this.waitList);
				return;
			}else{
				System.err.println("loadWaitingList dupicate, auto use first one: "+restaurantName+" amount of waitingList:"+oplist.size());
				wList = oplist.get(0);
				wList.setGuestList(this.waitList.getGuestList());
				db.store(wList);
				return;
			}
		}finally{
			db.close();
		}
	}

	public List<Order> getOrdersOfResturant(){
		return getOrdersOfResturant(this.restaurant.getName());
	}
	public List<Order> getOrdersOfResturant(String resturantName){
		ObjectContainer db = DataControl.getOCDB(Restaurant.class);
		try{
			List<Restaurant> oplist = db.queryByExample( new Restaurant(resturantName) );
			if(1==oplist.size()){
				Restaurant r = oplist.get(0);
				db.activate(r, 20);
				LinkedList<Order> oList = new LinkedList<Order>();
				for(Table t:r.getTableList()){
					oList.addAll(t.getOrderList());
				}
				/*for(Order or:oList){
					String gid = or.getGuestID();
					List<Guests> glist = db.queryByExample( new Guests(gid) );
					or.setGusets(glist.get(0));
				}*/
				//System.out.println("Orders are:"+oList);
				return oList;
			}else if(0==oplist.size()){
				System.err.println("No resterant in database!");
				return null;
			}else{
				System.err.println("Error on getOrdersOfResturant!");
				return null;
			}
		}finally{
			db.close();
		}
	}

	public List<BookOrder> getBookOrdersOfResturant(){
		return getBookOrdersOfResturant(restaurant.getName());
	}
	public List<BookOrder> getBookOrdersOfResturant(String resturantName) {
		if(this.restaurant==null){
			System.err.println("OrderLogic not initialized!");
			return null;
		}
		LinkedList<BookOrder> oList = new LinkedList<BookOrder>();
		for(Table t:restaurant.getTableList()){
			//System.out.println(t);
			oList.addAll(t.getBookOrderList());
		}
		//System.out.println("getBookOrdersOfResturant size: "+oList.size());
		return oList;
	}

	public WaitingList getWaitingListOfResturant(){
		return getWaitingListOfResturant(restaurant.getName());
	}
	public WaitingList getWaitingListOfResturant(String restaurantName) {
		ObjectContainer db = DataControl.getOCDB(WaitingList.class);
		try{
			List<WaitingList> oplist = db.queryByExample( new WaitingList(restaurantName) );
			if(1==oplist.size()){
				WaitingList r = oplist.get(0);
				return r;
			}else if( oplist.size()<1 ){
				System.err.println("No waiting list in database!");
				return null;
			}else{
				System.err.println("More than one waiting list in database!");
				WaitingList r = oplist.get(0);
				return r;
			}
		}finally{
			db.close();
		}
	}

	public List<Order> newCustomer(Guests guests) {
		if(null==guests){
			System.err.println("newCustomer: null guests!");
			return null;
		}
		if(null==this.restaurant.findGuestInResturant(guests.getId())){
			if( null==this.waitList.findGuests(guests.getId()) ){
				
				boolean addedToTable = addOrder(guests);
				if(addedToTable){
					System.out.println("New Customer set new order:"+lastOrders);
					return lastOrders;
				}else{
					return null;
				}
			}else{
				System.err.println("newCustomer: guest already waiting, try another id");
				return null;
			}
		}else{
			System.err.println("newCustomer: guest already ordered, try another id");
			return null;
		}
	}
	
	public List<BookOrder> newBooking(String bookOrderID,Guests guests, DateTime time) {
		if(null==guests || null==time){
			System.err.println("newBooking: null==guests || null==time");
			return null;
		}
		DateTime laterThenThisTime = DateTime.now(TimeZone.getDefault());
		laterThenThisTime.plus(0, 0, 0, +2, 0, 0, DayOverflow.Spillover);
		if( time.compareTo(laterThenThisTime)<=0 ){
			System.err.println("newBooking: book time should be later!");
			return null;
		}
		if( null==this.restaurant.findGuestInResturant(guests.getId()) ){
			if( null==this.waitList.findGuests(guests.getId()) ){
				//[FIXME] Check available table for booking
				boolean canBook = true;
				LinkedList<BookOrder> bol = new LinkedList<BookOrder>();
				BookOrder bo;
				int leftAmount = guests.getAmount();
				for(Table table:this.restaurant.getTableList()){
					canBook = true;
					for(BookOrder boGetFromTable:table.getBookOrderList()){
						if(false == boGetFromTable.canBookTime(time)){
							canBook = false;
						}
					}
					if(canBook==true){
						Guests subGuests = new Guests(guests.getId());
						subGuests.setSeatAlone(guests.getSeatAlone());
						if(leftAmount>table.getCapacity()){
							leftAmount = leftAmount-table.getCapacity();
							subGuests.setAmount(table.getCapacity());
						}else if(leftAmount<=table.getCapacity()){
							subGuests.setAmount(leftAmount);
							leftAmount = 0;
						}
						bo = new BookOrder(bookOrderID+"_"+UUID.randomUUID().toString().substring(0, 1)
								, this.operator, table, subGuests, time);

						System.out.println("add bookorder : "+bo);
						//System.err.println(restaurant.findTableByID(table.getId()).getBookOrderList().size());
						restaurant.findTableByID(table.getId()).addBookOrder(bo);
						//System.err.println(restaurant.findTableByID(table.getId()).getBookOrderList().size());
						bol.add(bo);
						
						if(leftAmount<=0){
							break;
						}else{
							continue;
						}
					}
				}
				if(bol.size()>0){
					saveResturant();
					//System.out.println("newBooking:"+bol.size()+" > "+bol);
					return bol;
				}else{
					return null;
				}
			}else{
				System.err.println("newBooking:  guest already waiting, try another id");
				return null;
			}
		}else{
			System.err.println("newBooking:  guest already ordered, try another id");
			return null;
		}
		
	}
}

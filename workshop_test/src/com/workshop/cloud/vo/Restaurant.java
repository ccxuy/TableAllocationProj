package com.workshop.cloud.vo;

public class Restaurant {
	private int rid;
	private String restaurantname;

	public Restaurant(){}
	
	public Restaurant(String name){
		this.restaurantname = name;
	}
	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getRestaurantname() {
		return restaurantname;
	}

	public void setRestaurantname(String restaurantname) {
		this.restaurantname = restaurantname;
	}

}

package com.workshop.cloud.db.dao;

import java.util.LinkedList;
import com.workshop.cloud.vo.Restaurant;

public interface RestaurantDao {
	public boolean addRestaurant(Restaurant restaurant);

	public boolean deleteRestaurantByID(int id);

	public LinkedList<Restaurant> getRestaurantList();
}

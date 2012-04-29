package com.workshop.cloud.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import com.workshop.cloud.db.db_conn;
import com.workshop.cloud.db.dao.RestaurantDao;
import com.workshop.cloud.vo.Restaurant;

public class RestaurantDaoImpl implements RestaurantDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.workshop.cloud.db.dao.RestaurantDao#addRestaurant(com.workshop.cloud
	 * .vo.Restaurant)
	 */
	@Override
	public boolean addRestaurant(Restaurant restaurant) {
		boolean isSuccess = false;
		try {
			db_conn dbc = new db_conn();
			Connection conn = dbc.getConnection();
			PreparedStatement pStatement = null;
			String insert = "INSERT INTO `restaurant`.`restaurant` (`restaurantname`) VALUES (?);";
			pStatement = conn.prepareStatement(insert);
			pStatement.setString(1, restaurant.getRestaurantname());
			int validate = 0;
			validate = pStatement.executeUpdate();
			if (validate > 0) {
				isSuccess = true;
				System.out.print("restaurantdao_impl:  添加restaurant成功" + "\n");
			}
			dbc.close();
			return isSuccess;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.workshop.cloud.db.dao.RestaurantDao#deleteRestaurantByID(int)
	 */
	@Override
	public boolean deleteRestaurantByID(int id) {
		boolean isSuccess = false;
		try {
			db_conn dbc = new db_conn();
			Connection conn = dbc.getConnection();
			PreparedStatement pStatement = null;
			String delete = "DELETE FROM `restaurant`.`restaurant` WHERE `rid` = ?;";
			pStatement = conn.prepareStatement(delete);
			pStatement.setInt(1, id);
			int validate = 0;
			validate = pStatement.executeUpdate();
			if (validate > 0) {
				isSuccess = true;
				System.out.print("RestaurantDaoImpl:  删除Restaurant成功" + "\n");
			}
			dbc.close();
			return isSuccess;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.workshop.cloud.db.dao.RestaurantDao#getRestaurantList()
	 */
	@Override
	public LinkedList<Restaurant> getRestaurantList() {
		try {
			db_conn dbc = new db_conn();
			Connection conn = dbc.getConnection();
			PreparedStatement pStatement = null;
			LinkedList<Restaurant> list = new LinkedList<Restaurant>();
			ResultSet rs = null;
			String select_all = "SELECT * FROM  `restaurant`.`restaurant`;";
			pStatement = conn.prepareStatement(select_all);
			rs = pStatement.executeQuery(select_all);
			if (rs != null) {
				while (rs.next()) {
					Restaurant obj = new Restaurant();
					obj.setRid(rs.getInt("rid"));
					obj.setRestaurantname(rs.getString("restaurantname"));
					list.add(obj);
				}
			}
			if (!rs.next()) {
				System.out.print("RestaurantDaoImpl:成功获取Restaurant链表");
			} else {
				System.out.println("RestaurantDaoImpl:Restaurant list empty");
			}
			dbc.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

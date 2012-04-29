package com.workshop.cloud.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import com.workshop.cloud.db.db_conn;
import com.workshop.cloud.db.dao.BookOrderDao;
import com.workshop.cloud.vo.BookOrder;

/**
 * @author james
 *
 */
public class BookOrderDaoImpl implements BookOrderDao{
	
	/* (non-Javadoc)
	 * @see com.workshop.cloud.db.dao.OrderDao#getOrderList()
	 */
	@Override
	public LinkedList<BookOrder> getOrderList() {
		try{
			db_conn dbc = new db_conn();
			Connection conn = dbc.getConnection();
			PreparedStatement pStatement = null;
			LinkedList<BookOrder> list = new LinkedList<BookOrder>();
			ResultSet rs = null; 
			String select_all = "SELECT * FROM  `restaurant`.`bookorder`;";
			pStatement = conn.prepareStatement(select_all);
			rs = pStatement.executeQuery(select_all);
			if (rs != null) {
				while (rs.next()) {
					BookOrder obj = new BookOrder();
					obj.setOid(rs.getInt("oid"));
					obj.setArrivetime(rs.getString("arrivetime"));
					obj.setCapacity(rs.getInt("capacity"));
					obj.setCid(rs.getInt("cid"));
					obj.setRid(rs.getInt("rid"));
					obj.setTid(rs.getInt("table_id"));
					obj.setTime(rs.getString("time"));
					obj.setWithothers(rs.getInt("withothers"));
					list.add(obj);
				}
			}
			if (!rs.next()){
				System.out.println("BookOrderDaoImpl:Order list empty");
			}else{
				System.out.print("BookOrderDaoImpl:成功获取order链表");
			}
			dbc.close();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.workshop.cloud.db.dao.OrderDao#AddAnOrder(com.workshop.cloud.vo.Order)
	 */
	@Override
	public boolean addAnOrder(BookOrder bookorder) {
		// TODO Auto-generated method stub
		boolean isSuccess = false;
		try{
			db_conn dbc = new db_conn();
			Connection conn = dbc.getConnection();
			PreparedStatement pStatement = null;
			String insert_user = "INSERT INTO `restaurant`.`bookorder` (`oid`, `covers`, `time`,`tid`,`cid`,`arrivetime`,`capacity`,`withothers`,`rid`) VALUES (?,?,?,?,?,?,?,?,?);";
			pStatement = conn.prepareStatement(insert_user);
			pStatement.setInt(1, bookorder.getOid());
			pStatement.setInt(2, bookorder.getCovers());
			pStatement.setString(3, bookorder.getTime());
			pStatement.setInt(4, bookorder.getTid());
			pStatement.setInt(5,bookorder.getCid());
			pStatement.setString(6,bookorder.getArrivetime());
			pStatement.setInt(7,bookorder.getCapacity());
			pStatement.setInt(8,bookorder.getWithothers());
			pStatement.setInt(9, bookorder.getRid());
			int validate = 0;
			validate = pStatement.executeUpdate();
			if (validate > 0) {
				isSuccess = true;
				System.out.print("BookOrderdao_impl:  添加Order成功" + "\n");
			}
			dbc.close();
			return isSuccess;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return isSuccess;
	}

	/* (non-Javadoc)
	 * @see com.workshop.cloud.db.dao.OrderDao#deleteAnOrderByID(int)
	 */
	@Override
	public boolean deleteAnOrderByID(int oid) {
		try{
			db_conn dbc = new db_conn();
			Connection conn = dbc.getConnection();
			PreparedStatement pStatement = null;
			boolean isSuccess = false;
			String delete = "DELETE FROM `restaurant`.`bookorder` WHERE `oid` = ?;";
			pStatement = conn.prepareStatement(delete);
			pStatement.setInt(1,oid);
			int validate = 0;
			validate = pStatement.executeUpdate();	
			if (validate > 0) {
				isSuccess = true;
				System.out.print("BookOrderdao_impl:  删除order成功" + "\n");
			}
			dbc.close();
			return isSuccess;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteOrderByGuest(String guestName) {
		// TODO Auto-generated method stub
		return false;
	}

}

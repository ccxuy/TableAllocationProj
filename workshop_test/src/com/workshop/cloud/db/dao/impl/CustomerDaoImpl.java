package com.workshop.cloud.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import com.workshop.cloud.db.db_conn;
import com.workshop.cloud.db.dao.CustomerDao;
import com.workshop.cloud.vo.Customer;

public class CustomerDaoImpl implements CustomerDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.workshop.cloud.db.dao.CustomerDao#addCustomer(com.workshop.cloud.
	 * vo.Customer)
	 */
	@Override
	public boolean addCustomer(Customer customer) {
		boolean isSuccess = false;
		try {
			db_conn dbc = new db_conn();
			Connection conn = dbc.getConnection();
			PreparedStatement pStatement = null;
			String insert = "INSERT INTO `restaurant`.`customer` (`name`, `phonenumber`, `rid`) VALUES (?,?,?);";
			pStatement = conn.prepareStatement(insert);
			pStatement.setString(1, customer.getName());
			pStatement.setString(2, customer.getPhonenumber());
			pStatement.setInt(3, customer.getRid());
			int validate = 0;
			validate = pStatement.executeUpdate();
			if (validate > 0) {
				isSuccess = true;
				System.out.print("Custoemrdao_impl:  添加Customer成功" + "\n");
			}
			dbc.close();
			return isSuccess;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	/* (non-Javadoc)
	 * @see com.workshop.cloud.db.dao.CustomerDao#deleteCustoemrByName(java.lang.String)
	 */
	@Override
	public boolean deleteCustoemrByName(String customername) {
		boolean isSuccess = false;
		try {
			db_conn dbc = new db_conn();
			Connection conn = dbc.getConnection();
			PreparedStatement pStatement = null;
			String delete = "DELETE FROM `restaurant`.`customer` WHERE `name` = ?;";
			pStatement = conn.prepareStatement(delete);
			pStatement.setString(1, customername);
			int validate = 0;
			validate = pStatement.executeUpdate();
			if (validate > 0) {
				isSuccess = true;
				System.out.print("CustomerDaoImpl:  删除customer成功" + "\n");
			}
			dbc.close();
			return isSuccess;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	// important change here
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.workshop.cloud.db.dao.CustomerDao#getCustomerList()
	 */
	@Override
	public LinkedList<Customer> getCustomerList() {
		try {
			db_conn dbc = new db_conn();
			Connection conn = dbc.getConnection();
			PreparedStatement pStatement = null;
			LinkedList<Customer> list = new LinkedList<Customer>();
			ResultSet rs = null;
			String select_all = "SELECT * FROM  `restaurant`.`customer`;";
			pStatement = conn.prepareStatement(select_all);
			rs = pStatement.executeQuery(select_all);
			if (rs != null) {
				while (rs.next()) {
					Customer obj = new Customer();
					obj.setCid(rs.getInt("cid"));
					obj.setName(rs.getString("name"));
					obj.setPhonenumber(rs.getString("phonenumber"));
					obj.setRid(rs.getInt("rid"));
					list.add(obj);
				}
			}
			if (!rs.next()) {
				System.out.println("CustomerDaoImpl:Customer list empty");
			} else {
				System.out.print("CustomerDaoImpl:成功获取order链表");
			}
			dbc.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

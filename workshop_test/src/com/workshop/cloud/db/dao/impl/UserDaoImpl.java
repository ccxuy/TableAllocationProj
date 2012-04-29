package com.workshop.cloud.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import com.workshop.cloud.db.db_conn;
import com.workshop.cloud.db.dao.UserDao;
import com.workshop.cloud.vo.User;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean validate_user(String username, String passwd) {
		boolean isSuccess = false;
		User user = new User();
		try {
			db_conn dbc = new db_conn();
			Connection conn = dbc.getConnection();
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			String select_one_user = "SELECT * FROM  `restaurant`.`user`"
					+ " WHERE `user`.`username` = '" + username + "' "
					+ "AND `user`.`passwd` = '" + passwd + "';";
			pStatement = conn.prepareStatement(select_one_user);
			rs = pStatement.executeQuery(select_one_user);
			if (rs.next()) {
				user.setUsername(rs.getString("username"));
				user.setPasswd(rs.getString("passwd"));
				System.out.print("Userdao_impl: user exist\n");
				isSuccess = true;
			}
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.workshop.cloud.db.dao.UserDao#addUser(com.workshop.cloud.vo.User)
	 */
	@Override
	public boolean addUser(User user) {
		boolean isSuccess = false;
		try {
			db_conn dbc = new db_conn();
			Connection conn = dbc.getConnection();
			PreparedStatement pStatement = null;
			String insert_user = "INSERT INTO `restaurant`.`user` (`username`, `passwd`, `priority`,`rid`) VALUES (?,?,?,?);";
			pStatement = conn.prepareStatement(insert_user);
			pStatement.setString(1, user.getUsername());
			pStatement.setString(2, user.getPasswd());
			pStatement.setInt(3, user.getPriority());
			pStatement.setInt(4, user.getRid());
			int validate = 0;
			validate = pStatement.executeUpdate();
			if (validate > 0) {
				isSuccess = true;
				System.out.print("Userdao_impl:  添加user成功" + "\n");
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
	 * @see com.workshop.cloud.db.dao.UserDao#deleteUserByName(java.lang.String)
	 */
	@Override
	public boolean deleteUserByName(String username) {

		boolean isSuccess = false;
		try {
			db_conn dbc = new db_conn();
			Connection conn = dbc.getConnection();
			PreparedStatement pStatement = null;
			String delete_user = "DELETE FROM `restaurant`.`user` WHERE `username` = ?;";
			pStatement = conn.prepareStatement(delete_user);
			pStatement.setString(1, username);
			// 执行预处理语句 用 validate 接受返回的 int 判断是否执行成功
			int validate = 0;
			validate = pStatement.executeUpdate();
			// 判断是否执行成功
			if (validate > 0) {
				isSuccess = true;
				System.out.print("Userdao_impl:  删除user成功" + "\n");
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
	 * @see
	 * com.workshop.cloud.db.dao.UserDao#getUserListByName(java.lang.String)
	 */
	@Override
	public User getUserByName(String username) {
		User user = null;
		try {
			db_conn dbc = new db_conn();
			Connection conn = dbc.getConnection();
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			String select_one_user = "SELECT * FROM  `restaurant`.`user`"
					+ " WHERE `user`.`username` = '" + username + "';";
			pStatement = conn.prepareStatement(select_one_user);
			rs = pStatement.executeQuery(select_one_user);

			if (rs.next()) {
				user = new User();
				user.setUsername(rs.getString("username"));
				user.setPasswd(rs.getString("passwd"));
				user.setPriority(rs.getInt("priority"));
				user.setRid(rs.getInt("rid"));
				System.out.print("Userdao_impl:成功获取用户名\n");
			}
			dbc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	/* (non-Javadoc)
	 * @see com.workshop.cloud.db.dao.UserDao#getUserList()
	 */
	@Override
	public LinkedList<User> getUserList() {
		try {
			db_conn dbc = new db_conn();
			Connection conn = dbc.getConnection();
			PreparedStatement pStatement = null;
			LinkedList<User> list = new LinkedList<User>();
			ResultSet rs = null;
			String select_all = "SELECT * FROM  `restaurant`.`user`;";
			pStatement = conn.prepareStatement(select_all);
			rs = pStatement.executeQuery(select_all);
			if (rs != null) {
				while (rs.next()) {
					User obj = new User();
					obj.setUid(rs.getInt("uid"));
					obj.setUsername(rs.getString("username"));
					obj.setPasswd(rs.getString("passwd"));
					obj.setPriority(rs.getInt("priority"));
					obj.setRid(rs.getInt("rid"));
					list.add(obj);
				}
			}
			if (!rs.next()) {
				System.out.println("UserDaoImpl:User list empty");
			} else {
				System.out.print("UserDaoImpl:成功获取User链表");
			}
			dbc.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
package com.workshop.cloud.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import com.workshop.cloud.db.db_conn;
import com.workshop.cloud.db.dao.TableDao;
import com.workshop.cloud.vo.Table;

public class TableDaoImpl implements TableDao {

	/* (non-Javadoc)
	 * @see com.workshop.cloud.db.dao.TableDao#addTable(com.workshop.cloud.vo.Table)
	 */
	@Override
	public boolean addTable(Table table) {
		boolean isSuccess = false;
		try {
			db_conn dbc = new db_conn();
			Connection conn = dbc.getConnection();
			PreparedStatement pStatement = null;
			String insert = "INSERT INTO `restaurant`.`tableinfo` (`number`, `place`, `rid`) VALUES (?,?,?);";
			pStatement = conn.prepareStatement(insert);
			pStatement.setString(1, table.getNumber());
			pStatement.setString(2, table.getPlace());
			pStatement.setInt(3, table.getRid());
			int validate = 0;
			validate = pStatement.executeUpdate();
			if (validate > 0) {
				isSuccess = true;
				System.out.print("Tabledao_impl:  添加Table成功" + "\n");
			}
			dbc.close();
			return isSuccess;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	/* (non-Javadoc)
	 * @see com.workshop.cloud.db.dao.TableDao#deleteTable(int)
	 */
	@Override
	public boolean deleteTable(int tid) {
		boolean isSuccess = false;
		try{
			db_conn dbc = new db_conn();
			Connection conn = dbc.getConnection();
			PreparedStatement pStatement = null;
			String delete = "DELETE FROM `restaurant`.`tableinfo` WHERE `tid` = ?;";
			pStatement = conn.prepareStatement(delete);
			pStatement.setInt(1,tid);
			int validate = 0;
			validate = pStatement.executeUpdate();	
			if (validate > 0) {
				isSuccess = true;
				System.out.print("TableDaoImpl:  删除table成功" + "\n");
			}
			dbc.close();
			return isSuccess;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	/* (non-Javadoc)
	 * @see com.workshop.cloud.db.dao.TableDao#getTableList()
	 */
	@Override
	public LinkedList<Table> getTableList() {
		try{
			db_conn dbc = new db_conn();
			Connection conn = dbc.getConnection();
			PreparedStatement pStatement = null;
			LinkedList<Table> list = new LinkedList<Table>();
			ResultSet rs = null;
			String select_all = "SELECT * FROM  `restaurant`.`tableinfo`;";
			pStatement = conn.prepareStatement(select_all);
			rs = pStatement.executeQuery(select_all);
			if (rs != null) {
				while (rs.next()) {
					Table obj = new Table();
					obj.setTid(rs.getInt("tid"));
					obj.setPlace(rs.getString("number"));
					obj.setPlace(rs.getString("place"));
					obj.setRid(rs.getInt("rid"));
					list.add(obj);
				}
			}
			if (!rs.next()) {
				System.out.print("TableDaoImpl:成功获取table链表");
			} else {
				System.out.println("TableDaoImpl:Table list empty");
			}
			dbc.close();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

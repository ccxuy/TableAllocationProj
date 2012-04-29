package com.workshop.cloud.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import com.workshop.cloud.db.db_conn;
import com.workshop.cloud.db.dao.WalkinOrderDao;
import com.workshop.cloud.vo.WalkinOrder;

public class WalkinOrderDaoImpl implements WalkinOrderDao{

	/* (non-Javadoc)
	 * @see com.workshop.cloud.db.dao.WalkinOrderDao#addWalkinOrder(com.workshop.cloud.vo.WalkinOrder)
	 */
	@Override
	public boolean addWalkinOrder(WalkinOrder walkinorder) {
		boolean isSuccess = false;
		try{
			db_conn dbc = new db_conn();
			Connection conn = dbc.getConnection();
			PreparedStatement pStatement = null;
			String insert = "INSERT INTO `restaurant`.`walkinorder` (`covers`, `time`, `tid`,`rid`, `withseat`) VALUES (?,?,?,?,?);";
			pStatement = conn.prepareStatement(insert);
			pStatement.setInt(1, walkinorder.getCovers());
			pStatement.setString(2, walkinorder.getTime());
			pStatement.setInt(3, walkinorder.getTid());
			pStatement.setInt(4, walkinorder.getRid());
			pStatement.setInt(5, walkinorder.getWithseat());
			int validate = 0;
			validate = pStatement.executeUpdate();
			if (validate > 0) {
				isSuccess = true;
				System.out.print("WalkinOrderdao_impl:  添加WalkinOrder成功" + "\n");
			}
			dbc.close();
			return isSuccess;
		}catch (Exception e) {
			e.printStackTrace();
			return isSuccess;
		}
	}

	/* (non-Javadoc)
	 * @see com.workshop.cloud.db.dao.WalkinOrderDao#deleteWalkinOrderByID(int)
	 */
	@Override
	public boolean deleteWalkinOrderByID(int id) {
		boolean isSuccess = false;
		try{
			db_conn dbc = new db_conn();
			Connection conn = dbc.getConnection();
			PreparedStatement pStatement = null;
			String delete = "DELETE FROM `restaurant`.`walkinorder` WHERE `wid` = ?;";
			pStatement = conn.prepareStatement(delete);
			pStatement.setInt(1, id);
			// 执行预处理语句 用 validate 接受返回的 int 判断是否执行成功
			int validate = 0;
			validate = pStatement.executeUpdate();
			// 判断是否执行成功
			if (validate > 0) {
				isSuccess = true;
				System.out.print("WalkinOrderDaoImpl:  删除WalkinOrder成功" + "\n");
			}
			dbc.close();
			return isSuccess;
		}catch (Exception e) {
			e.printStackTrace();
			return isSuccess;
		}
	}

	/* (non-Javadoc)
	 * @see com.workshop.cloud.db.dao.WalkinOrderDao#getWalkinOrderList()
	 */
	@Override
	public LinkedList<WalkinOrder> getWalkinOrderList() {
		try{
			db_conn dbc = new db_conn();
			Connection conn = dbc.getConnection();
			PreparedStatement pStatement = null;
			LinkedList<WalkinOrder> list = new LinkedList<WalkinOrder>();
			ResultSet rs = null;
			String select_all = "SELECT * FROM  `restaurant`.`walkinorder`;";
			pStatement = conn.prepareStatement(select_all);
			rs = pStatement.executeQuery(select_all);
			if (rs != null) {
				while (rs.next()) {
					WalkinOrder obj = new WalkinOrder();
					obj.setWid(rs.getInt("wid"));
					obj.setCovers(rs.getInt("covers"));
					obj.setTime(rs.getString("time"));
					obj.setTid(rs.getInt("tid"));
					obj.setRid(rs.getInt("rid"));
					obj.setWithseat(rs.getInt("withseat"));
					list.add(obj);
				}
			}
			if (!rs.next()) {
				System.out.print("WalkinOrderDaoImpl:成功获取WalkinOrder链表");
			} else {
				System.out.println("WalkinOrderDaoImpl:WalkinOrder list empty");
			}
			dbc.close();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

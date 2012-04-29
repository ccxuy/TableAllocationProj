package com.workshop.clould.test;

import com.workshop.cloud.db.db_conn;

public class test_db_conn {
	public static void main(String[] args){
		try {
			db_conn db = new db_conn();
			db.getConnection();
			System.out.print("get connection");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("fail to get connection");
		}
	}
	
}

package com.workshop.cloud.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class db_conn {
	public Connection conn = null;

	private static final String DBDRIVER = "org.gjt.mm.mysql.Driver";

	private static final String DBURL = "jdbc:mysql://localhost:3306/restaurant";

	private static final String DBUSER = "root";
	private static final String DBPASSWORD = "";

	public db_conn() throws Exception {
		try {
			Class.forName(DBDRIVER); // Load database driver
			this.conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD); // Connect
																				// database
		} catch (Exception e) {
			throw e;
		}
	}

	public Connection getConnection() { // Get the connection
		return this.conn;
	}

	public void close() throws Exception {
		if (this.conn != null) {
			try {
				this.conn.close(); // Close database connection
			} catch (Exception e) {
				throw e;
			}
		}
	}
}

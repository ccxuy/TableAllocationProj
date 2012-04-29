package com.workshop.cloud.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
	String username = "root";
	String password = "";
	String jdbcDriver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306";

	public CreateTable() {
		registerDriver(); 
	}

	public void registerDriver() {
		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			System.err.print(e.getMessage());
		}
	}

	public void execute() {
		Connection con = null;
		Statement stmt = null;
		String SQLCreate1 = "CREATE TABLE `restaurant`.`tableinfo` (`tid` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,`number` VARCHAR(45) NOT NULL,`place` VARCHAR(45) NOT NULL,`rid` INTEGER UNSIGNED NOT NULL,PRIMARY KEY (`tid`),UNIQUE(`number`))ENGINE = InnoDB;";
		String SQLCreate2 = "CREATE TABLE `restaurant`.`customer` (`cid` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NOT NULL,`phonenumber` VARCHAR(45) NOT NULL,`rid` INTEGER UNSIGNED NOT NULL,PRIMARY KEY (`cid`))ENGINE = InnoDB;";
		String SQLCreate3 = "CREATE TABLE `restaurant`.`walkinorder` (`wid` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,`covers` INTEGER UNSIGNED NOT NULL,`time` VARCHAR(45) NOT NULL,`tid` INTEGER UNSIGNED NOT NULL,`rid` INTEGER UNSIGNED NOT NULL,`withseat` INTEGER UNSIGNED NOT NULL DEFAULT  '0',PRIMARY KEY (`wid`))ENGINE = InnoDB;";
		String SQLCreate4 = "CREATE TABLE `restaurant`.`bookorder` (`oid` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,`covers` INTEGER UNSIGNED NOT NULL,`time` VARCHAR(45) NOT NULL,`tid` INTEGER UNSIGNED NOT NULL,`cid` INTEGER UNSIGNED NOT NULL,`arrivetime` VARCHAR(45),`capacity` INTEGER UNSIGNED NOT NULL,`withothers` INTEGER UNSIGNED NOT NULL,`rid` INTEGER UNSIGNED NOT NULL,PRIMARY KEY (`oid`))ENGINE = InnoDB;";
		String SQLCreate5 = "CREATE TABLE `restaurant`.`user` (`uid` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,`username` VARCHAR(45) NOT NULL ,`passwd` VARCHAR(45) NOT NULL ,`priority` INTEGER UNSIGNED NOT NULL,`rid` INTEGER UNSIGNED NOT NULL,PRIMARY KEY (`uid`),UNIQUE(`username`)) ENGINE = InnoDB;";
		String SQLCreate6 = "CREATE DATABASE  `restaurant`;";
		String SQLCreate7 = "DROP DATABASE  `restaurant`;";
		String SQLCreate8 = "CREATE TABLE `restaurant`.`restaurant` (`rid` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,`restaurantname` VARCHAR(45) NOT NULL,PRIMARY KEY (`rid`),UNIQUE(`restaurantname`))ENGINE = InnoDB;";
		String SQLAlert1="ALTER TABLE   `restaurant`.`bookorder` ADD FOREIGN KEY (  `tid` ) REFERENCES  `restaurant`.`tableinfo` (`tid`)ON DELETE CASCADE ON UPDATE CASCADE;";
		String SQLAlert2="ALTER TABLE   `restaurant`.`bookorder` ADD FOREIGN KEY (  `cid` ) REFERENCES  `restaurant`.`customer` (`cid`)ON DELETE CASCADE ON UPDATE CASCADE;";
		String SQLAlert3="ALTER TABLE   `restaurant`.`bookorder` ADD FOREIGN KEY (  `rid` ) REFERENCES  `restaurant`.`restaurant` (`rid`)ON DELETE CASCADE ON UPDATE CASCADE;";
		String SQLAlert4="ALTER TABLE   `restaurant`.`customer` ADD FOREIGN KEY (  `rid` ) REFERENCES  `restaurant`.`restaurant` (`rid`)ON DELETE CASCADE ON UPDATE CASCADE;";
		String SQLAlert5="ALTER TABLE   `restaurant`.`tableinfo` ADD FOREIGN KEY (  `rid` ) REFERENCES  `restaurant`.`restaurant` (`rid`)ON DELETE CASCADE ON UPDATE CASCADE;";
		String SQLAlert6="ALTER TABLE   `restaurant`.`user` ADD FOREIGN KEY (  `rid` ) REFERENCES  `restaurant`.`restaurant` (`rid`)ON DELETE CASCADE ON UPDATE CASCADE;";
		String SQLAlert7="ALTER TABLE   `restaurant`.`walkinorder` ADD FOREIGN KEY (  `tid` ) REFERENCES  `restaurant`.`tableinfo` (`rid`)ON DELETE CASCADE ON UPDATE CASCADE;";
		String SQLAlert8="ALTER TABLE   `restaurant`.`walkinorder` ADD FOREIGN KEY (  `rid` ) REFERENCES  `restaurant`.`restaurant` (`rid`)ON DELETE CASCADE ON UPDATE CASCADE;";
		try {
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			try {
				stmt.execute(SQLCreate6);
			} catch (Exception e) {
				stmt.execute(SQLCreate7);
				stmt.execute(SQLCreate6);
				System.out.print("Create a new database");
			} finally {
				stmt.execute(SQLCreate1);
				stmt.execute(SQLCreate2);
				stmt.execute(SQLCreate3);
				stmt.execute(SQLCreate4);
				stmt.execute(SQLCreate5);
				stmt.execute(SQLCreate8);
				stmt.execute(SQLAlert1);
				stmt.execute(SQLAlert2);
				stmt.execute(SQLAlert3);
				stmt.execute(SQLAlert4);
				stmt.execute(SQLAlert5);
				stmt.execute(SQLAlert6);
				stmt.execute(SQLAlert7);
				stmt.execute(SQLAlert8);
			}
			con.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception ex) {
			} // ����
		}
	}

	public static void main(String[] args) {
		CreateTable createTable = new CreateTable();
		createTable.execute();
	}
} // END OF CreateTable

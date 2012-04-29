package com.workshop.clould.test;

import com.workshop.cloud.db.dao.impl.UserDaoImpl;
import com.workshop.cloud.vo.User;
/*test plan:
 * 1.检查外键rid
 * 2.检查username duplicated
 */
public class test_db_user {
	public static void main(String[] args) {
		//testAddUser();
		//deleteAddUser();
		//getUserByname();
		//validateuser();
		//testUserLogin();
	}
	
	public static void testAddUser(){
		User user = new User();
		user.setUsername("22");
		user.setPasswd("james");
		user.setPriority(0);
		user.setRid(2);
		UserDaoImpl udi = new UserDaoImpl();
		System.out.print(udi.addUser(user));
	}
	public static void deleteAddUser(){
		UserDaoImpl udi = new UserDaoImpl();
		System.out.print(udi.deleteUserByName("james"));
	}
	
	public static void getUserByname(){
		UserDaoImpl udi = new UserDaoImpl();
		User user =udi.getUserByName("james");
		System.out.print(user.getPasswd());
	}	
	
	public static void validateuser(){
		UserDaoImpl udi = new UserDaoImpl();
		System.out.print(udi.validate_user("james","james"));
	}
	
	public static void testUserLogin(){
		User user = new User();
		user.setUsername("james");
		user.setPasswd("james");
		System.out.print(user.Exist());
	}
}

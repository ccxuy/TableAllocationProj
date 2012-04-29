package com.workshop.cloud.db.dao;

import java.util.LinkedList;

import com.workshop.cloud.vo.User;

public interface UserDao {
public boolean validate_user(String username,String passwd);
public boolean addUser(User user);
public boolean deleteUserByName(String username);
public User getUserByName(String username);
public LinkedList<User> getUserList();
}

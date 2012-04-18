package com.tap.usersys;

public interface StuffFunction {
 
	public abstract int login(String id, String pw);
	public abstract void logout();
	public abstract void doOrderCheckin();
	public abstract void doOrderCheckout();
}
 

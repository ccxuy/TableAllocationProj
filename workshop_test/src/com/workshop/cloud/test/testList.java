package com.workshop.cloud.test;

import java.util.ArrayList;

public class testList {
	public static void main(String[] args){
	ArrayList<String> list = new ArrayList<String>();
	list.add("user1");
	list.add("user2");
	
	String[] strArray = (String [])list.toArray(new String[0]);
	System.out.print(strArray[0]);
	}
}

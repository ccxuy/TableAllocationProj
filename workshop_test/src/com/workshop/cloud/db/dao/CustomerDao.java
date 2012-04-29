package com.workshop.cloud.db.dao;

import java.util.LinkedList;

import com.workshop.cloud.vo.Customer;

public interface CustomerDao {
public boolean addCustomer(Customer customer);
public boolean deleteCustoemrByName(String customername);
public LinkedList<Customer> getCustomerList();
}

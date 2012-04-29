package com.workshop.cloud.db.dao;

import java.util.LinkedList;

import com.workshop.cloud.vo.WalkinOrder;

public interface WalkinOrderDao {
public boolean addWalkinOrder(WalkinOrder walkinorder);
public boolean deleteWalkinOrderByID(int id);
public LinkedList<WalkinOrder> getWalkinOrderList();
}

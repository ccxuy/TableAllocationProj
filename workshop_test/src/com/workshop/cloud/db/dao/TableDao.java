package com.workshop.cloud.db.dao;

import java.util.LinkedList;
import com.workshop.cloud.vo.Table;

public interface TableDao {
	public boolean addTable(Table table);

	public boolean deleteTable(int tid);

	public LinkedList<Table> getTableList();
}

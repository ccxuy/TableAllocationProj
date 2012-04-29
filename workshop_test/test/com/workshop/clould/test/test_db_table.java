package com.workshop.clould.test;

import com.workshop.cloud.db.dao.impl.TableDaoImpl;
import com.workshop.cloud.vo.Table;
/*test plan:
 * 1.检查是否和rid关联
 * 2.检查table name的duplicated是否存在
 */
public class test_db_table {
	public static void main(String[] args) {
		//testAdd();
		//testDelete();
		//testList();
	}
	public static void testAdd(){
	TableDaoImpl tdi = new TableDaoImpl();
	Table t = new Table("103","near james",2);
	System.out.print(tdi.addTable(t));
	}
	public static void testDelete(){
		TableDaoImpl tdi = new TableDaoImpl();
		System.out.print(tdi.deleteTable(1));
	}
	public static void testList(){
		TableDaoImpl tdi = new TableDaoImpl();
		System.out.print(tdi.getTableList());
	}
	
	
}

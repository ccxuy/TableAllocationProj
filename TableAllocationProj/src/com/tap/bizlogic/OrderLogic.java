package com.tap.bizlogic;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.tap.tableordersys.Order;
import com.tap.usersys.Operator;

public class OrderLogic {

	
	/**
	 * @param o
	 * @return
	 */
	public boolean addAnOrder(Order o) {
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "auto.db");
		try{
			List<Operator> oplist = db.queryByExample(o);
			if(1<oplist.size()){
				//throw new Exception("Database have dupilicate entities for login user!");
				return false;
			}else{
				 db.store(o);
				 return true;
			}
		}finally{
			db.close();
		}
}
}

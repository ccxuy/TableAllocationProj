package com.tap.bizlogic;

import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.tap.usersys.Operator;

public class OperatorLogic {
	transient final static int SIGN_SUCCESS = 1;
	transient final static int SIGN_FAIL = 0;
	
	static Operator login(String id, String pw) throws Exception{
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "auto.db");
		try{
			List<Operator> oplist = db.queryByExample(new Operator(id, pw));
			if(1<oplist.size()){
				throw new Exception("Database have dupilicate entities for login user!");
			}else if(0==oplist.size()){
				return null;
			}else{
				return oplist.get(0);
			}
		}finally{
			db.close();
		}
	}
	
	static int register(String id, String pw, String position){
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "auto.db");
		try{
			List<Operator> oplist = db.queryByExample(new Operator());
			if(0<oplist.size()){
				//throw new Exception("Database have dupilicate entities for login user!");
				return SIGN_FAIL;
			}else{
				return SIGN_SUCCESS;
			}
		}finally{
			db.close();
		}
		//return SIGN_FAIL;
	}

}

package com.tap.bizlogic;

import java.util.LinkedList;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.tap.datastorage.DataControl;
import com.tap.tableordersys.Restaurant;
import com.tap.usersys.Operator;

public class OperatorLogic {
	transient public final static int SIGN_SUCCESS = 1;
	transient public final static int SIGN_FAIL = 0;
	
	transient public final static String PO_ADMIN = "admin";
	transient public final static String PO_STAFF = "staff";
	
	private Operator currentOperator = null;
	
	public Operator getCurrentOperator() {
		return currentOperator;
	}

	public void setCurrentOperator(Operator currentOperator) {
		this.currentOperator = currentOperator;
	}

	public boolean isLogined(){
		return this.currentOperator!=null;
	}
	
	/**
	 * @param id
	 * @param pw
	 * @return Operator, if wrong pw or id, return null
	 * @throws Exception database error
	 */
	public Operator login(String id, String pw) throws Exception{
		ObjectContainer db = DataControl.getOCDB(Operator.class);
		try{
			List<Operator> oplist = db.queryByExample(new Operator(id, pw));
			if(1<oplist.size()){
				//throw new Exception("Database have dupilicate entities for login user!");
				System.err.println("Database have dupilicate entities for login user!");
			}
			if(0>=oplist.size()){
				return null;
			}else{
				this.currentOperator = oplist.get(0);
				return oplist.get(0);
			}
		}finally{
			db.close();
		}
	}
	
	public void logout(){
		this.currentOperator = null;
	}
	
	/**
	 * @param id
	 * @param pw
	 * @param position string PO_ADMIN or PO_STAFF
	 * @return int SIGN_SUCCESS SIGN_FAIL
	 */
	public int register(String id, String pw, String position){
		ObjectContainer db = DataControl.getOCDB(Operator.class);
		try{
			Operator op = new Operator();
			op.setId(id);
			List<Operator> oplist = db.queryByExample( op );
			if(0<oplist.size()){
				//throw new Exception("Database have dupilicate entities for login user!");
				System.err.println("Exist following operator:");
				for(Operator o:oplist){
					System.err.println("\t"+o);
				}
				return SIGN_FAIL;
			}else{
				op = new Operator(id, pw, position);
				db.store(op);
				return SIGN_SUCCESS;
			}
		}finally{
			db.close();
		}
		//return SIGN_FAIL;
	}

	public boolean isAdmin() {
		return currentOperator.getPosition().equals(PO_ADMIN);
	}

	public List<Operator> getAllOperator() {
		ObjectContainer db = DataControl.getOCDB(Operator.class);
		try{
			Operator op = new Operator();
			List<Operator> opl = db.queryByExample( op );
			List<Operator> oplist = new LinkedList<Operator>();
			if(0<opl.size()){
				for(Operator o:opl)
					oplist.add(o);
				return oplist;
			}else{
				System.err.println("No operators in databse");
				return null;
			}
		}finally{
			db.close();
		}
	}

}

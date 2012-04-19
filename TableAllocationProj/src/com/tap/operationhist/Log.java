package com.tap.operationhist;

public class Log {
	transient public final String logFileAddress = "/log.txt";
	transient public final int SUCCESS = 1;
	transient public final int FAIL = 0;
 
	public int writeLog(String s) {
		return 0;
	}
	 
	public int writeErrLog(String s) {
		return 0;
	}
	
	public String read(){
		return null;
	}
}
 

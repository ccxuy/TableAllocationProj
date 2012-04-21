package com.tap.locinfo;

public enum Status {
	SUCCESS(1),
	FAIL(0),
	ORDER_STATE_CANCELED(10),
	ORDER_STATE_INIT(11),
	ORDER_STATE_ORDERD(12),
	ORDER_STATE_FINISHED(13);
	
	private int value;
	
	private Status(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

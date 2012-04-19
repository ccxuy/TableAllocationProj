package com.tap.locinfo;

public enum Status {
	SUCCESS(1),
	FAIL(0),
	STATE_CANCELED(10),
	STATE_INIT(11),
	STATE_ORDERD(12),
	STATE_FINISHED(13);
	
	private int value;
	
	private Status(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

package com.tap.tableordersys;

import java.util.LinkedList;
import java.util.List;

import com.tap.locinfo.Status;

public class WaitingList {
 
	private List<Guests> guestList;
	
	
	public WaitingList() {
		super();
		this.guestList = new LinkedList<Guests>();
	}

	/**
	 * @param GuestsID
	 * @return how many gusets have been remove
	 */
	public int removeGuests(int GuestsID) {
		int count = 0;
		while( this.guestList.remove(new Guests(GuestsID)) ){
			
		}
		return count;
	}
	 
	public int addGuests(Guests guest) {
		this.guestList.add(guest);
		return Status.SUCCESS.getValue();
	}
	 
	public List<Guests> getGuestsList() {
		return this.guestList;
	}
	public List<Guests> getGuestList() {
		return guestList;
	}

	public void setGuestList(List<Guests> guestList) {
		this.guestList = guestList;
	}

	public List<Guests> doScanAvalableGuests() {
		return null;
	}
	 
	 
}
 

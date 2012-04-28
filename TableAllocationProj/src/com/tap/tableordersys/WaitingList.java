package com.tap.tableordersys;

import java.util.LinkedList;
import java.util.List;

import com.tap.locinfo.Status;

public class WaitingList {
 
	private String resturant;
	private List<Guests> guestList;
	
	
	public WaitingList(String resturantID) {
		super();
		this.resturant = resturantID;
		this.guestList = new LinkedList<Guests>();
	}

	public String getResturant() {
		return resturant;
	}

	public void setResturant(String resturant) {
		this.resturant = resturant;
	}

	/**
	 * @param GuestsID
	 * @return how many gusets have been remove
	 */
	public int removeGuests(String GuestsID) {
		int count = 0;
		while( this.guestList.remove(new Guests(GuestsID)) ){
			
		}
		return count;
	}
	 
	public int addGuests(Guests guest) {
		if(this.guestList.contains(guest)){
			return Status.FAIL.getValue();
		}else{
			this.guestList.add(guest);
			return Status.SUCCESS.getValue();
		}
	}
	
	public Guests findGuests(String guestID) {
		for(Guests g:this.guestList){
			if(g.getId().equals(guestID)){
				return g;
			}
		}
		return null;
	}
	 
	public List<Guests> getGuestList() {
		return guestList;
	}

	public void setGuestList(List<Guests> guestList) {
		this.guestList = guestList;
	}

	//[TODO] doScanAvalableGuests
	public List<Guests> doScanAvalableGuests() {
		return null;
	}
	 
	 
}
 

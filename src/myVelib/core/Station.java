/**
 * The stations that are described in the project
 * @author Shuai
 */
package myVelib.core;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;

public class Station implements java.io.Serializable{
	static final long serialVersionUID = 2326497858930734556L;
	/**
	 * All the attributes are public for easier inspection
	 */
	public Coordinates Co;
	public ArrayList<Slot> slots;
	public int ID;
	public boolean Online;
	public boolean isPlus;
	public int slot_num;
	public boolean hasBikes;
	public int rentNum;
	public int returnNum;
 //	calculating balance between the following interval 
	static String starttime="2021-05-26/13:00:00";
	static String setuptime="2021-05-26/13:05:00";
	static String endtime="2021-06-30/18:00:00";
	public double occupationRate;
	
	
	/**
	 * Generate a station without a plus attribute
	 * @param co the coordinate
	 * @param slots_n the number of slots
	 * @param iD the ID
	 * @param online if it is online or not
	 * @throws ParseException
	 */
	
	public Station(Coordinates co, int slots_n, int iD, boolean online) throws ParseException {
		super();
		Co = co;
		ID = iD;
		Online = online;
		isPlus=false;
		slot_num=slots_n;
		this.slots = GenerateSlot();
		rentNum=0;
		returnNum=0;
	}
	/**
	 * Generate a plus station
	 * @param co coordinate
	 * @param slots_n the number of slots
	 * @param iD the ID
	 * @param online if it's online or not
	 * @param isplus it is plus station
	 * @throws ParseException
	 */
	public Station(Coordinates co, int slots_n, int iD, boolean online,boolean isplus) throws ParseException {
		super();
		Co = co;
		ID = iD;
		Online = online;
		isPlus=isplus;
		slot_num=slots_n;
		this.slots = GenerateSlot();
		rentNum=0;
		returnNum=0;
	}
	/**
	 * Generate the slots in the stations
	 * @return a arraylist of slots
	 * @throws ParseException
	 */
	public ArrayList<Slot> GenerateSlot() throws ParseException {
		ArrayList<Slot>  slots = new ArrayList<Slot>();
		for (int i=1;i<=slot_num;i++) {
			Slot s=new Slot();
			s.ID=i;
			s.free=true;
			s.freeTimeInterval.add(new Time(starttime).time);
			slots.add(s);
		}
		return slots;
	}
	/**
	 * Count the number of free slots
	 * @return number of free slots
	 */
	public int countFree() {
		int cnt=0;
		for(Slot s : slots) {
			if (s.free==true){
				cnt++;
			}
		}
		return cnt;
}
	/**
	 * Distribute bikes in the slots
	 * @param b the bikes
	 * @throws ParseException
	 */
	public void setupBicycle(Bicycle b) throws ParseException {
		int cnt=countFree();
		if (cnt==0) {
			System.out.println("No more free slot");
		}
		else {
			for (Slot s : this.slots) {
				if (s.free==true) {
					s.freeTimeInterval.add(new Time(setuptime).time);
					s.free=false;
					s.bicycle=b;
					break;
			}
		}
	
	}
	}
	/**
	 * add a bicycle in the slots
	 * @param sess the ride session which contains the bike
	 * @throws ParseException
	 */
	public void addBicycle(Session sess) throws ParseException {
		int cnt=countFree();
		if (cnt==0) {
			System.out.println("No more free slot");
		}
		else {
			for (Slot s : this.slots) {
				if (s.free==true) {
					s.freeTimeInterval.add(sess.getEndtime().time);
					s.free=false;
					s.bicycle=sess.getBicycle();
					break;
			}
		}
	
	}
	}
	/**
	 * clear all the bikes
	 */
	public void clear() {
		for (Slot s : this.slots) {
			
			if (s.free==true) {
				s.bicycle=null;
			}
	}
		
	}
	/**
	 * remove the bike from the slot
	 * @return 
	 */
	public int removeBicycle() {
		boolean hasbikes=hasBikes();
		if (hasbikes==false) {
			System.out.println("No more free bicycle");
			return -1;
		}
		else {
			for (Slot slot : slots) {
				
				if (slot.free==false) {
					slot.free=true;
					Date d=new Date();
					slot.freeTimeInterval.add(d);
					int id=slot.bicycle.getID();
					return id;
				}
		}
	
	}
	return -1;
	}
	/**
	 * if the station has bikes
	 * @return if the station has bikes
	 */
	public boolean hasBikes() {
		int cnt=this.slot_num-countFree();
		if(cnt==0) {
			return false;
		}
		return true;
	}
	/**
	 * Calculate the balance of a station
	 * @return balance of the station
	 * @throws ParseException
	 */
	public double calculateBalance() throws ParseException {
		Date start=new Time(starttime).time;
		Date end=new Time(endtime).time;
		int timeInterval=(int)((end.getTime()-start.getTime())/1000/60);
		for(Slot s:slots) {
			s.freeTimeInterval.add(new Time(endtime).time);
			for(int i=0;i<s.freeTimeInterval.size()-1;i+=2) {
				long f=s.freeTimeInterval.get(i+1).getTime()-s.freeTimeInterval.get(i).getTime();
				s.freetime+=(int)(f/1000/60);
			}
		}
		int totalFreeTime=0;
		for(Slot s:slots) {
			totalFreeTime+=s.freetime;
		}

		double balance=1.0-((double)(totalFreeTime)/(double)(timeInterval*slot_num));
		occupationRate=balance;
		return balance;
	}
	/**
	 * Rewrite the toString method
	 */
	@Override
	public String toString() {
		return "Station: ID "+ID;
	}

}


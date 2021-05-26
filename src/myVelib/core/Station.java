package myVelib.core;

import java.text.ParseException;
import java.util.*;

public class Station implements java.io.Serializable{
	static final long serialVersionUID = 2326497858930734556L;
	Coordinates Co;
	ArrayList<Slot> slots;
	int ID;
	boolean Online;
	public boolean isPlus;
	int slot_num;
	boolean hasBikes;
	public int rentNum;
	public int returnNum;
 //	calculating balance between the following interval 
	static String starttimeInterval="2021-05-26/13:00:00";
	static String endtimeInterval="2021-06-30/18:00:00";
	public double occupationRate;
	
	
	
	
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
	
	public ArrayList<Slot> GenerateSlot() throws ParseException {
		ArrayList<Slot>  slots = new ArrayList<Slot>();
		for (int i=1;i<=slot_num;i++) {
			Slot s=new Slot();
			s.ID=i;
			s.free=true;
			s.freeTimeInterval.add(new Time(starttimeInterval).time);
			slots.add(s);
		}
		return slots;
	}

	public int countFree() {
		int cnt=0;
		for(Slot s : slots) {
			if (s.free==true){
				cnt++;
			}
		}
		return cnt;
}
	public void addBicycle(Bicycle b) {
		int cnt=countFree();
		if (cnt==0) {
			System.out.println("No more free slot");
		}
		else {
			for (Slot s : this.slots) {
				if (s.free==true) {
					s.freeTimeInterval.add(new Date());
					s.free=false;
					s.bicycle=b;
					break;
			}
		}
	
	}
	}
	public void clear() {
		for (Slot s : this.slots) {
			
			if (s.free==true) {
				s.bicycle=null;
			}
	}
		
	}
	public int removeBicycle() {
		boolean hasbikes=this.hasBikes();
		if (hasbikes==false) {
			System.out.println("No more free bicycle");
			return 0;
		}
		else {
			for (Slot s : this.slots) {
				
				if (s.free==false) {
					s.free=true;
					s.freeTimeInterval.add(new Date());
					int id=s.bicycle.getID();
					return id;
				}
		}
	
	}
	return 0;
	}
	public boolean hasBikes() {
		int cnt=this.slot_num-countFree();
		if(cnt==0) {
			return false;
		}
		return true;
	}
	public double calculateBalance() throws ParseException {
		Date start=new Time(starttimeInterval).time;
		Date end=new Time(endtimeInterval).time;
		int timeInterval=(int)((end.getTime()-start.getTime())/1000/60);
		for(Slot s:slots) {
			s.freeTimeInterval.add(new Time(endtimeInterval).time);
			for(int i=0;i<s.freeTimeInterval.size();i+=2) {
				long f=s.freeTimeInterval.get(i+1).getTime()-s.freeTimeInterval.get(i).getTime();
				s.freetime+=(int)(f/1000/60);
			}
		}
		int totalFreeTime=0;
		for(Slot s:slots) {
			totalFreeTime+=s.freetime;
		}
		double balance=1-totalFreeTime/timeInterval*slot_num;
		occupationRate=balance;
		return balance;
	}

}


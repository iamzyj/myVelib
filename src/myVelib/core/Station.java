package myVelib.core;

import java.util.ArrayList;

public class Station implements java.io.Serializable{
	static final long serialVersionUID = 2326497858930734556L;
	Coordinates Co;
	ArrayList<Slot> slots;
	int ID;
	boolean Online;
	public boolean isPlus;
	int slot_num;
	boolean hasBikes;
	
	
	
	
	public Station(Coordinates co, int slots_n, int iD, boolean online) {
		super();
		Co = co;
		this.slots = Station.GenerateSlot(slots_n);
		ID = iD;
		Online = online;
		isPlus=false;
		slot_num=slots_n;
	}
	public Station(Coordinates co, int slots_n, int iD, boolean online,boolean isplus) {
		super();
		Co = co;
		this.slots = Station.GenerateSlot(slots_n);
		ID = iD;
		Online = online;
		isPlus=isplus;
		slot_num=slots_n;
	}
	
	public static ArrayList<Slot> GenerateSlot(int n) {
		ArrayList<Slot>  slots = new ArrayList<Slot>();
		for (int i=1;i<=n;i++) {
			Slot s=new Slot();
			s.ID=i;
			s.free=true;
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

}


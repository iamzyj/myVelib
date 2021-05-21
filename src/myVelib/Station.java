package myVelib;

import java.util.ArrayList;

public class Station {
	Coordinates Co;
	ArrayList<Slot> slots;
	int ID;
	boolean Online;
	boolean isPlus;
	int slot_num;
	
	
	
	
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

	public int countFree(ArrayList<Slot>  slots) {
		int cnt=0;
		for(Slot s : slots) {
			if (s.free==true){
				cnt++;
			}
		}
		return cnt;
}
	public void addBicycle(Bicycle b) {
		int cnt=countFree(this.slots);
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
	public void removeBicycle(String type) {
		int cnt=this.slot_num-countFree(this.slots);
		if (cnt==0) {
			System.out.println("No more free bicycle");
		}
		else {
			for (Slot s : this.slots) {
				if (type=="E") {
				if (s.free==false && s.bicycle instanceof Electrical) {
					s.free=true;
					s.bicycle=null;
					break;
				}
			}
				
				if (type=="M") {
					if (s.free==false && s.bicycle instanceof Mechanical) {
						s.free=true;
						s.bicycle=null;
						break;
					}
				}
		}
	
	}
	}
	public void removeBicycle() {
		int cnt=this.slot_num-countFree(this.slots);
		if (cnt==0) {
			System.out.println("No more free bicycle");
		}
		else {
			for (Slot s : this.slots) {
				
				if (s.free==false) {
					s.free=true;
					s.bicycle=null;
					break;
				}
		}
	
	}
	}

}


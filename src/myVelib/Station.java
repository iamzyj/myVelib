package myVelib;

import java.util.ArrayList;

public class Station {
	Coordinates Co;
	ArrayList<Slot> slot;
	int ID;
	boolean Online;
	boolean isPlus;
	int slot_num;
	
	
	
	
	public Station(Coordinates co, ArrayList<Slot> slot, int iD, boolean online, int s) {
		super();
		Co = co;
		this.slot = slot;
		ID = iD;
		Online = online;
		isPlus=false;
		slot_num=s;
	}
	
	public static ArrayList<Slot> GenerateSlot(int n) {
		ArrayList<Slot>  slot = new ArrayList<Slot>(n);
		for (int i=1;i<=n;i++) {
			Slot s=new Slot();
			s.ID=i;
			s.free=true;
			slot.add(s);
		}
		return slot;
	}

	public static int countFree(ArrayList<Slot>  slot) {
		int cnt=0;
		for(Slot s : slot) {
			if (s.free==true){
				cnt++;
			}
		}
		return cnt;
}
	public void addBicycle(Bicycle b) {
		int cnt=countFree(this.slot);
		if (cnt==0) {
			System.out.println("No more free slot");
		}
		else {
			for (Slot s : this.slot) {
				if (s.free==true) {
					s.free=false;
					s.bicycle=b;
			}
		}
	
	}
	}
	public void removeBicycle(Bicycle b) {
		int cnt=this.slot_num-countFree(this.slot);
		if (cnt==0) {
			System.out.println("No more free bicycle");
		}
		else {
			for (Slot s : this.slot) {
				if (s.free==false && s.bicycle==b) {
					s.free=true;
					s.bicycle=null;
			}
		}
	
	}
	}

}


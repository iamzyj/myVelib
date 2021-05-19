package myVelib;

import java.util.ArrayList;

public class Station {
	Coordinates Co;
	ArrayList<Slot> slot;
	int ID;
	boolean Online;
	
	
	
	
	public Station(Coordinates co, ArrayList<Slot> slot, int iD, boolean online) {
		super();
		Co = co;
		this.slot = slot;
		ID = iD;
		Online = online;
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

	
}


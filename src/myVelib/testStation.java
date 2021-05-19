package myVelib;

import java.util.ArrayList;

public class testStation {
	public static void main(String args[]) {
		int n=2;
		ArrayList<Slot> slot = Station.GenerateSlot(n);
		Coordinates co= new Coordinates(2,2);
		Station s = new Station(co,slot,1,true,n);
		Electrical b= new Electrical(0,"E");
		Mechanical b1=new Mechanical(0,"M");
		int fr=s.countFree(s.slot);
		System.out.println(fr);
		s.addBicycle(b1);
		fr=s.countFree(s.slot);
		System.out.println(fr);
		s.addBicycle(b);
		fr=s.countFree(s.slot);
		System.out.println(fr);
		
		s.removeBicycle("M");
		System.out.println(s.slot.get(0).free);
		
	}
}

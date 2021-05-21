package myVelib;

import java.util.ArrayList;

public class testStation {
	public static void main(String args[]) {
		int n=2;
		Coordinates co= new Coordinates(2,2);
		Station s = new Station(co,n,1,true);
		Electrical b= new Electrical(0);
		Mechanical b1=new Mechanical(0);
		int fr=s.countFree(s.slots);
		System.out.println(fr);
		s.addBicycle(b1);
		fr=s.countFree(s.slots);
		System.out.println(fr);
		s.addBicycle(b);
		fr=s.countFree(s.slots);
		System.out.println(fr);
		
		s.removeBicycle("M");
		System.out.println(s.slots.get(0).free);
		
	}
}

package myVelib;

import java.util.ArrayList;

public class VelibSystem {
	ArrayList<Bicycle> bicycles = new ArrayList<Bicycle>();
	ArrayList<Station> stations =new ArrayList<Station>();
	ArrayList<User> users=new ArrayList<User>();
	public static void main(String args[]) {
		VelibSystem s= new VelibSystem();
		
		for(int i=0;i<10;i++) {
			int id;
			if (s.bicycles==null) {
				id=0;
			}
			else
				{id = s.bicycles.size();}
			s.bicycles.add(new Electrical(id));
		}
		for (Bicycle b:s.bicycles) {
		System.out.println(b.getID());
		}
	}
}

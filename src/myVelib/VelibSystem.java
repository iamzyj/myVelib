package myVelib;

import java.util.ArrayList;

public class VelibSystem {
	 ArrayList<Bicycle> bicycle = new ArrayList<Bicycle>();
	public static void main(String args[]) {
		VelibSystem s= new VelibSystem();
		
		for(int i=0;i<10;i++) {
			int id;
			if (s.bicycle==null) {
				id=0;
			}
			else
				{id = s.bicycle.size();}
			s.bicycle.add(new Electrical(id));
		}
		for (Bicycle b:s.bicycle) {
		System.out.println(b.getID());
		}
	}
}

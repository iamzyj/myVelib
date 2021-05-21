package myVelib;

import java.util.*;

public class VelibSystem {
	ArrayList<Bicycle> bicycles = new ArrayList<Bicycle>();
	ArrayList<Station> stations =new ArrayList<Station>();
	ArrayList<User> users=new ArrayList<User>();
	String name;
	public VelibSystem(String name) {
		this.name=name;
	}
	public static void main(String args[]) {
		VelibSystem s= new VelibSystem("001");
		
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
	public void generateStations(int nStations,int nSlots) {
		HashSet<Coordinates> c=new HashSet<Coordinates>();
		while(c.size()<nStations) {
			c.add(new Coordinates());
		}
		int i=0;
		for(Coordinates cs:c) {
			stations.add(new Station(cs,nSlots,i,true));
		}

	}
	public void distributeBikes(int nBikes) {
		int nMech=(int)(nBikes*0.7);
		int nElec=nBikes-nMech;
		int id=0;
		while(nMech>0) {
			for(Station s:stations) {
				if (nMech>0) {
				s.addBicycle(new Mechanical(id));
				id+=1;
				nMech-=1;
				}
			}
		}
		while(nElec>0) {
			for(Station s:stations) {
				if (nElec>0) {
				s.addBicycle(new Electrical(id));
				id+=1;
				nElec-=1;
				}
			}
			}
		
	}
	public void setup(String name,int nStations, int nSlots, int nBikes) {
		VelibSystem s= new VelibSystem(name);
		s.generateStations(nStations, nSlots); 
		s.distributeBikes(nBikes);
	}
}

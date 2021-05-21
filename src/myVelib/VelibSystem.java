package myVelib;

import java.text.ParseException;
import java.util.*;

public class VelibSystem {
	HashMap<Integer,Bicycle> bicycles;
	HashMap<Integer,Station> stations;
	HashMap<Integer,User> users;
//	ArrayList<Station> stations =new ArrayList<Station>();
//	ArrayList<User> users=new ArrayList<User>();
	String name;
	public VelibSystem(String name) {
		this.bicycles=new HashMap<Integer,Bicycle>();
		this.stations=new HashMap<Integer,Station>();
		this.users=new HashMap<Integer,User>();
		this.name=name;
	}
	public static void main(String args[]) {
//		VelibSystem s= new VelibSystem("001");
//		
//		for(int i=0;i<10;i++) {
//			int id;
//			if (s.bicycles==null) {
//				id=0;
//			}
//			else
//				{id = s.bicycles.size();}
//			s.bicycles.add(new Electrical(id));
//		}
//		for (Bicycle b:s.bicycles) {
//		System.out.println(b.getID());
//		}
	}
	public void generateStations(int nStations,int nSlots) {
		HashSet<Coordinates> c=new HashSet<Coordinates>();
		while(c.size()<nStations) {
			c.add(new Coordinates());
		}
		int id=0;
		for(Coordinates cs:c) {
			stations.put(id,new Station(cs,nSlots,id,true));
		}

	}
	public void distributeBikes(int nBikes) {
		int nMech=(int)(nBikes*0.7);
		int nElec=nBikes-nMech;
		int id=0;
		while(nMech>0) {
			for(Integer key:stations.keySet()) {
				Station s=stations.get(key);
				if (nMech>0) {
				s.addBicycle(new Mechanical(id));
				id+=1;
				nMech-=1;
				}
			}
		}
		while(nElec>0) {
			for(Integer key:stations.keySet()) {
				Station s=stations.get(key);
				if (nElec>0) {
				s.addBicycle(new Electrical(id));
				id+=1;
				nElec-=1;
				}
			}
			}
		
	}
	public static VelibSystem setup(String name,int nStations, int nSlots, int nBikes) {
		VelibSystem s= new VelibSystem(name);
		s.generateStations(nStations, nSlots); 
		s.distributeBikes(nBikes);
		return s;
	}
//	之后并没有加network name,因为都是实例方法，直接调用,名字貌似没用？
//	RegistrationCard似乎有点问题
	public void addUser(String username,RegistrationCard card) {
		int id=users.size()+1;
		User u=new User(id);
		u.setUsername(username);
		u.setCard(card);
		users.put(id,u);
	}
	public void offline(int stationID) {
		Station s=stations.get(stationID);
		s.Online=false;
	}
	public void online(int stationID) {
		Station s=stations.get(stationID);
		s.Online=false;
	}
//	rent bike 必须与 return bike 配套
	public void rentbike(int userID,int stationID) {
		User u=users.get(userID);
		Station s=stations.get(stationID);
		int bicycleID=s.removeBicycle();
		Bicycle b=bicycles.get(bicycleID);
		Session sess=new Session(s,b);
		u.addSession(sess);
	}
	public double returnbike(int userID,int stationID,String str) throws ParseException {
		User u=users.get(userID);
		Station s=stations.get(stationID);
		Session sess=u.getCurrentSession();
		sess.setEndtime(str);
		sess.setEndStation(s);
		sess.calculatePrice(u.getCard(), u.getCredits());
		return sess.getPrice();
	}
}

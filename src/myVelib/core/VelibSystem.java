package myVelib.core;

import myVelib.Exceptions.*;

import java.text.ParseException;
import java.util.*;
//可以指定一个或者随机一个大数，再加上原有从0开始的ID,不过这样取ID的时候要注意
//station要加上rental records 和returning records 为了之后展示数据.
//还车时暂未考虑plus staion,所以没有加上credits
public class VelibSystem implements java.io.Serializable{
	static final long serialVersionUID = 2326497858953073456L;
	private HashMap<Integer,Bicycle> bicycles;
	private HashMap<Integer,Station> stations;
	private HashMap<Integer,User> users;
	double length;
	double width;
//	ArrayList<Station> stations =new ArrayList<Station>();
//	ArrayList<User> users=new ArrayList<User>();
	String name;
	public VelibSystem() {
		this.setBicycles(new HashMap<Integer,Bicycle>());
		this.setStations(new HashMap<Integer,Station>());
		this.setUsers(new HashMap<Integer,User>());
	}
	public VelibSystem(String name) {
		this.setBicycles(new HashMap<Integer,Bicycle>());
		this.setStations(new HashMap<Integer,Station>());
		this.setUsers(new HashMap<Integer,User>());
		this.name=name;
	}
	public static void main(String args[]) throws ParseException, InvalidIDException, VacancyException, NoneParkingSlotException{
		VelibSystem v=new VelibSystem();
		v.setup(10, 10, 70);
		v.addUser("ls", null);
		v.rentbike(1, 1);
		String p=v.returnbike(1, 7, "2021-05-22/19:00:00");
		System.out.println(p);
	}
	public void generateStations(int nStations,int nSlots) {
		HashSet<Coordinates> c=new HashSet<Coordinates>();
		while(c.size()<nStations) {
			c.add(new Coordinates());
		}
		int id=0;
		for(Coordinates cs:c) {
			Station s=new Station(cs,nSlots,id,true);
			getStations().put(id,s);
			id+=1;
		}
	}
	public void distributeBikes(int nBikes) {
		int nMech=(int)(nBikes*0.7);
		int nElec=nBikes-nMech;
		int id=0;
		while(nMech>0) {
			for(Integer key:getStations().keySet()) {
				Station s=getStations().get(key);
				if (nMech>0) {
				Mechanical m=new Mechanical(id);
				s.addBicycle(m);
				getBicycles().put(id,m);
				id+=1;
				nMech-=1;
				}
			}
		}
		while(nElec>0) {
			for(Integer key:getStations().keySet()) {
				Station s=getStations().get(key);
				if (nElec>0) {
				Electrical e=new Electrical(id);
				s.addBicycle(e);
				getBicycles().put(id,e);
				id+=1;
				nElec-=1;
				}
			}
			}
		
	}
	public String setup(int nStations, int nSlots, int nBikes) {
		String returnvalue="Setting up System "+name+" number of stations is "+nStations+", number of slots is "+nSlots+", number of Bikes is "+nBikes ;
		this.generateStations(nStations, nSlots); 
		this.distributeBikes(nBikes);
		return returnvalue;
	}
// cardtype用字符串会更好些，不然没法直接解析CLUI参数
	public String addUser(String username,RegistrationCard card) {
		int id=getUsers().size()+1;
		User u=new User(id);
		u.setUsername(username);
		u.setCard(card);
		getUsers().put(id,u);
		return "user "+username+" is added..."+(card==null?"who has not a card yet":"whose cardtype is "+card.name);
	}
	public String offline(int stationID) throws InvalidIDException {
		Station s=getStations().get(stationID);
		if (s==null) {
			throw new InvalidIDException();
		}
		s.Online=false;
		return "Station ID: "+stationID+" is offline rightnow";
	}
	public String online(int stationID) throws InvalidIDException {
		Station s=getStations().get(stationID);
		if (s==null) {
			throw new InvalidIDException();
		}
		s.Online=false;
		return "Station ID: "+stationID+" is online rightnow";
	}
	public String rentbike(int userID,int stationID) throws InvalidIDException, VacancyException {		
		User u=getUsers().get(userID);
		Station s=getStations().get(stationID);
		if (s==null || u==null) {
			throw new InvalidIDException();
		}
		if (s.hasBikes()==false) {
			throw new VacancyException(s.ID);
		}
		int bicycleID=s.removeBicycle();
		Session sess=new Session(s,getBicycles().get(bicycleID));
		u.addSession(sess);
		return "user "+u.getUsername()+" rented a bike at "+"Station ID: "+stationID+". Time is "+sess.printStarttime();
		}
	public String returnbike(int userID,int stationID,String str) throws ParseException, InvalidIDException, NoneParkingSlotException {
		User u=getUsers().get(userID);
		Station s=getStations().get(stationID);
		if (s==null || u==null) {
			throw new InvalidIDException();
		}
		if (s.countFree()==0) {
			throw new NoneParkingSlotException(s.ID);
		}
		Session sess=u.getCurrentSession();
		sess.setEndtime(str);
		sess.setEndStation(s);
		sess.calculatePrice(u.getCard(), u.getCredits());
		return "user "+u.getUsername()+" returns a bike at "+"Station ID: "+stationID+". Time is "+str+"\n"+"the total price is "+sess.getPrice();
	}
	public HashMap<Integer,Bicycle> getBicycles() {
		return bicycles;
	}
	public void setBicycles(HashMap<Integer,Bicycle> bicycles) {
		this.bicycles = bicycles;
	}
	public HashMap<Integer,Station> getStations() {
		return stations;
	}
	public void setStations(HashMap<Integer,Station> stations) {
		this.stations = stations;
	}
	public HashMap<Integer,User> getUsers() {
		return users;
	}
	public void setUsers(HashMap<Integer,User> users) {
		this.users = users;
	}
}

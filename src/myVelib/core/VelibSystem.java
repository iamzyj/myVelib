package myVelib.core;

import myVelib.Exceptions.*;

import java.text.ParseException;
import java.util.*;
//可以指定一个或者随机一个大数，再加上原有从0开始的ID,不过这样取ID的时候要注意
//station要加上rental records 和returning records 为了之后展示数据.
public class VelibSystem implements java.io.Serializable{
	static final long serialVersionUID = 2326497858953073456L;
	private HashMap<Integer,Bicycle> bicycles;
	private HashMap<Integer,Station> stations;
	private HashMap<Integer,User> users;
	private ArrayList<Session> sessions;
	public double PlusStationPercentage=0.4;
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
	public static void main(String args[]) throws ParseException, InvalidIDException, VacancyException, NoneParkingSlotException, EndPriorToStartException, NoBikeToReturnException, RentMoreThanOneBikeException{
		VelibSystem v=new VelibSystem();
		v.setup(10, 10, 70);
		v.addUser("ls", null);
		v.rentbike(1, 1);
		String p=v.returnbike(1, 7, "2021-05-26/19:00:00");
		System.out.println(p);
	}
	public void setPlusStation() {
		int PlusStationNum=(int) Math.ceil(stations.size()*PlusStationPercentage);
		int counter=0;
		while(counter<PlusStationNum) {
			for(Integer key:getStations().keySet()) {
				Station s=getStations().get(key);
				double rand=Math.random();
				if(rand<=0.4) {
					s.isPlus=true;	
					counter+=1;
				}
				if(counter==PlusStationNum) {
					break;
				}
		}
		}
	}
	public void generateStations(int nStations,int nSlots) throws ParseException {
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
	public String setup(int nStations, int nSlots, int nBikes) throws ParseException {
		String returnvalue="Setting up System "+name+" number of stations is "+nStations+", number of slots is "+nSlots+", number of Bikes is "+nBikes ;
		this.generateStations(nStations, nSlots); 
		this.distributeBikes(nBikes);
		this.setPlusStation();
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
	public String rentbike(int userID,int stationID) throws InvalidIDException, VacancyException, RentMoreThanOneBikeException {	
		User u=getUsers().get(userID);
		Station s=getStations().get(stationID);
		if (s==null || u==null) {
			throw new InvalidIDException();
		}
		if (s.hasBikes()==false) {
			throw new VacancyException(s.ID);
		}
		if(u.hasRentedBefore()) {
			throw new RentMoreThanOneBikeException();
		}
		int bicycleID=s.removeBicycle();
		Session sess=new Session(s,getBicycles().get(bicycleID));
		s.rentNum+=1;
		u.addSession(sess);
		sessions.add(sess);
		return "user "+u.getUsername()+" rented a bike at "+"Station ID: "+stationID+". Time is "+sess.printStarttime();
		}
	public String returnbike(int userID,int stationID,String str) throws ParseException, InvalidIDException, NoneParkingSlotException, EndPriorToStartException, NoBikeToReturnException {
		User u=getUsers().get(userID);
		Station s=getStations().get(stationID);
		if (s==null || u==null) {
			throw new InvalidIDException();
		}
		if (s.countFree()==0) {
			throw new NoneParkingSlotException(s.ID);
		}
		Session sess=u.getCurrentSession();
		if (sess==null) {
			throw new NoBikeToReturnException();
		}
		sess.setEndtime(str);
		if (sess.getDuration()<=0) {
			throw new EndPriorToStartException(str);
		}
		else {
		sess.setEndStation(s);
		if (s.isPlus==true) {
			u.setCredits(u.getCredits()+5);
		}
		sess.calculatePrice(u.getCard(), u.getCredits());
		sess.setFinished(true);
		s.returnNum+=1;
		}
		return "user "+u.getUsername()+" returns a bike at "+"Station ID: "+stationID+". Time is "+str+"\n"+"the total price is "+sess.getPrice();
	}
	public String displayStation(int ID) {
		Station s=stations.get(ID);
		return ""+s.rentNum+s.returnNum;
	}
	public Map<Integer,Double> calAllStationBalance(int ID) throws ParseException {
		Map<Integer,Double> m=new HashMap<Integer,Double>();
		for(Integer key:getStations().keySet()) {
			Station s=getStations().get(key);
			s.calculateBalance();
			m.put(s.ID, s.occupationRate);
		}
		return m;
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

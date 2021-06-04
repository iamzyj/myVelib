/**
 * The core of the project which includes the method to maintain the system
 * @author Shuai
 */
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
	/**
	 * Empty constructor
	 */
	public VelibSystem() {
		this.setBicycles(new HashMap<Integer,Bicycle>());
		this.setStations(new HashMap<Integer,Station>());
		this.setUsers(new HashMap<Integer,User>());
		this.sessions=new ArrayList<Session>();
	}
	/**
	 * Constructor just indicate the name
	 * @param name of the system
	 */
	public VelibSystem(String name) {
		this.setBicycles(new HashMap<Integer,Bicycle>());
		this.setStations(new HashMap<Integer,Station>());
		this.setUsers(new HashMap<Integer,User>());
		this.name=name;
		this.sessions=new ArrayList<Session>();
	}
	public static void main(String args[]) throws ParseException, InvalidIDException, VacancyException, NoneParkingSlotException, EndPriorToStartException, NoBikeToReturnException, RentMoreThanOneBikeException{
		VelibSystem v=new VelibSystem();
		Map<Integer,Double> m=new HashMap<Integer,Double>();
		v.setup(10, 10, 70);
		v.addUser("ls", null);
		v.rentbike(1, 1);
		String p=v.returnbike(1, 7, "2021-06-16/19:00:00");
		m=v.calAllStationBalance();
		System.out.println(m.get(0));
		
	}
	/**
	 * Setup the plus stations with the ratio of 0.4
	 */
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
//		System.out.println(counter);
		}
	}
	/**
	 * Generate the stations of a system
	 * @param nStations number of stations
	 * @param nSlots number of slots in a station
	 * @throws ParseException
	 */
	public void generateStations(int nStations,int nSlots) throws ParseException {
		HashSet<Coordinates> c=new HashSet<Coordinates>();
		while(c.size()<nStations) {
			c.add(new Coordinates());
		}
		int id=1;
		for(Coordinates cs:c) {
			Station s=new Station(cs,nSlots,id,true);
			getStations().put(id,s);
			id+=1;
		}
	}
	/**
	 * Distribute bikes in the stations with mechanical bike's ratio of 0.7 
	 * @param nBikes number of bikes
	 * @throws ParseException
	 */
	public void distributeBikes(int nBikes) throws ParseException {
		BicycleFactory bf=new BicycleFactory();
		int nMech=(int)(nBikes*0.7);
		int nElec=nBikes-nMech;
		int id=1;
		while(nMech>0) {
			for(Integer key:getStations().keySet()) {
				Station s=getStations().get(key);
				if (nMech>0) {
				Mechanical m=(Mechanical) bf.createBike("M", id);
				s.setupBicycle(m);
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
				Electrical e=(Electrical) bf.createBike("E", id);
				s.setupBicycle(e);
				getBicycles().put(id,e);
				id+=1;
				nElec-=1;
				}
			}
			}
		
	}
	/**
	 * Setup the station of a given system
	 * @param nStations number of the stations in this system
	 * @param nSlots number of the slots in a station
	 * @param nBikes initial number of bikes in a station
	 * @return
	 * @throws ParseException
	 */
	public String setup(int nStations, int nSlots, int nBikes) throws ParseException {
		String returnvalue="Setting up System "+name+" number of stations is "+nStations+", number of slots is "+nSlots+", number of Bikes is "+nBikes ;
		this.generateStations(nStations, nSlots); 
		this.distributeBikes(nBikes);
		this.setPlusStation();
		return returnvalue;
	}
// cardtype用字符串会更好些，不然没法直接解析CLUI参数
	/**
	 * Add the user to a system, called by CLUI
	 * @param username name of user
	 * @param card card type of user
	 * @return result in CLUI
	 */
	public String addUser(String username,RegistrationCard card) {
		int id=getUsers().size()+1;
		User u=new User(id);
		u.setUsername(username);
		u.setCard(card);
		getUsers().put(id,u);
		return "user "+username+" is added..."+(card==null?"who has not a card yet":"whose cardtype is "+card.getName());
	}
	/**
	 * Offline a station
	 * @param stationID ID of the station that we want to set offline
	 * @return result in CLUI
	 * @throws InvalidIDException no such a ID in the system
	 */
	public String offline(int stationID) throws InvalidIDException {
		Station s=getStations().get(stationID);
		if (s==null) {
			throw new InvalidIDException();
		}
		s.Online=false;
		return "Station ID: "+stationID+" is offline rightnow";
	}
	/**
	 * Online a station
	 * @param stationID of which we want to set online
	 * @return result in CLUI
	 * @throws InvalidIDException no such a ID in the system
	 */
	public String online(int stationID) throws InvalidIDException {
		Station s=getStations().get(stationID);
		if (s==null) {
			throw new InvalidIDException();
		}
		s.Online=false;
		return "Station ID: "+stationID+" is online rightnow";
	}
	/**
	 * Rent a bike in a system
	 * @param userID user's ID in the system
	 * @param stationID ID of the station
	 * @return result in CLUI
	 * @throws InvalidIDException no such a ID in the system
	 * @throws VacancyException don't have a bike
	 * @throws RentMoreThanOneBikeException One user can't rent more than one bike
	 */
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
	/**
	 * return the bike in a system
	 * @param userID ID of the user
	 * @param stationID of the station
	 * @param str Time in string shape
	 * @return result inC CLUI
	 * @throws ParseException
	 * @throws InvalidIDException no such a ID in the system
	 * @throws NoneParkingSlotException no more free slot in that station
	 * @throws EndPriorToStartException should end a session before renting a new bike
	 * @throws NoBikeToReturnException no bike to return
	 */
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
		sess.calculatePrice(u.getCard(), u.getCredits());
//		add credits for the next ride
		if (s.isPlus==true) {
			u.setCredits(u.getCredits()+5);
		}
		sess.setFinished(true);
		s.addBicycle(sess);
		s.returnNum+=1;
		}
		return "user "+u.getUsername()+" returns a bike at "+"Station ID: "+stationID+". Time is "+str+"\n"+"the total price is "+sess.getPrice();
	}
	/**
	 * Display the station status
	 * @param ID of the station we want to inspect
	 * @return the state of the station, online/offline, plus/not
	 * @throws ParseException
	 */
	public String displayStation(int ID) throws ParseException {
		Station s=stations.get(ID);
		s.calculateBalance();
		return "Station ID"+ID+", currentState is "+(s.Online==true?"online":"offline")+(s.isPlus==true?", is plus station:":", is not plus station ")+", Number of free slots: "+s.countFree()+", TotalRentNum is "+s.rentNum+", TotalReturnNum is "+s.rentNum+s.returnNum+", OccupationRate is "+s.occupationRate;
	}
	/**
	 * Display the records of each session of a user
	 * @return each session of a user: start/end station, duration and price
	 */
	public String displayrecords() {
		String str="";
		for(Integer key:getUsers().keySet()){
			User u=getUsers().get(key);
			for(Session s:u.getSessions()) {
			str+="user: "+u.getUsername()+", startstation: "+s.getStartStation()+", endstation: "+s.getEndStation()+", duration: "+s.getDuration()+", price: "+s.getPrice()+"\n";
			}
		}
		return str;
	}
	/**
	 * Display the status of a user
	 * @param ID of the user
	 * @return credit, total rent number, total duration and total charge
	 */
	public String displayUser(int ID) {
		User u=getUsers().get(ID);
		int totalDuration=0;
		int totalRentNum=u.getSessions().size();
		double totalCharge=0.0;
		for(Session s:u.getSessions()) {
			totalDuration+=s.getDuration();
			totalCharge+=s.getPrice();
		}
		return "user "+u.getUsername()+", credit: "+u.getCredits()+", TotalRentNumber: "+totalRentNum+", TotalDuration: "+totalDuration+"mins"+", TotalCharge: "+totalCharge+"€";
	}
	/**
	 * Dispaly the status of a system
	 * @return All stations and the user in the system
	 * @throws ParseException
	 */
	public String displaySystem() throws ParseException {
		String str="";
		str+=displayAllStations();
		str+=displayAllUsers();
		return str;
	}
	/**
	 * Display all stations
	 * @return status of each station in the system
	 * @throws ParseException
	 */
	public String displayAllStations() throws ParseException {
		String str="";
		for(Integer key:getStations().keySet()) {
			str+=displayStation(key)+"\n";
		}
		return str;
	}
	/**
	 * Display all users in the system
	 * @return status of each user in the system
	 */
	public String displayAllUsers() {
		String str="";
		for(Integer key:getUsers().keySet()) {
			str+=displayUser(key)+"\n";
		}
		return str;
	}
	/**
	 * Calculate the balance of each station
	 * @return a map that mapping station's ID to its balance
	 * @throws ParseException
	 */
	public Map<Integer,Double> calAllStationBalance() throws ParseException {
		Map<Integer,Double> m=new HashMap<Integer,Double>();
		for(Integer key:getStations().keySet()) {
			Station s=getStations().get(key);
			s.calculateBalance();
			m.put(s.ID, s.occupationRate);
		}
		return m;
	}
	/**
	 * Sort the station with a policy
	 * @param p sorting policy
	 * @return sorted stations 
	 * @throws ParseException
	 */
	public String sortStation(SortPolicy p) throws ParseException {
		String str="";
		calAllStationBalance();
		ArrayList<Station> as=new ArrayList<Station>();
		for(Integer key:getStations().keySet()) {
			Station s=getStations().get(key);
			as.add(s);
		}
		p.sort(as);
		for (Station s:as) {
			str+="Station ID: "+s.ID+", ";
		}
		return str;
	}
	/**
	 * Calculate the ride planning given a policy, start/end coordinate
	 * @param p the ride planning strategy
	 * @param start start coordinate
	 * @param end end coordinate
	 * @return the sorted stations
	 * @throws ParseException
	 */
	public String rideplan(RideSortStrategy p, Coordinates start, Coordinates end) throws ParseException {
		String str="";
		RidePlanning plan = new RidePlanning(p, getStations(),start,end);
		plan.sortStation();
		str=plan.toString();
		return str;
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

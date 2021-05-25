package myVelib.core;

import java.util.*;

public class User implements java.io.Serializable{
	static final long serialVersionUID = 7326497358930734556L;
	private String username;
	private int ID;
	private Coordinates position;
	private double credits=0.0;
	private RegistrationCard card=null;
	private ArrayList<Session> sessions;
	public User(int ID) {
		this.ID=ID;
		this.position=new Coordinates();
		this.sessions=new ArrayList<Session>();
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Coordinates getPosition() {
		return position;
	}
	public void setPosition(Coordinates position) {
		this.position = position;
	}
	public double getCredits() {
		return credits;
	}
	public void setCredits(double credits) {
		this.credits = credits;
	}
	public RegistrationCard getCard() {
		return card;
	}
	public void setCard(RegistrationCard card) {
		this.card = card;
	}
	public void addSession(Session e) {
//		e.calculatePrice(this.card, this.credits);
		this.sessions.add(e);
	}
	public Session getCurrentSession() {
		int len=this.sessions.size();
		if(len>0) {			
			if(!this.sessions.get(len-1).isFinished()) {
				return this.sessions.get(len-1);
			}
			return null;
		}
		return null;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean hasRentedBefore() {
		for(Session s:sessions) {
			if(!s.isFinished()) {
				return true;
			}
		}
		return false;
	}
	
}

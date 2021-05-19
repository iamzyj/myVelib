package myVelib;

import java.util.*;

public class User {
	private int ID;
	private Coordinates position;
	private double credits=0.0;
	private RegistrationCard card=null;
	private ArrayList<Session> sessions;
	public User(int ID) {
		this.ID=ID;
		this.position=new Coordinates();
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
		e.calculatePrice(this.card, this.credits);
		this.sessions.add(e);
	}
	
}

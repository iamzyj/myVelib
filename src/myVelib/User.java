package myVelib;

public class User {
	private int ID;
	private Coordinates position;
	private int credits;
	private RegistrationCard card;
	public User(int ID) {
		this.ID=ID;
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
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public RegistrationCard getCard() {
		return card;
	}
	public void setCard(RegistrationCard card) {
		this.card = card;
	}

}

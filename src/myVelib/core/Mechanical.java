package myVelib.core;

public class Mechanical implements Bicycle,java.io.Serializable{
	static final long serialVersionUID = 6126497878902756542L;
	private int ID;
	public Mechanical(int ID) {
		this.ID=ID;
		// TODO Auto-generated constructor stub
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
}

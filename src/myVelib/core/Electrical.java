package myVelib.core;

public class Electrical implements Bicycle, java.io.Serializable{
	static final long serialVersionUID = 3126497878902756556L;
	private int ID;
	public Electrical(int ID) {
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

package myVelib.core;

import java.io.*;

public class Bicycle implements Serializable {
	static final long serialVersionUID = 3126998878902358585L;
	private int ID;
	public Bicycle(int ID) {
		super();
		this.ID = ID;
	}
	public int getID() {
		return ID;
	}
	
	
}

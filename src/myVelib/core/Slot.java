package myVelib.core;

import java.util.*;

public class Slot implements java.io.Serializable{
	static final long serialVersionUID = 2126497858930744556L;
	public int ID;
	public boolean free;
	public Bicycle bicycle;
	public ArrayList<Date> freeTimeInterval;
	public int freetime;
	public Slot() {
		super();
		freeTimeInterval=new ArrayList<Date>();
		freetime=0;
	}
}

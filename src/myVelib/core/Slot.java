/**
 * The slot of the stations where bikes are stored
 * @author Shuai
 */
package myVelib.core;

import java.util.*;

public class Slot implements java.io.Serializable{
	static final long serialVersionUID = 2126497858930744556L;
	/**
	 * All the attributes are public because they should open to all to simplify the inspection
	 */
	public int ID;
	public boolean free;
	public Bicycle bicycle;
	public ArrayList<Date> freeTimeInterval;
	public int freetime;
	/**
	 * Initial construction
	 */
	public Slot() {
		super();
		freeTimeInterval=new ArrayList<Date>();
		freetime=0;
	}
}

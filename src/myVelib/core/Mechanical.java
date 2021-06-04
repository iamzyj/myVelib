/**
 * Bicycle factory pattern to generate a mechanical bike
 * @author Yingjie
 */
package myVelib.core;

public class Mechanical implements Bicycle,java.io.Serializable{
	static final long serialVersionUID = 6126497878902756542L;
	private int ID;
	/**
	 * To construct a mechanical bike by indicating ID
	 * @param ID the ID of the bike
	 */
	public Mechanical(int ID) {
		this.ID=ID;
		// TODO Auto-generated constructor stub
	}
	/**
	 * Get the ID
	 * @return ID the ID of bike
	 */
	public int getID() {
		return ID;
	}
	/**
	 * Set the ID
	 * @param iD
	 */
	public void setID(int iD) {
		ID = iD;
	}
	
}

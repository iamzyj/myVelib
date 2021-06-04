/**
 * The electrical bike class
 * @author Shuai
 */
package myVelib.core;

public class Electrical implements Bicycle, java.io.Serializable{
	static final long serialVersionUID = 3126497878902756556L;
	/**
	 * The ID for each bike
	 */
	private int ID;
	/**
	 * Generate a electrical bike by indicating ID
	 * @param ID
	 */
	public Electrical(int ID) {
		this.ID=ID;
		// TODO Auto-generated constructor stub
	}
	/**
	 * Get the ID
	 * @return ID of the bike
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

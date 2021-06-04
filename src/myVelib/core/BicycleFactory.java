/** The factory pattern of bicycle
 * To generate the bicycle instance
 * @author Shuai
 */
package myVelib.core;



public class BicycleFactory implements java.io.Serializable {
	private static final long serialVersionUID = -4316815741584342228L;
	/**
	 * Empty constructor
	 */
	public BicycleFactory() {
		super();
	}
	/**
	 * To create a bike instance
	 * @param bikeType, the type as mechanical or electrical
	 * @param ID of the bike
	 * @return a instance of proper type of Bike
	 */
	public Bicycle createBike(String bikeType,int ID) {
		if(bikeType.equalsIgnoreCase("E")) {
			return new Electrical(ID);
		}else if(bikeType.equalsIgnoreCase("M")) {
			return new Mechanical(ID);
		}else {
			System.err.println("Biketype unrecognized....");
			return null;
		}
	}
}

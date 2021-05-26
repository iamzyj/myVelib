package myVelib.core;



public class BicycleFactory implements java.io.Serializable {
	private static final long serialVersionUID = -4316815741584342228L;
	public BicycleFactory() {
		super();
	}
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

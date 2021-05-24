package myVelib.Exceptions;

public class NoneParkingSlotException extends Exception{
 static final long serialVersionUID = 4324877753508104110L;
	private int stationID;
	public NoneParkingSlotException(int stationID) {
		this.stationID=stationID;
		System.err.println("the station "+stationID+" has no more parking slots,please change to anothor ");
	}
}

package myVelib.Exceptions;

public class VacancyException extends Exception{
	private int stationID;
	public VacancyException(int stationID) {
		this.stationID=stationID;
		System.err.println("the station "+stationID+" has no more bikes,please change to anothor ");
	}
}

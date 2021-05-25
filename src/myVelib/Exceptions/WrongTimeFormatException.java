package myVelib.Exceptions;

public class WrongTimeFormatException extends Exception {
	private String t="yyyy-MM-dd/HH:mm:ss";
	private static final long serialVersionUID = 7165545894389714417L;
	public WrongTimeFormatException() {
		System.err.println("The time format is invalid, please follow the right one of "+t);
		
	}
}

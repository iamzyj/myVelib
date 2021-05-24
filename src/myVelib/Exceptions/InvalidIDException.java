package myVelib.Exceptions;

public class InvalidIDException extends Exception{
	private static final long serialVersionUID = 6995844822578555320L;

	public InvalidIDException() {
		super();
		System.err.println("Please enter a valid ID");
	}
}

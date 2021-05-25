package myVelib.Exceptions;

public class RentMoreThanOneBikeException extends Exception {
	private static final long serialVersionUID = 3562096803135570935L;
	public RentMoreThanOneBikeException() {
		System.err.println("One user cannot rent more than one bike at the same time....");
	}
}

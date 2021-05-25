package myVelib.Exceptions;

public class NoBikeToReturnException extends Exception {
	private static final long serialVersionUID = -6336493107876011158L;
	public NoBikeToReturnException() {
		System.err.println("No bike to return, please register the correct information!");
		
	}
}

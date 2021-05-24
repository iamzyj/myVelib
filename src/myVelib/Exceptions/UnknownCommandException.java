package myVelib.Exceptions;

public class UnknownCommandException extends Exception{
	private static final long serialVersionUID = 7858947797464338144L;
	public String name;
	public UnknownCommandException(String commandName) {
		super();
		this.name=commandName;
	}
}

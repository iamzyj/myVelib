package myVelib.core;

public class RegistrationCard implements java.io.Serializable{
	static final long serialVersionUID = 3126497838102773556L;
	private String name;
	public RegistrationCard() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

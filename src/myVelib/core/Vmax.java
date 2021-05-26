package myVelib.core;

public class Vmax implements RegistrationCard{
	private static final long serialVersionUID = 7830221119697303123L;
	private String name;
	public Vmax() {
		super();
		this.setName("Vmax");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
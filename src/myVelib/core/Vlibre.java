package myVelib.core;

import java.util.*;

public class Vlibre extends RegistrationCard{
	private static final long serialVersionUID = 1382438272515045956L;
	private String name;
	public Vlibre() {
		super();
		this.setName("Vlibre");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

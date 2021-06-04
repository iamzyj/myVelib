/**
 * Realization of the Vlibre card
 * @author Yingjie
 */
package myVelib.core;

import java.util.*;

public class Vlibre implements RegistrationCard{
	private static final long serialVersionUID = 1382438272515045956L;
	private String name;
	/**
	 * empty constructor
	 */
	public Vlibre() {
		super();
		this.setName("Vlibre");
	}
	/**
	 * get the name of the card
	 * @return get the name
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

/**
 * realization of the Vmax card
 * @author Shuai
 */
package myVelib.core;

public class Vmax implements RegistrationCard{
	private static final long serialVersionUID = 7830221119697303123L;
	private String name;
	/**
	 * Empty constructor
	 */
	public Vmax() {
		super();
		this.setName("Vmax");
	}
	/**
	 * Get the name of the card
	 * @return name of the card
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
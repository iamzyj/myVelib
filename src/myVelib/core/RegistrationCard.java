/**
 * The interface of Card factory pattern
 * @author Shuai
 */
package myVelib.core;

public interface RegistrationCard {
	/**
	 * To get the name of the card
	 * @return the name of the card
	 */
	public String getName();
	/**
	 * To set the name of the card
	 * @param name name of the card
	 */
	public void setName(String name);
}

/** The factory pattern of the card
 * To generate a card instance
 * @author Shuai
 */
package myVelib.core;

public class CardFactory implements java.io.Serializable{
	private static final long serialVersionUID = -7769247109945047671L;
	/**
	 * To generate an instance of card
	 * @param cardtype, Vlibre or Vmax
	 * @return the card instance
	 */
	public static RegistrationCard createcard(String cardtype) {
		if(cardtype.equals("Vlibre")) {
			return new Vlibre();
		}
		else if(cardtype.equals("Vmax")) {
			return new Vmax();
		}
		else {
			return null;
		}
	}
}

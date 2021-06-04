/**
 * The interface of the stations sorting strategy pattern
 * @author Shuai
 */
package myVelib.core;
import java.util.*;
public interface SortPolicy{
	/**
	 * The method
	 * @param stations all stations of the system
	 * @return the sorted stations
	 */
	public ArrayList<Station> sort(ArrayList<Station> stations);
}

/**
 * The interface of the ride planning strategy pattern
 * @author Yingjie
 */
package myVelib.core;

import java.util.HashMap;
import java.util.TreeMap;

public interface RideSortStrategy {
	/**
	 * To sort the start stations
	 * @param stations the stations of the system
	 * @param start start coordinate
	 * @return sorted stations by increasing distance
	 */
	public TreeMap<Double,Station> SortStart(HashMap<Integer,Station> stations, Coordinates start);
	/**
	 * To sort the end stations
	 * @param stations the stations of the system
	 * @param end end coordinate
	 * @return sorted stations by increasing distance
	 */
	public TreeMap<Double,Station> SortEnd(HashMap<Integer,Station> stations, Coordinates end);
}

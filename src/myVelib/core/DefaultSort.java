/**
 * This is the default sort policy of ride planning which consider all avaliable stations
 * @author Yingjie
 */
package myVelib.core;

import java.util.HashMap;
import java.util.TreeMap;

public class DefaultSort implements RideSortStrategy{
	/**
	 * Sort the start stations
	 * @param stations the all available stations
	 * @param start the coordinate of user
	 * @return the stations with distance increasing
	 */
	@Override
	public TreeMap<Double, Station> SortStart(HashMap<Integer, Station> stations, Coordinates start) {
		// TODO Auto-generated method stub
		HashMap<Double, Station> sortStation = new HashMap<Double,Station>();
		

		for (Integer key : stations.keySet()) {
			
			Station tempStation=stations.get(key);
			if (tempStation.hasBikes()==true) {
				sortStation.put(tempStation.Co.getDistance(start), tempStation);
			}
		}
		
		TreeMap<Double, Station> sortedMap = new TreeMap<Double,Station>(sortStation);
		
		
		return sortedMap;
	}
	/**
	 * Sort the end stations
	 * @param stations the all available stations
	 * @param end the coordinate of user's destination
	 * @return the stations with distance increasing
	 */
	@Override
	public TreeMap<Double, Station> SortEnd(HashMap<Integer, Station> stations, Coordinates end) {
		// TODO Auto-generated method stub
		HashMap<Double, Station> sortStation = new HashMap<Double,Station>();
		

		for (Integer key : stations.keySet()) {
			
			Station tempStation=stations.get(key);
			if (tempStation.countFree()!=0 ) {
				sortStation.put(tempStation.Co.getDistance(end), tempStation);
			}
		}
		
		TreeMap<Double, Station> sortedMap = new TreeMap<Double,Station>(sortStation);
		
		
		return sortedMap;
	}

}

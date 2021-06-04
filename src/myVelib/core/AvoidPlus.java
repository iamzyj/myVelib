/** AvoidPlus sort policy
 * A policy that avoid the plus station
 * @author Yingjie
 */
package myVelib.core;

import java.util.HashMap;
import java.util.TreeMap;

public class AvoidPlus implements RideSortStrategy{
	/**
	 * To sort the start station
	 * @param stations are all stations in the system, start is the coordinate of the user
	 * @return returns the target stations without Plus, distance increasing
	 */
	@Override
	public TreeMap<Double, Station> SortStart(HashMap<Integer, Station> stations, Coordinates start) {
		// TODO Auto-generated method stub
		HashMap<Double, Station> sortStation = new HashMap<Double,Station>();
		

		for (Integer key : stations.keySet()) {
			
			Station tempStation=stations.get(key);
			if (tempStation.hasBikes()==true && tempStation.isPlus==false) {
				sortStation.put(tempStation.Co.getDistance(start), tempStation);
			}
		}
		
		TreeMap<Double, Station> sortedMap = new TreeMap<Double,Station>(sortStation);
		
		
		return sortedMap;
	}
	/**
	 * To sort the end station
	 * @param stations are all stations in the system, end is the coordinate of the user's destination
	 * @return returns the target stations without Plus, distance increasing
	 */
	@Override
	public TreeMap<Double, Station> SortEnd(HashMap<Integer, Station> stations, Coordinates end) {
		// TODO Auto-generated method stub
		
		HashMap<Double, Station> sortStation = new HashMap<Double,Station>();
		

		for (Integer key : stations.keySet()) {
			
			Station tempStation=stations.get(key);
			if (tempStation.countFree()!=0 && tempStation.isPlus==false) {
				sortStation.put(tempStation.Co.getDistance(end), tempStation);
			}
		}
		
		TreeMap<Double, Station> sortedMap = new TreeMap<Double,Station>(sortStation);
		
		
		return sortedMap;
		
		

	}

}

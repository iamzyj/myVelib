package myVelib.core;

import java.util.HashMap;
import java.util.TreeMap;

public class DefaultSort implements RideSortStrategy{

	@Override
	public TreeMap<Double, Station> SortStart(HashMap<Integer, Station> stations, Coordinates start) {
		// TODO Auto-generated method stub
		HashMap<Double, Station> sortStation = new HashMap<Double,Station>();
		

		for (Integer key : stations.keySet()) {
			
			Station tempStation=stations.get(key);
			if (tempStation.hasBikes==true)
				sortStation.put(tempStation.Co.getDistance(start), tempStation);
		}
		
		TreeMap<Double, Station> sortedMap = new TreeMap<Double,Station>(sortStation);
		
		
		return sortedMap;
	}

	@Override
	public TreeMap<Double, Station> SortEnd(HashMap<Integer, Station> stations, Coordinates end) {
		// TODO Auto-generated method stub
HashMap<Double, Station> sortStation = new HashMap<Double,Station>();
		

		for (Integer key : stations.keySet()) {
			
			Station tempStation=stations.get(key);
			if (tempStation.countFree()!=0 )
				sortStation.put(tempStation.Co.getDistance(end), tempStation);
		}
		
		TreeMap<Double, Station> sortedMap = new TreeMap<Double,Station>(sortStation);
		
		
		return sortedMap;
	}

}

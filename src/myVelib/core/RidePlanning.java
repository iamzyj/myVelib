package myVelib.core;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RidePlanning {
	
	public TreeMap<Double,Station> Sort(HashMap<Integer,Station> stations, Coordinates start){
		
		HashMap<Double, Station> sortStation = new HashMap<>();
		

		for (Integer key : stations.keySet()) {
			
			Station tempStation=stations.get(key);
			if (tempStation.hasBikes==true)
				sortStation.put(tempStation.Co.getDistance(start), tempStation);
		}
		
		TreeMap<Double, Station> sortedMap = new TreeMap<>(sortStation);
		
		
		
		
		return sortedMap;
		
	}
	
	
	
	
	public HashMap<String,Station> Planning(HashMap<Integer,Station> stations, Coordinates start, Coordinates end){
		
		System.out.println("Would you like to avoid plus station? (yes/no):");
		System.out.println("Would you like to prefer plus station? (yes/no):");
		TreeMap<Double, Station> sortedStart =  Sort(stations, start);
		TreeMap<Double, Station> sortedEnd =	Sort(stations, end);
		
		HashMap<String,Station> StartEnd = new HashMap<>();
		
		for (Double key : sortedStart.keySet()) {
			
			Station tempStation=sortedStart.get(key);
			StartEnd.put("Start", tempStation);
			break;
		}
		
		
		for (Double key : sortedEnd.keySet()) {
			
			Station tempStation=sortedEnd.get(key);
			StartEnd.put("End", tempStation);
			break;
		}
		
		return StartEnd;
		
	}
}

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
	
	
	
@SuppressWarnings("unused")
public TreeMap<Double,Station> filter(TreeMap<Double,Station> stations, int flag){
		
		HashMap<Double, Station> sortStation = new HashMap<>();
		
		
		if (flag==0)
			return stations;
		else if (flag==1) {
			for (Double key : stations.keySet()) {
				
				Station tempStation=stations.get(key);
				if (tempStation.isPlus==true)
					stations.remove(key);
				else
					break;
			}
			
			return stations;
		}
		
		else if (flag==2) {
			int cnt=0;
			double mindis = 0;
			for (Double key : stations.keySet()) {		
				Station tempStation=stations.get(key);
				if (cnt==0)
				{
					if (tempStation.isPlus==true){
						return stations;
					}
					else {
						mindis=key;
					}
					cnt++;
					break;
				}
				if (tempStation.isPlus==true) {
					if (key>1.1*mindis) {
						return stations;
					}
					else {
						for (int i=0;i<cnt;i++) {
							for (Double key1 : stations.keySet()) {
									stations.remove(key1);
									break;
							}
							return stations;
						}
					}
				}
				cnt++;	
			}
			
			
			
		}
		return stations;
	}
	
	
	
	
	public HashMap<String,Station> Planning(HashMap<Integer,Station> stations, Coordinates start, Coordinates end){
		int flag;//flag==0 : normal   flag==1 avoid plus   flag==2 prefer plus  flag==3 balance;
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

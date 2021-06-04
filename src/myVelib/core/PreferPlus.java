package myVelib.core;

import java.util.HashMap;
import java.util.TreeMap;

public class PreferPlus implements RideSortStrategy{

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
		int cnt=0;
		double mindis = 0;
		for (Double key : sortedMap.keySet()) {		
			Station tempStation=sortedMap.get(key);
			if (cnt==0)
			{
				if (tempStation.isPlus==true){
					return sortedMap;
				}
				else {
					mindis=key;
				}
				cnt++;
				break;
			}
			if (tempStation.isPlus==true) {
				if (key>1.1*mindis) {
					return sortedMap;
				}
				else {
					for (int i=0;i<cnt;i++) {
						for (Double key1 : sortedMap.keySet()) {
							sortedMap.remove(key1);
								break;
						}
						return sortedMap;
					}
				}
			}
			cnt++;	
		}
		
		return sortedMap;
	}

	@Override
	public TreeMap<Double, Station> SortEnd(HashMap<Integer, Station> stations, Coordinates end) {
		// TODO Auto-generated method stub
		HashMap<Double, Station> sortStation = new HashMap<Double,Station>();
		for (Integer key : stations.keySet()) {
			Station tempStation=stations.get(key);
			if (tempStation.countFree()!=0) {
				sortStation.put(tempStation.Co.getDistance(end), tempStation);
			}
		}
		
		TreeMap<Double, Station> sortedMap = new TreeMap<Double,Station>(sortStation);
		int cnt=0;
		double mindis = 0;
		for (Double key : sortedMap.keySet()) {		
			Station tempStation=sortedMap.get(key);
			if (cnt==0)
			{
				if (tempStation.isPlus==true){
					return sortedMap;
				}
				else {
					mindis=key;
				}
				cnt++;
				break;
			}
			if (tempStation.isPlus==true) {
				if (key>1.1*mindis) {
					return sortedMap;
				}
				else {
					for (int i=0;i<cnt;i++) {
						for (Double key1 : sortedMap.keySet()) {
							sortedMap.remove(key1);
								break;
						}
						return sortedMap;
					}
				}
			}
			cnt++;	
		}
		
		return sortedMap;
	}

}

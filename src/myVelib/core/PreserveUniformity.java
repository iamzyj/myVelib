package myVelib.core;

import java.util.HashMap;
import java.util.TreeMap;

public class PreserveUniformity implements SortStrategy{

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
		TreeMap<Double, Station> sortedMap1 = new TreeMap<Double,Station>();
		Double firstkey=0.0;
		
		for (Double key : sortedMap.keySet() )
		{
			firstkey=key;
			break;
		}
		Station firstStation=sortedMap.get(firstkey);
		
		for (Double key : sortedMap.keySet() )
		{
			if (key!=firstkey && key<=1.05*firstkey ) {
				Station tempStation=sortedMap.get(key);
				if ((tempStation.slot_num-tempStation.countFree())>(firstStation.slot_num-firstStation.countFree()))
					sortedMap1.put(key, tempStation);
			}
		}
		if (sortedMap1.size()==0)
			return sortedMap;
		return sortedMap1;
		
	}

	@Override
	public TreeMap<Double, Station> SortEnd(HashMap<Integer, Station> stations, Coordinates end) {
		// TODO Auto-generated method stub
		HashMap<Double, Station> sortStation = new HashMap<Double,Station>();
		

		for (Integer key : stations.keySet()) {
			
			Station tempStation=stations.get(key);
			if (tempStation.countFree()!=0)
				sortStation.put(tempStation.Co.getDistance(end), tempStation);
		}
		
		TreeMap<Double, Station> sortedMap = new TreeMap<Double,Station>(sortStation);
		TreeMap<Double, Station> sortedMap1 = new TreeMap<Double,Station>();
		Double firstkey=0.0;
		
		for (Double key : sortedMap.keySet() )
		{
			firstkey=key;
			break;
		}
		Station firstStation=sortedMap.get(firstkey);
		
		for (Double key : sortedMap.keySet() )
		{
			if (key!=firstkey && key<=1.05*firstkey ) {
				Station tempStation=sortedMap.get(key);
				if ((tempStation.countFree())>(firstStation.countFree()))
					sortedMap1.put(key, tempStation);
			}
		}
		if (sortedMap1.size()==0)
			return sortedMap;
		return sortedMap1;
	}

}

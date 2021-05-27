package myVelib.core;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RidePlanning {
	
	SortStrategy strategy;
	HashMap<Integer, Station> stations;
	TreeMap<Double, Station> SortStart;
	TreeMap<Double, Station> SortEnd;
	Coordinates start;
	Coordinates end;
	
	
	public RidePlanning(SortStrategy strategy, HashMap<Integer, Station> stations, Coordinates start, Coordinates end) {
		super();
		this.strategy = strategy;
		this.stations = stations;
		this.start = start;
		this.end = end;
	}


	public void sortStation() {
		this.SortStart=strategy.SortStart(stations,start);
		this.SortEnd=strategy.SortEnd(stations,end);
	}
	
		
}

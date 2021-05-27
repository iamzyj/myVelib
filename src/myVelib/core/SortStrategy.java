package myVelib.core;

import java.util.HashMap;
import java.util.TreeMap;

public interface SortStrategy {
	public TreeMap<Double,Station> SortStart(HashMap<Integer,Station> stations, Coordinates start);
	public TreeMap<Double,Station> SortEnd(HashMap<Integer,Station> stations, Coordinates end);
}

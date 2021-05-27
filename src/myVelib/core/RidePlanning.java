package myVelib.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class RidePlanning {
	
	RideSortStrategy strategy;
	HashMap<Integer, Station> stations;
	TreeMap<Double, Station> SortStart;
	TreeMap<Double, Station> SortEnd;
	Coordinates start;
	Coordinates end;
	
	
	public RidePlanning(RideSortStrategy strategy, HashMap<Integer, Station> stations, Coordinates start, Coordinates end) {
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


	@Override
	public String toString() {
		String str = "Start:";//用于存放遍历出来的字符串
        Iterator<Double>  iterator = this.SortStart.keySet().iterator();
        while (iterator.hasNext()) {
            //2、拼接字符串
        	str +=iterator.toString()+":";
            str += this.SortStart.get(iterator.next()).toString()+",";
        }
        str+="\nEnd:";
        Iterator<Double>  iterator1 = this.SortEnd.keySet().iterator();
        while (iterator1.hasNext()) {
            //2、拼接字符串
        	str +=iterator1.toString()+":";
            str += this.SortEnd.get(iterator1.next()).toString()+",";
        }
        str+="\n";
		return str;
	}
	
		
}

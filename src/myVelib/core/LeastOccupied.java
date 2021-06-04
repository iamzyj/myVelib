/**
 * station sorting strategy pattern by least occupied
 * @author Shuai
 */
package myVelib.core;

import java.util.*;

public class LeastOccupied implements SortPolicy,Comparator<Station>,java.io.Serializable{
	private static final long serialVersionUID = 8500198120324613764L;
	@Override
	/**
	 * The compare method
	 */
	public int compare(Station o1, Station o2) {
		if(o1.occupationRate>o2.occupationRate) {
			return -1;
		}
		else if(o1.occupationRate==o2.occupationRate){
			return 0;
		}
		else {
			return 1;
		}
	}
	/**
	 * Sorting the stations with least occupied standard
	 * @param stations list of stations
	 * @return sorted stations
	 */
	@Override
	public ArrayList<Station> sort(ArrayList<Station> stations) {
		LeastOccupied ls=new LeastOccupied();
		Collections.sort(stations,ls);
		return stations;
	}

}

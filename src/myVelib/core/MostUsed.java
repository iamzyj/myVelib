/**
 * Station sorting policy by most used standard
 * @author Shuai
 */
package myVelib.core;

import java.util.*;

public class MostUsed implements SortPolicy,Comparator<Station>,java.io.Serializable{
private static final long serialVersionUID = 5471272137897189422L;
	/**
	 * The compare method
	 */
	@Override
	public int compare(Station o1, Station o2) {
		if((o1.rentNum+o1.returnNum)>(o2.rentNum+o2.returnNum)) {
			return -1;
		}
		else if((o1.rentNum+o1.returnNum)==(o2.rentNum+o2.returnNum)) {
			return 0;
		}
		else {
			return 1;	
		}
	}
	/**
	 * To sort the stations by most used standard
	 * @param stations the list of stations
	 * @return stations sorted stations
	 */
	@Override
	public ArrayList<Station> sort(ArrayList<Station> stations) {
		MostUsed ms=new MostUsed();
		Collections.sort(stations,ms);
		return stations;
	}

}

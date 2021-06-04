/**
 * To generate the time by java.util
 * @author Shuai
 */
package myVelib.core;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//when calling Time. use time1.time to call the Date Attribute
public class Time implements java.io.Serializable{
	static final long serialVersionUID = 2325197258900734556L;
	public Date time;
	public String format="yyyy-MM-dd/HH:mm:ss";
	/**
	 * Construct the Time
	 */
	public Time() {
		super();
		this.time=new Date();
	}
	/**
	 * Construct the Time by a format
	 * @param s the format
	 * @throws ParseException
	 */
	public Time(String s) throws ParseException {
		super();
		SimpleDateFormat sdf = new SimpleDateFormat(this.format);
		try {
		this.time=sdf.parse(s);
		}catch(ParseException e) {
			System.err.println("The time format is invalid, please follow the right one of "+format);
		}
	}

}

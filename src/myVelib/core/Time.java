package myVelib.core;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//when calling Time. use time1.time to call the Date Attribute
public class Time implements java.io.Serializable{
	static final long serialVersionUID = 2325197258900734556L;
	public Date time;
	public String format="yyyy-MM-dd/HH:mm:ss";
	public Time() {
		super();
		this.time=new Date();
	}
	public Time(String s) throws ParseException {
		super();
		SimpleDateFormat sdf = new SimpleDateFormat(this.format);
		this.time=sdf.parse(s);
	}

}

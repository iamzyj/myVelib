package myVelib;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Time{
	public Date time;
	public String format="yyyy-MM-dd HH:mm:ss";
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

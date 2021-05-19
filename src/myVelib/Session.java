package myVelib;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Session {
	private Station startStation;
	private Station endStation;
	private int duration;
	private Time starttime;
	private Time endtime;
	private Bicycle bicycle;
	public Session(Station startStation,Station endStation,Bicycle bicycle) {
		this.startStation=startStation;
		this.endStation=endStation;
		this.setStarttime(new Time());
		this.bicycle=bicycle;
	}
	
	public static void main(String[] args) throws ParseException {
        
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间 
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记  
        Date date = new Date();// 获取当前时间 
        
        System.out.println("现在时间：" + sdf.format(date)); // 输出已经格式化的现在时间（24小时制）
        SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy年MM月dd日");                
        //字符串变为时间Date类,解析p格式化f,pf        
        String birthday = "1995年05月02日";        
        Date d = sdf1.parse(birthday);                
        //获得时间毫秒值        
        long myTime = d.getTime();        
        //当前日期毫秒值        
        long currentTime = new Date().getTime();        
        System.out.println((currentTime-myTime)/1000/60);    
    } 
	public Station getStartStation() {
		return startStation;
	}
	public void setStartStation(Station startStation) {
		this.startStation = startStation;
	}
	public Station getEndStation() {
		return endStation;
	}
	public void setEndStation(Station endStation) {
		this.endStation = endStation;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Bicycle getBicycle() {
		return bicycle;
	}
	public void setBicycle(Bicycle bicycle) {
		this.bicycle = bicycle;
	}
	public Time getStarttime() {
		return starttime;
	}
	public void setStarttime(Time time) {
		this.starttime = time;
	}
	public Time getEndtime() {
		return endtime;
	}
	public void setEndtime(String str) throws ParseException {
		Time endtime=new Time(str);
		this.endtime = endtime;
		long s=this.starttime.getTime();
		long e=this.endtime.getTime();
		this.duration=(int)(e-s)/1000/60;
	}
	
}

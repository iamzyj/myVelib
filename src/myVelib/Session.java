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
	private double price;
	public Session(Station startStation,Station endStation,Bicycle bicycle) {
		this.startStation=startStation;
		this.endStation=endStation;
		this.setStarttime(new Time());
		this.bicycle=bicycle;
	}
	public Session() {
		this.setStarttime(new Time());
	}
	
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Session s1=new Session();
		s1.setEndtime("2021-05-20 12:34:56");
		System.out.println(s1.duration);
		System.out.println(sdf.format(s1.endtime.time));
		
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
		long s=this.starttime.time.getTime();
		long e=this.endtime.time.getTime();
		this.duration=(int)((e-s)/1000/60);
	}
	public void calculatePrice(RegistrationCard r,double credit) {
		if (r==null) {
			if(this.bicycle instanceof Electrical) {
				double temp=Math.ceil((this.duration-credit)/60);
				this.setPrice(2*temp);
			}
			if(this.bicycle instanceof Mechanical) {
				double temp=Math.ceil((this.duration-credit)/60);
				this.setPrice(1*temp);
			}
		}
		if(r instanceof Vlibre) {
			if(this.bicycle instanceof Electrical) {
				double temp=Math.ceil((this.duration-credit)/60-1);
				this.setPrice(temp>0.0?2*temp+1:1.0);
			}
			if(this.bicycle instanceof Mechanical) {
				double temp=Math.ceil((this.duration-credit)/60-1);
				this.setPrice(temp>0.0?1*temp:0.0);
			}
		}
		if(r instanceof Vmax) {
			double temp=Math.ceil((this.duration-credit)/60-1);
			this.setPrice(temp>0.0?1*temp:0.0);
		}
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}

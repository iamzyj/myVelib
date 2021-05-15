package myVelib;

import java.util.*;

public class Session {
	private Station startStation;
	private Station endStation;
	private long duration;
	private Bicycle bicycle;
	public Session(Station startStation,Station endStation,long duraiton,Bicycle bicycle) {
		this.startStation=startStation;
		this.endStation=endStation;
		this.duration=duration;
		this.bicycle=bicycle;
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
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public Bicycle getBicycle() {
		return bicycle;
	}
	public void setBicycle(Bicycle bicycle) {
		this.bicycle = bicycle;
	}
	
}

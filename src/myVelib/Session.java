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
        
        SimpleDateFormat sdf = new SimpleDateFormat();// ��ʽ��ʱ�� 
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// aΪam/pm�ı��  
        Date date = new Date();// ��ȡ��ǰʱ�� 
        
        System.out.println("����ʱ�䣺" + sdf.format(date)); // ����Ѿ���ʽ��������ʱ�䣨24Сʱ�ƣ�
        SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy��MM��dd��");                
        //�ַ�����Ϊʱ��Date��,����p��ʽ��f,pf        
        String birthday = "1995��05��02��";        
        Date d = sdf1.parse(birthday);                
        //���ʱ�����ֵ        
        long myTime = d.getTime();        
        //��ǰ���ں���ֵ        
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

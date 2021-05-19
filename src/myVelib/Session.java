package myVelib;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Session {
	private Station startStation;
	private Station endStation;
	private long duration;
	private Date starttime;
	private Date endtime;
	private Bicycle bicycle;
	public Session(Station startStation,Station endStation,Bicycle bicycle) {
		this.startStation=startStation;
		this.endStation=endStation;
		this.setStarttime(new Date());
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
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public Bicycle getBicycle() {
		return bicycle;
	}
	public void setBicycle(Bicycle bicycle) {
		this.bicycle = bicycle;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
}
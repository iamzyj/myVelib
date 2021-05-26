package myVelib.core;

public class Coordinates implements java.io.Serializable{
	static final long serialVersionUID = 3126497878902358585L;
	private double x,y;

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public Coordinates() {
		this.x=4*Math.random();
		this.y=4*Math.random();
	}
	
	public double getDistance(Coordinates co) {
		
		
		return Math.sqrt((this.x-co.getX())*(this.x-co.getX())+(this.y-co.getY())*(this.y-co.getY()));
	}

	public Coordinates(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	public static void main(String[] args) {
		Coordinates c=new Coordinates();
		System.out.println(c.getX());
		Coordinates d=new Coordinates();
		System.out.println(d.getX());
		System.out.println(c.getDistance(d));
	}
}

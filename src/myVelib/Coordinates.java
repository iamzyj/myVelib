package myVelib;

public class Coordinates {
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
	

	public Coordinates(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	public static void main(String[] args) {
		Coordinates c=new Coordinates();
		System.out.println(c.getX());
	}
}

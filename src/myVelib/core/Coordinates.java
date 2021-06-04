/** The coordinates class
 * To define the coordinate of the user or station
 * @author Shuai
 */
package myVelib.core;

public class Coordinates implements java.io.Serializable{
	static final long serialVersionUID = 3126497878902358585L;
	/**
	 * x,y the coordinates
	 */
	private double x,y;
	/**
	 * get X coordinate
	 * @return X coordinate
	 */
	public double getX() {
		return x;
	}
	/**
	 * set X coordinate
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * get Y coordinate
	 * @return Y coordinate
	 */
	public double getY() {
		return y;
	}
	/**
	 * set Y coordinate
	 * @param y coordinate
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Default constructor, randomly for a station within 4 km.
	 */
	public Coordinates() {
		this.x=4*Math.random();
		this.y=4*Math.random();
	}
	/**
	 * To calculate distance between the current coordinate to the target coordinate
	 * @param co, the target coordinate
	 * @return distance between each other
	 */
	public double getDistance(Coordinates co) {
		
		
		return Math.sqrt((this.x-co.getX())*(this.x-co.getX())+(this.y-co.getY())*(this.y-co.getY()));
	}
	/**
	 * Set the coordinates by indicating x and y
	 * @param x
	 * @param y
	 */
	public Coordinates(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	/**
	 * Rewriting toString function
	 */
	@Override
	public String toString() {
		return "("+this.getX()+","+this.getY()+")";
	}
	public static void main(String[] args) {
		Coordinates c=new Coordinates();
		System.out.println(c.getX());
		Coordinates d=new Coordinates();
		System.out.println(d.getX());
		System.out.println(c.getDistance(d));
	}
}

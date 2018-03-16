import java.awt.Point;

import com.sun.javafx.geom.Vec2d;

/*
 * Body.java
 * Author: Jeremiah Hanson
 * --------------------------------------------------
 * This is used to store object data for a body
 */

public class Body {

	double mass;
	int size;
	Vec2d vel;
	Point pos;
	
	/*
	 * Constructor
	 * Author: Jeremiah Hanson
	 * ---------------------------------------
	 * constructor
	 * Parameters:
	 * 	mass: mass of the body
	 * 	vel: velocity of the body
	 * 	pos: position of the body
	 * 	size: size of the body
	 */
	public Body(double mass, Vec2d vel, Point pos, int size) {
		this.mass = mass;
		this.vel = vel;
		this.pos  = pos;
		this.size = size;
	}
	
	/*
	 * Constructor
	 * Author: Jeremiah Hanson
	 * ---------------------------------------
	 * constructor
	 * Parameters:
	 * 	mass: mass of the body
	 * 	size: size of the body
	 */
	public Body(double mass, int size) {
		this.mass = mass;
		this.size = size;
	}
	
	public int getX() {
		return pos.x;
	}
	
	public int getY() {
		return pos.y;
	}
	
	public int getSize() {
		return size;
	}
}

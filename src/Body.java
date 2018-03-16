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
	int size, timeSteps;
	Vec2d vel;
	Point pos;
	Positions position;
	
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
	public Body(double mass, Vec2d vel, Point pos, int size, int steps) {
		this.mass = mass;
		this.vel = vel;
		this.pos  = pos;
		this.size = size;
		this.timeSteps = steps;
		position = new Positions();
		position.addPos(pos, vel);
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
	
	/*
	 * Positions
	 * Author: Jeremiah Hanson
	 * ---------------------------------------
	 * This class stores all locations of the body
	 */
	private class Positions {
		
		Point[] pos;
		Vec2d[] vel;
		int last;
		
		/*
		 * Constructor
		 * Author: Jeremiah Hanson
		 * --------------------------------
		 * constructor
		 */
		public Positions() {
			pos = new Point[timeSteps];
			vel = new Vec2d[timeSteps];
			last = 0;
		}
		
		/*
		 * getPoint
		 * Author: Jeremiah Hanson
		 * ------------------------------------
		 * Purpose: gets point at index
		 * Parameters: 
		 * 	index: the index to get
		 */
		public Point getPoint(int index) {
			return pos[index];
		}
		
		/*
		 * getVel
		 * Author: Jeremiah Hanson
		 * ------------------------------------
		 * Purpose: gets velocity at index
		 * Parameters: 
		 * 	index: the index to get
		 */
		public Vec2d getVel(int index) {
			return vel[index];
		}
		
		/*
		 * addPos
		 * Author: Jeremiah Hanson
		 * ---------------------------------
		 * Purpose: adds a pos to the list
		 * Parameters:
		 * 	point: the pos to add 
		 *  velocity: the velocity to add
		 */
		public void addPos(Point point, Vec2d velocity) {
			pos[last] = point;
			vel[last] = velocity;
			last++;
		}
		

	}
}

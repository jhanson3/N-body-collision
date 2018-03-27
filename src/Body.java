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
	Vec2d vel, pos, force;
	Positions positions;
	int collide;
	
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
	public Body(double mass, Vec2d pos, Vec2d vel, int size, int steps) {
		this.mass = mass;
		this.vel = vel;
		this.pos  = pos;
		this.force = new Vec2d(0, 0);
		this.size = size;
		this.timeSteps = steps;
		positions = new Positions();
		positions.addPos(pos, vel);
		
		collide = -1;
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
	
	public int getCollide() {
		return collide;
	}
	
	public void setCollide(int c) {
		collide = c;
	}
	
	public void removeCollide() {
		collide = -1;
	}
	
	public synchronized double getX() {
		return pos.x;
	}
	
	public synchronized double getY() {
		return pos.y;
	}
	
	public synchronized double getVX() {
		return vel.x;
	}
	
	public synchronized double getVY() {
		return vel.y;
	}
	
	public double getFX() {
		return force.x;
	}
	
	public double getFY() {
		return force.y;
	}
	
	public void setX(double x) {
		pos.x = x;
	}
	
	public void setY(double y) {
		pos.y = y;
	}
	
	public void setVX(double x) {
		vel.x = x;
	}
	
	public void setVY(double y) {
		vel.y = y;
	}
	
	public void setFX(double x) {
		force.x = x;
	}
	
	public void setFY(double y) {
		force.y = y;
	}
	
	public int getSize() {
		return size;
	}
	
	public Vec2d Position(int n) {
		return positions.getPoint(n);
	}
	
	public Vec2d Velocity(int n) {
		return positions.getVel(n);
	}
	
	public Vec2d Force(int n) {
		return positions.getForce(n);
	}
	
	public void addLast() {
		positions.addPos(pos, vel);
	}
	
	/*
	 * Positions
	 * Author: Jeremiah Hanson
	 * ---------------------------------------
	 * This class stores all locations of the body
	 */
	private class Positions {
		
		Vec2d[] pos;
		Vec2d[] vel;
		Vec2d[] force;
		int last;
		
		/*
		 * Constructor
		 * Author: Jeremiah Hanson
		 * --------------------------------
		 * constructor
		 */
		public Positions() {
			pos = new Vec2d[timeSteps];
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
		public Vec2d getPoint(int index) {
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
		
		public Vec2d getForce(int index) {
			return force[index];
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
		public void addPos(Vec2d position, Vec2d velocity) {
			pos[last] = new Vec2d(position);
			vel[last] = new Vec2d(velocity);
			last++;
		}
		

	}
}

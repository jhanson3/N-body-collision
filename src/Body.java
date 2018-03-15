import java.awt.Point;

import com.sun.javafx.geom.Vec2d;

/*
 * Body.java
 * Author: Jeremiah Hanson
 * --------------------------------------------------
 * This is used to store object data for a body
 */

public class Body {

	double mass, width, height;
	Vec2d vel;
	Point pos;
	
	/*
	 * Constructor
	 * Author: Jeremiah Hanson
	 * ---------------------------------------
	 * constructor
	 */
	public Body(double mass, Vec2d vel, Point pos) {
		this.mass = mass;
		this.vel = vel;
		this.pos  = pos;
		this.width = 10;
		this.height = 10;
	}
}

import java.awt.Point;
import java.util.Random;

import com.sun.javafx.geom.Vec2d;

/*
 * SequentialCollision.java
 * Author: Jeremiah Hanson
 * ---------------------------------------------------------------
 * This is an n-body collision program that runs sequentially with
 * one process.
 */

public class SequentialCollision {
	
	@SuppressWarnings("unused")
	private static int workers, bodies, bodySize, timeSteps;
	private static Body[] body;

	public static void main(String[] args) {
		
		if (args.length < 4) {
			System.out.println("ERROR: not enough arguments");
			return;
		} else {
			workers   = Integer.parseInt(args[0]);
			bodies    = Integer.parseInt(args[1]);
			bodySize  = Integer.parseInt(args[2]);
			timeSteps = Integer.parseInt(args[3]);
		}
		
		// create random and set seed
		Random rand = new Random();
		if (args.length >= 5) {
			rand.setSeed(Integer.parseInt(args[4]));
		}
		
		body = new Body[bodies];
		
		for (int i = 0; i < bodies; i++) {
			int x = rand.nextInt((int)(1000/bodies));
			x += i * (1000/bodies);
			Point p = new Point(x, rand.nextInt(1000));
			Vec2d v = new Vec2d(rand.nextInt(100), rand.nextInt(100));
			body[i] = new Body(10, v, p, bodySize);
		}
		
		ColliderGUI gui = new ColliderGUI();
		gui.setVisible(true);
		gui.draw(body);
	}
	
}

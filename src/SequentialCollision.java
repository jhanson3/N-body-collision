import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
	private static final double M = 1000;
	private static final double SPEED_CONST = 1;
	private static boolean gui;

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		if (args.length < 4) {
			System.out.println("ERROR: not enough arguments");
			return;
		} else {
			workers   = Integer.parseInt(args[0]);
			bodies    = Integer.parseInt(args[1]);
			bodySize  = Integer.parseInt(args[2]);
			timeSteps = Integer.parseInt(args[3]);
		}
		
		gui = false;
		if (args.length == 5) {
			if (args[4].equals("true")) {
				gui = true;
			} else {
				gui = false;
			}
		}
		
		// create random and set seed
		Random rand = new Random();
		if (args.length >= 5) {
			rand.setSeed(Integer.parseInt(args[4]));
		}
		
		body = new Body[bodies];
		
		for (int i = 0; i < bodies; i++) {
			
			Vec2d p = new Vec2d(rand.nextInt(1000), rand.nextInt(1000));
			boolean tooClose = false;
			
			for (int j = 0; j < i-1; j++) {
				if (i > 0 &&
					(Math.abs(p.x - body[j].getX()) <= bodySize &&
					Math.abs(p.y - body[j].getY()) <= bodySize)) {
					
					tooClose = true;
					break;
				}
			}
			
			if (tooClose) {
				i--;
				continue;
			}
			
			Vec2d velocity = new Vec2d(0, 0);
			
			if (p.x <= 500)
				velocity.x = SPEED_CONST * (rand.nextInt(2) + 1);
			else
				velocity.x = -SPEED_CONST * (rand.nextInt(2) + 1);
			
			if (p.y <= 500)
				velocity.y = SPEED_CONST * (rand.nextInt(2) + 1);
			else
				velocity.y = -SPEED_CONST * (rand.nextInt(2) + 1);
			
			body[i] = new Body(M, p, velocity, bodySize, timeSteps);
		}
		
		long start, end;
		
		start = System.nanoTime();
		//printOut(0);
		crunch();
		end = System.nanoTime();
		printAll();

		
		System.out.printf("Time: %.9f", ((double)(end-start)/1000000000));
		
		if (gui) {
			ColliderGUI gui = new ColliderGUI(body, timeSteps);
			gui.setVisible(true);
			gui.repaint();
		}
	}
	
	public static void moveOneStep() {

		for (int i = 0; i < bodies; i++) {
			
			Vec2d nextPos = new Vec2d(body[i].getX() + body[i].getVX(), body[i].getY() + body[i].getVY());
			
			for (int j = 0; j < i; j++) {
				if (Math.abs(nextPos.x - body[j].getX()) < bodySize && Math.abs(nextPos.y - body[j].getY()) < bodySize) {
					body[i].setCollide(j);
					body[j].setCollide(i);
					collision();
				}
			}
			
			for (int j = i+1; j < bodies; j++) {
				if (Math.abs(nextPos.x - body[j].getX()) < bodySize && Math.abs(nextPos.y - body[j].getY()) < bodySize) {
					body[i].setCollide(j);
					body[j].setCollide(i);
					collision();
				}
			}
			
			body[i].setX(nextPos.x);
			body[i].setY(nextPos.y);
		}
	}
	
	public static void collision() {
		
		for (int i = 0; i < bodies; i++) {
			
			if (body[i].getCollide() == -1)
				continue;
			
			int other = body[i].getCollide();
			
			body[i].setCollide(-1);
			body[other].setCollide(-1);
			
			double vx =  body[other].getVX() * Math.pow((body[other].getX() - body[i].getX()), 2);
				   vx += body[other].getVY() * (body[other].getX() - body[i].getX())  * (body[other].getY() - body[i].getY());
				   
				   vx += body[i].getVX() * Math.pow((body[other].getY() - body[i].getY()), 2);
				   vx -= body[i].getVY() * (body[other].getX() - body[i].getX()) * (body[other].getY() - body[i].getY());
				   
				   vx =  vx / (Math.pow((body[other].getY() - body[i].getY()), 2) + Math.pow((body[other].getX() - body[i].getX()), 2));
				   
			double vy =  body[other].getVX() * (body[other].getX() - body[i].getX())  * (body[other].getY() - body[i].getY());
				   vy += body[other].getVY() * Math.pow((body[other].getY() - body[i].getY()), 2);
				   
				   vy -= body[i].getVX() * (body[other].getX() - body[i].getX())  * (body[other].getY() - body[i].getY());
				   vy += body[i].getVY() * Math.pow((body[other].getX() - body[i].getX()), 2);
				   
				   vy =  vy / (Math.pow((body[other].getY() - body[i].getY()), 2) + Math.pow((body[other].getX() - body[i].getX()), 2));
				   
			double vx2 =  body[i].getVX() * Math.pow((body[other].getX() - body[i].getX()), 2);
				   vx2 += body[i].getVY() * (body[other].getX() - body[i].getX())  * (body[other].getY() - body[i].getY());
				   
				   vx2 += body[other].getVX() * Math.pow((body[other].getY() - body[i].getY()), 2);
				   vx2 -= body[other].getVY() * (body[other].getX() - body[i].getX())  * (body[other].getY() - body[i].getY());
				   
				   vx2 =  vx2 / (Math.pow((body[other].getY() - body[i].getY()), 2) + Math.pow((body[other].getX() - body[i].getX()), 2));
				   
			double vy2 =  body[i].getVX() * (body[other].getX() - body[i].getX())  * (body[other].getY() - body[i].getY());
				   vy2 += body[i].getVY() * Math.pow((body[other].getY() - body[i].getY()), 2);
				   
				   vy2 -= body[other].getVX() * (body[other].getX() - body[i].getX())  * (body[other].getY() - body[i].getY());
				   vy2 += body[other].getVY() * Math.pow((body[other].getX() - body[i].getX()), 2);
				   
				   vy2 =  vy2 / (Math.pow((body[other].getY() - body[i].getY()), 2) + Math.pow((body[other].getX() - body[i].getX()), 2));
				   
			body[i].setVX(vx);
			body[i].setVY(vy);
			body[other].setVX(vx);
			body[other].setVY(vy);
		}
	}
	
	public static void crunch() {
		
		int step = 1;
		
		while(step < timeSteps) {
			moveOneStep();
			//collision();
			
			for (int i = 0; i < bodies; i++) {
				body[i].addLast();
			}
			
			//printOut(step);
			
			step++;
		}
	}
	
	public static void printOut(int j) {
		
		System.out.println("Step " + j);
		for (int i = 0; i < bodies; i++) {
			System.out.println("	Body " + i);
			System.out.println("		Pos " + j + " -----> x: " + body[i].Position(j).x 
							   + " y: " + body[i].Position(j).y);
			System.out.println("		Vel " + j + " -----> x: " + body[i].Velocity(j).x 
					   		   + " y: " + body[i].Velocity(j).y);
			System.out.println();
		}
		
	}
	
	public static void printAll() throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("seqResults.txt", "UTF-8");

		for (int i = 0; i < bodies; i++) {
			writer.println("Body " + i);
			for (int j = 0; j < timeSteps; j++) {
				writer.println("	Pos " + j + " -----> x: " + body[i].Position(j).x 
								   + " y: " + body[i].Position(j).y);
				writer.println("	Vel " + j + " -----> x: " + body[i].Velocity(j).x 
						   		   + " y: " + body[i].Velocity(j).y);
			}
		}
		writer.close();
	}
	
}

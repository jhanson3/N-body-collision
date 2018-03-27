import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

import com.sun.javafx.geom.Vec2d;

/*
 * Parallel Collision.java
 * Author: Jeremiah Hanson
 * ---------------------------------------------------------------
 * This is an n-body collision program that runs in parallel with 
 * between 2 and 32 threads
 */

public class ParallelCollision {

	private static int bodies, bodySize, timeSteps;
	public static Body[] body;
	private static final double M = 1000;
	private static final double SPEED_CONST = 1;
	private static Thread collisionWorker;
	public static Thread[] workersList;
	public static CyclicBarrier barrier;
	private static Boolean gui;
	
	public static int ready, workers, curStep;

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		if (args.length < 4) {
			System.out.println("ERROR: not enough arguments");
			return;
		} else {
			workers   = Integer.parseInt(args[0]) - 1;
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
		
		barrier = new CyclicBarrier(workers+1);
		
		// create random and set seed
		Random rand = new Random();
		rand.setSeed(1234567890);
		
		body = new Body[bodies];
		workersList = new Thread[workers];
		
		int bodyPerThread = bodies / workers;
		int begin = 0;
		int stop = begin + bodyPerThread;
		curStep = 1;
		
		//System.out.println("Workers: " + workers);
		
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

		collisionWorker = new Thread(new CollisionWorker(bodies, timeSteps));
		collisionWorker.start();
		
		for (int i = 0; i < workers; i++) {
			//System.out.println("begin: " + begin + " Stop: " + stop);
			if (i == workers-1) 
				workersList[i] = new Thread(new Worker(bodies, bodySize, timeSteps, begin, bodies));
			else
				workersList[i] = new Thread(new Worker(bodies, bodySize, timeSteps, begin, stop));
			begin = stop;
			stop += bodyPerThread;
			workersList[i].start();
		}
		
		while (true) {
			int done = 0;
			
			for (int n=0; n<workers; n++) {
				if (!workersList[n].isAlive())
					done++;
			}
			if (!collisionWorker.isAlive()) done++;
			
			if (done == workers + 1)
				break;
		}
		
		end = System.nanoTime();
		printAll();
		
		System.out.printf("Time: %.9f", ((double)(end-start)/1000000000));
		System.out.println();
		
		if (gui) {
			ColliderGUI gui = new ColliderGUI(body, timeSteps);
			gui.setVisible(true);
			gui.repaint();
		}
	}
	
	public static void printAll() throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("parResults.txt", "UTF-8");

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

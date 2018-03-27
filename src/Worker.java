import java.util.concurrent.BrokenBarrierException;

import com.sun.javafx.geom.Vec2d;

public class Worker implements Runnable{

	private int bodies, bodySize, timeSteps, start, stop;
	
	public Worker(int bodies, int bodySize, int steps, int start, int stop) {
		this.bodies = bodies;
		this.timeSteps = steps;
		this.start = start;
		this.stop = stop;
	}
	
	@Override
	public void run() {
		int step = 1;
		
		while(step < timeSteps) {
			moveOneStep();
			try {
				//System.out.println("waiting");
				ParallelCollision.barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for (int i = start; i < stop; i++) {
				ParallelCollision.body[i].addLast();
			}
			
			//printOut(step);
			
			step++;
		}
		
	}
	
	public synchronized void moveOneStep() {

		for (int i = start; i < stop; i++) {
			
			Vec2d nextPos = new Vec2d(ParallelCollision.body[i].getX() + ParallelCollision.body[i].getVX(), ParallelCollision.body[i].getY() + ParallelCollision.body[i].getVY());
			
			for (int j = 0; j < i; j++) {
				if (Math.abs(nextPos.x - ParallelCollision.body[j].getX()) < bodySize && Math.abs(nextPos.y - ParallelCollision.body[j].getY()) < bodySize) {
					ParallelCollision.body[i].setCollide(j);
					ParallelCollision.body[j].setCollide(i);
				}
			}
			
			for (int j = i+1; j < bodies; j++) {
				if (Math.abs(nextPos.x - ParallelCollision.body[j].getX()) < bodySize && Math.abs(nextPos.y - ParallelCollision.body[j].getY()) < bodySize) {
					ParallelCollision.body[i].setCollide(j);
					ParallelCollision.body[j].setCollide(i);
				}
			}
			
			ParallelCollision.body[i].setX(nextPos.x);
			ParallelCollision.body[i].setY(nextPos.y);
		}
	}
	
	

}

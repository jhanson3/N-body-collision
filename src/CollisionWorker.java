import java.util.concurrent.BrokenBarrierException;

public class CollisionWorker implements Runnable{

	private int bodies;
	
	public CollisionWorker(int bodies){
		this.bodies = bodies;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				//System.out.println("barrier hit");
				ParallelCollision.barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			collision();
		}
	}
	
	public void collision() {
		
		for (int i = 0; i < bodies; i++) {
			
			if (ParallelCollision.body[i].getCollide() == -1)
				continue;
			
			int other = ParallelCollision.body[i].getCollide();
			
			ParallelCollision.body[i].setCollide(-1);
			ParallelCollision.body[other].setCollide(-1);
			
			double vx =  ParallelCollision.body[other].getVX() * Math.pow((ParallelCollision.body[other].getX() - ParallelCollision.body[i].getX()), 2);
				   vx += ParallelCollision.body[other].getVY() * (ParallelCollision.body[other].getX() - ParallelCollision.body[i].getX())  * (ParallelCollision.body[other].getY() - ParallelCollision.body[i].getY());
				   
				   vx += ParallelCollision.body[i].getVX() * Math.pow((ParallelCollision.body[other].getY() - ParallelCollision.body[i].getY()), 2);
				   vx -= ParallelCollision.body[i].getVY() * (ParallelCollision.body[other].getX() - ParallelCollision.body[i].getX()) * (ParallelCollision.body[other].getY() - ParallelCollision.body[i].getY());
				   
				   vx =  vx / (Math.pow((ParallelCollision.body[other].getY() - ParallelCollision.body[i].getY()), 2) + Math.pow((ParallelCollision.body[other].getX() - ParallelCollision.body[i].getX()), 2));
				   
			double vy =  ParallelCollision.body[other].getVX() * (ParallelCollision.body[other].getX() - ParallelCollision.body[i].getX())  * (ParallelCollision.body[other].getY() - ParallelCollision.body[i].getY());
				   vy += ParallelCollision.body[other].getVY() * Math.pow((ParallelCollision.body[other].getY() - ParallelCollision.body[i].getY()), 2);
				   
				   vy -= ParallelCollision.body[i].getVX() * (ParallelCollision.body[other].getX() - ParallelCollision.body[i].getX())  * (ParallelCollision.body[other].getY() - ParallelCollision.body[i].getY());
				   vy += ParallelCollision.body[i].getVY() * Math.pow((ParallelCollision.body[other].getX() - ParallelCollision.body[i].getX()), 2);
				   
				   vy =  vy / (Math.pow((ParallelCollision.body[other].getY() - ParallelCollision.body[i].getY()), 2) + Math.pow((ParallelCollision.body[other].getX() - ParallelCollision.body[i].getX()), 2));
				   
			double vx2 =  ParallelCollision.body[i].getVX() * Math.pow((ParallelCollision.body[other].getX() - ParallelCollision.body[i].getX()), 2);
				   vx2 += ParallelCollision.body[i].getVY() * (ParallelCollision.body[other].getX() - ParallelCollision.body[i].getX())  * (ParallelCollision.body[other].getY() - ParallelCollision.body[i].getY());
				   
				   vx2 += ParallelCollision.body[other].getVX() * Math.pow((ParallelCollision.body[other].getY() - ParallelCollision.body[i].getY()), 2);
				   vx2 -= ParallelCollision.body[other].getVY() * (ParallelCollision.body[other].getX() - ParallelCollision.body[i].getX())  * (ParallelCollision.body[other].getY() - ParallelCollision.body[i].getY());
				   
				   vx2 =  vx2 / (Math.pow((ParallelCollision.body[other].getY() - ParallelCollision.body[i].getY()), 2) + Math.pow((ParallelCollision.body[other].getX() - ParallelCollision.body[i].getX()), 2));
				   
			double vy2 =  ParallelCollision.body[i].getVX() * (ParallelCollision.body[other].getX() - ParallelCollision.body[i].getX())  * (ParallelCollision.body[other].getY() - ParallelCollision.body[i].getY());
				   vy2 += ParallelCollision.body[i].getVY() * Math.pow((ParallelCollision.body[other].getY() - ParallelCollision.body[i].getY()), 2);
				   
				   vy2 -= ParallelCollision.body[other].getVX() * (ParallelCollision.body[other].getX() - ParallelCollision.body[i].getX())  * (ParallelCollision.body[other].getY() - ParallelCollision.body[i].getY());
				   vy2 += ParallelCollision.body[other].getVY() * Math.pow((ParallelCollision.body[other].getX() - ParallelCollision.body[i].getX()), 2);
				   
				   vy2 =  vy2 / (Math.pow((ParallelCollision.body[other].getY() - ParallelCollision.body[i].getY()), 2) + Math.pow((ParallelCollision.body[other].getX() - ParallelCollision.body[i].getX()), 2));
				   
				   ParallelCollision.body[i].setVX(vx);
				   ParallelCollision.body[i].setVY(vy);
				   ParallelCollision.body[other].setVX(vx);
				   ParallelCollision.body[other].setVY(vy);
		}
	}
	
}

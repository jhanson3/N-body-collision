import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * ColliderGUI.java
 * Author: Jeremiah Hanson
 * ----------------------------------------------------------------------
 * This is a GUI class to see visually what is happening to the bodies
 */
public class ColliderGUI extends JFrame{

	/**
	 * Automatically generated serial number
	 */
	private static final long serialVersionUID = -4681843281978083821L;
	
	private JPanel view;
	private JButton forward, backward;
	private int step, totalSteps;
	
	/*
	 * Constructor
	 * Author: Jeremiah Hanson
	 * -------------------------------
	 * constructor
	 */
	public ColliderGUI(Body[] body, int numSteps) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1100, 1100);
		this.setLocation(100, 100);
		this.setTitle("N-Body Collisions");
		this.setLayout(null);
		view = new DrawView(body);
		totalSteps = numSteps;
		step = 0;
		
		this.add(view);
		
		forward = new JButton("Next");
		backward = new JButton("Previous");
		forward.addActionListener(new ButtonListener());
		backward.addActionListener(new ButtonListener());
		
		forward.setSize(100, 50);
		forward.setLocation(800, 900);
		backward.setSize(100, 50);
		backward.setLocation(200, 900);
		this.add(forward);
		this.add(backward);
		
		redraw();

		
	}
	
	/*
	 * ButtonListener
	 * Author: Jeremiah Hanson
	 * ----------------------------------------------
	 * listener for the buttons
	 */
	public class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if (arg0.getSource() == forward) {
				if (step < totalSteps-1)
					step++;
				view.repaint();
			}
			else if (arg0.getSource() == backward) {
				if (step > 0)
					step--;
				view.repaint();
			}
			
			redraw();

		}
		
	}
	
	public void redraw() {
		this.add(forward);
		this.add(backward);
	}
	
	/*
	 * DrawView
	 * Author: Jeremiah Hanson
	 * ---------------------------------------------
	 * Class responsible for the main view
	 */
	private class DrawView extends JPanel {
		
		/**
		 * Automatically generated serial number
		 */
		private static final long serialVersionUID = -158235515021115934L;
		private Body[] body;
		
		public DrawView(Body[] body) {
			this.body = body;
			this.setSize(1300, 1000);
		}

		public void paintComponent(Graphics g){
	           if (body == null)
	        	   return;
	           
	           g.clearRect(0, 0, 1300, 1000);
	           for (int i = 0; i < body.length; i++) {
	        	   g.fillOval((int)body[i].Position(step).x, (int)body[i].Position(step).y, body[i].getSize(), body[i].getSize());
	           }
	         }
	}

}

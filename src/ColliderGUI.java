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
	private Body[] body;
	private JButton forward, backward;
	
	/*
	 * Constructor
	 * Author: Jeremiah Hanson
	 * -------------------------------
	 * constructor
	 */
	public ColliderGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1100, 1100);
		this.setLocation(100, 100);
		this.setTitle("N-Body Collisions");
		this.setLayout(null);
		view = new DrawView();
		
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
		
	}
	
	/*
	 * draw
	 * Author: Jeremiah Hanson
	 * --------------------------------------------
	 * Purpose: draws the bodies
	 * Parameters:
	 * 	body: an array of Bodies
	 */
	public void draw(Body[] body) {
		this.body = body;
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
				
			}
		}
		
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

		public void paintComponent(Graphics g){
	           if (body == null)
	        	   return;
	           
	           for (int i = 0; i < body.length; i++) {
	        	   g.fillOval(body[i].getX(), body[i].getY(), body[i].getSize(), body[i].getSize());
	           }
	         }
	}

}

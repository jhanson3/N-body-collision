import java.awt.Graphics;

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
	
	JPanel view;
	
	/*
	 * Constructor
	 * Author: Jeremiah Hanson
	 * -------------------------------
	 * constructor
	 */
	public ColliderGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(2000, 1000);
		this.setLocation(100, 100);
		this.setTitle("N-Body Collisions");
		view = new DrawView();
		
		this.add(view);
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
	           g.fillOval(100, 100, 100, 100);
	         }
	}

}

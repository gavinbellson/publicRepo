import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Ball extends Rectangle {

	/**
	 * ball variables
	 */
	Random random ;
	int xVelocity ;
	int yVelocity ;
	
	/**
	 * constructor method
	 */
	Ball (int yPosition, int xPosition, int ballDiameter, int ballHeight) {
		super (yPosition, xPosition, ballDiameter, ballHeight);
		random = new Random ();
		int randomXDirection = random.nextInt(2);
		if (randomXDirection == 0) {
			randomXDirection-- ;
			setRandomXDirection(randomXDirection);
		}
		
		int randomYDirection = random.nextInt(2);
		if (randomYDirection == 0) {
			randomYDirection-- ;
			setRandomYDirection(randomYDirection);
		}
	}
	
	/**
	 * setXDirection setter method ball random x direction of new ball
	 * @param random x direction
	 */
	public void setRandomXDirection (int randomXDirection) {
		xVelocity = randomXDirection ;
	}
	/**
	 * setYDirection setter method ball random y direction for new ball
	 * @param random y direction 
	 */
	public void setRandomYDirection (int randomYDirection) {
		yVelocity = randomYDirection ;
	}
	
	/**
	 * move method
	 */
	public void move () {
		x = x + xVelocity ;
		y = y + yVelocity ;
	}
	
	/**
	 * draw method
	 * @param Graphics g
	 */
	public void draw (Graphics g) {
		g.setColor(Color.orange);
		g.fillOval(x, y, height, width);
	}
}

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Paddle extends Rectangle {
	/**
	 * paddle variables
	 */
	int id ;
	int yVelocity ;
	int speed_paddle_moves = 10; //10 pixels
	int stationary_paddle = 0 ;
	/**
	 * constructor method
	 */
	Paddle(int XPosition, int YPosition, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
		super (XPosition, YPosition , PADDLE_WIDTH, PADDLE_HEIGHT);
		this.id = id ;
	}
	
	/**
	 * key press methods
	 */
	public void keyPressed (KeyEvent e) {
		switch (id) {
		case 1:
			if (e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(-speed_paddle_moves);//move up x pixels
				move();
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(speed_paddle_moves);//move down x pixels
				move();
			}
			break;
		case 2:
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(-speed_paddle_moves);//move up x pixels
				move();
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(speed_paddle_moves);//move down x pixels
				move();
			}
			break;
		}
	}
	public void keyReleased (KeyEvent e) {

		switch (id) {
		case 1:
			if (e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(stationary_paddle);//stop moving
				move();
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(stationary_paddle);//stop moving
				move();
			}
			break;
		case 2:
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(stationary_paddle);//stop moving
				move();
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(stationary_paddle);//stop moving
				move();
			}
			break;
		}

	}
	
	/**
	 * setYDirection setter method 
	 * @param yDirection integer
	 */
	public void setYDirection (int yDirection) {
		yVelocity = yDirection ;
	}
	/**
	 * move the paddles
	 */
	public void move () {
		y = y + yVelocity ;
	}
	
	/**
	 * draw
	 * @param Graphics g
	 */
	public void draw(Graphics g) {
		if (id == 1) {
			g.setColor(Color.blue);
		}
		else {
			g.setColor(Color.red);
		}
		g.fillRect(x, y, width, height);
	}
}

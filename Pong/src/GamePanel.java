import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GamePanel extends JPanel implements Runnable {
	
	/**
	 * panel variables
	 */
	static final int GAME_WIDTH = 1000 ;
	static final int GAME_HEIGHT = (int) (GAME_WIDTH * (.55)) ;
	static final Dimension SCREEN_SIZE = new Dimension (GAME_WIDTH, GAME_HEIGHT) ;
	static final int BALL_DIAMETER = 20 ;
	static final int PADDLE_WIDTH = 25 ;
	static final int PADDLE_HEIGHT = 100 ;
	Thread gameThread ;
	Image image ;
	Graphics graphics ;
	Random random ;
	Paddle paddle1 ;
	int paddle1id = 1 ;
	int paddle2id = 2 ;
	Paddle paddle2 ;
	Ball ball ;
	Score score ;

	/**
	 * constructor method
	 */
	GamePanel() {
		newPaddle() ;
		newBall() ;
		score = new Score (GAME_WIDTH,GAME_HEIGHT) ;
		this.setFocusable(true);
		this.addKeyListener(new actionListener ());
		this.setPreferredSize(SCREEN_SIZE);
		gameThread = new Thread (this);
		gameThread.start();
	}
	
	/**
	 * new ball method 
	 */
	public void newBall () {
		random = new Random ();
		ball = new Ball (GAME_WIDTH/2 - BALL_DIAMETER/2, GAME_HEIGHT/2 - BALL_DIAMETER/2, 
				BALL_DIAMETER, BALL_DIAMETER) ;
	}
	
	/**
	 * new paddle method
	 */
	public void newPaddle () {
		paddle1 = new Paddle (0, (GAME_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, paddle1id);
		paddle2 = new Paddle ((GAME_WIDTH - PADDLE_WIDTH), (GAME_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, paddle2id);
	}
	
	/**
	 * paint 
	 */
	public void paint (Graphics g) {
		image = createImage (getWidth(),getHeight());
		graphics = image.getGraphics();
		draw (graphics);
		g.drawImage(image, 0, 0, this);
	}
	
	/**
	 * draw method
	 * @param Graphics g
	 */
	public void draw (Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
	}
	
	/**
	 * move method to move things after each iteration of game loop.
	 * without calling paddle1.move() here the paddles are sluggish.
	 */
	public void move () {
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	
	/**
	 * checkCollision method
	 */
	public void checkCollision () {
		//keep ball on screen
		if (ball.y <= 0) {
			ball.setRandomYDirection(-ball.yVelocity) ;
		}
		if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
			ball.setRandomYDirection(-ball.yVelocity) ;
		}
		
		if (ball.x <= 0) {
			ball.setRandomXDirection(-ball.xVelocity) ;
		}
		if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
			ball.setRandomXDirection(-ball.xVelocity) ;
		}
		
		//prevent paddles from leaving screen
		if (paddle1.y <= 0) {
			paddle1.y = 0 ;
		}
		if (paddle1.y >= GAME_HEIGHT - PADDLE_HEIGHT) {
			paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT ;
		}
		if (paddle2.y <= 0) {
			paddle2.y = 0 ;
		}
		if (paddle2.y >= GAME_HEIGHT - PADDLE_HEIGHT) {
			paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT ;
		}
	}
	
	/**
	 * game loop
	 */
	public void run () {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0 ;
		double ns = 1000000000  / amountOfTicks;
		double delta = 0 ;
		while (true) {
			long now = System.nanoTime();
			delta = delta + (now - lastTime) / ns ;
			lastTime = now ;
			if (delta >= 1 ) {
				move ();
				checkCollision ();
				repaint ();
				delta-- ;
				//System.out.println(delta); //comment when not testing loop
			}
		}
	}
	
	/**
	 * inner class actionListener
	 * 2 methods 
	 */
	public class actionListener extends KeyAdapter {
		public void keyPressed (KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		public void keyReleased (KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}


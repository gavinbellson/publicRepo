package main;
import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class GamePanel extends JPanel implements Runnable {

	/* screen settings */
	//16 x 16 tile (old nes standard size)
	final int originalTileSize = 16 ; 
	//modern screen resolution is higher so have to scale up
	final int scale = 3 ;
	//48x48 actual tile size displayed
	public final int tileSize = originalTileSize * scale ; 
	public final int maxScreenCol = 16; //how many tiles wide
	public final int maxScreenRow = 12;    //how many titles tall
	//768 pixels = (originalTileSize * scale) * maxScreenCol 
	public final int screenWidth = tileSize * maxScreenCol ;
	//576 pixels = (16 * 3) * 12
	public final int screenHeight = tileSize * maxScreenRow ;
	
	//WORLD MAP Settings 
	public final int maxWorldCol = 50 ;//how many tiles wide
	public final int maxWorldRow = 50 ;//how many titles tall
	public final int worldWidth = tileSize * maxWorldCol ;
	public final int worldHeight = tileSize * maxWorldRow ;
	
	public CollisionChecker collisionChecker = new CollisionChecker (this);
	Thread gameThread;//implements Runnable
	KeyHandler keyH = new KeyHandler();//named instance of keyhandler
	final int FPS = 60 ;//frames per second,game loop rate
	public ObjectSetter objectSetter = new ObjectSetter(this);
	public Player player = new Player (this, keyH);
	public TileManager tileManager = new TileManager (this);
	public SuperObject obj[] = new SuperObject [10];//display 10 objs at a time
	
	/* panel constructor */
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);//better rendering performance
		this.addKeyListener(keyH);//give keyhandler instance to panel
		this.setFocusable(true);
	}

	/* 
	 * set up objects and such in game.  make sure in Game this
	 * is called BEFORE startGameThread
	 */
	public void setUpGame () {
		objectSetter.setObject();
	}
	
	/* create instance of thread and give to panel.
	 * startGameThread needs to be called in jframe */
	public void startGameThread () {
		gameThread = new Thread (this);
		gameThread.start();//calls the run (loop) method
	}
	
	/* started by thread, our game loop 
	 * keeps calling update and repaint
	 * over and over. need to tell it how often (FPS),
	 * else it will do it as many times as possible */
//	@Override
//	public void run() {
//		//the current time = System.nanoTime()
//		
//		//how often we want to draw, 1 sec (in nanoseconds) divided by FPS = rate, 60 fps
//		double drawInterval = 1000000000 / FPS ;
//		//next time to draw
//		double nextDrawTime = System.nanoTime() + drawInterval ;
//		
//		while (gameThread != null) {
//
//			//#1 update - info such as character position
//			//by calling the update method we make below
//			update();
//			//#2 draw - draw the screen with the updated info
//			//repaint calls paintComponent (not sure how?)
//			repaint();
//			
//			System.out.println("playerX = "+ playerX + "playerY = " + playerY);
//			
//			try {
//				//how long until next draw
//				double remainingTime = nextDrawTime - System.nanoTime();
//				//Thread.sleep uses millisec so have to convert remainingTime before calling sleep
//				remainingTime = remainingTime / 1000000 ;
//				/* if the update, repaint take longer than 1 loop then don't draw this loop */
//				if (remainingTime < 0) {
//					remainingTime = 0;
//				}
//				Thread.sleep((long) remainingTime) ;//sleep until time to draw
//				nextDrawTime = nextDrawTime + drawInterval ;//next draw time
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//		
//	}
	
	/**
	 * alternative game loop logic 
	 * 
	 * 	
	 */
	public void run() {
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0 ;//checking FPS
		int drawCount = 0 ;//checking FPS
		
		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta = delta + (currentTime - lastTime) / drawInterval ;
			timer = timer + (currentTime - lastTime);//checking FPS
			lastTime = currentTime;
			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;//checking FPS
			}
			/* checking FPS */
			if (timer >= 1000000000) {
				System.out.println("FPS = " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	/* note in java upper left corner is x=0, y=0
	 * and x values increase to the right 
	 * and y values increase as they go down 
	 * */
	public void update() {
		player.update();
		
	}
	
	/* paintComponent is a built in java name/method for drawing
	 * things on the screen in layers
	 * @param Graphics a class that has many functions to draw */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;//cast g to g2 for more functions
		//draw tiles
		tileManager.draw(g2);//bottom layer
		//draw objects
		for (int i = 0; i < obj.length; i++) {
			if (obj[i] != null  ) {
				obj[i].draw(g2, this);
			}
		}
		//draw player
		player.draw(g2);//next up from the bottom layer
		g2.dispose();//save memory after drawing
	}
}

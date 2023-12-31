import javax.swing.JPanel;
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
	final int tileSize = originalTileSize * scale ; 
	final int maxScreenCol = 16;//how many tiles wide
	final int maxScreenRow = 12;//how many titles tall
	//768 pixels = (originalTileSize * scale) * maxScreenCol 
	final int screenWidth = tileSize * maxScreenCol ;
	//576 pixels = (16 * 3) * 12
	final int screenHeight = tileSize * maxScreenRow ;
	
	Thread gameThread;//implements Runnable
	KeyHandler keyH = new KeyHandler();//named instance of keyhandler
	final int FPS = 60 ;//frames per second,game loop rate
	
	/* player default position */ 
	int playerX = 100 ;
	int playerY = 100 ;
	int playerSpeed = 4 ;//N of pixels 
	
	/* panel constructor */
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);//better rendering performance
		this.addKeyListener(keyH);//give keyhandler instance to panel
		this.setFocusable(true);
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
	@Override
	public void run() {
		//the current time = System.nanoTime()
		
		//how often we want to draw, 1 sec (in nanoseconds) divided by FPS = rate, 60 fps
		double drawInterval = 1000000000 / FPS ;
		//next time to draw
		double nextDrawTime = System.nanoTime() + drawInterval ;
		
		while (gameThread != null) {

			//#1 update - info such as character position
			//by calling the update method we make below
			update();
			//#2 draw - draw the screen with the updated info
			//repaint calls paintComponent (not sure how?)
			repaint();
			
			System.out.println("playerX = "+ playerX + "playerY = " + playerY);
			
			try {
				//how long until next draw
				double remainingTime = nextDrawTime - System.nanoTime();
				//Thread.sleep uses millisec so have to convert remainingTime before calling sleep
				remainingTime = remainingTime / 1000000 ;
				/* if the update, repaint take longer than 1 loop then don't draw this loop */
				if (remainingTime < 0) {
					remainingTime = 0;
				}
				Thread.sleep((long) remainingTime) ;//sleep until time to draw
				nextDrawTime = nextDrawTime + drawInterval ;//next draw time
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	/* note in java upper left corner is x=0, y=0
	 * and x values increase to the right 
	 * and y values increase as they go down 
	 * */
	public void update() {
		if (keyH.upPressed == true) {
			playerY = playerY - playerSpeed ;
		}
		if (keyH.downPressed == true) {
			playerY = playerY + playerSpeed ;
		}
		if (keyH.leftPressed == true) {
			playerX = playerX - playerSpeed ;
		}
		if (keyH.rightPressed == true) {
			playerX = playerX + playerSpeed ;
		}
	}
	
	/* paintComponent is a built in java name/method for drawing
	 * things on the screen 
	 * @param Graphics a class that has many functions to draw */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;//cast g to g2 for more functions
		g2.setColor(Color.white);
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		g2.dispose();//save memory after drawing
	}
}

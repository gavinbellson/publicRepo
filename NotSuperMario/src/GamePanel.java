import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class GamePanel extends JPanel implements Runnable{

	//screen settings
	final int originalTileSize = 16 ; //16 x 16 tile (old nes standard size)
	final int scale = 3 ;//modern screen resolution is higher
	final int tileSize = originalTileSize * scale ;//actual tile size displayed 
	final int maxScreenCol = 16;//how many tiles wide
	final int maxScreenRow = 12;//how many titles tall
	final int screenWidth = tileSize * maxScreenCol ;
	//768 pixels = (originalTileSize * scale) * maxScreenCol  
	final int screenHeight = tileSize * maxScreenRow ;//576 pixels = (16 * 3) * 12
	
	Thread gameThread;//implements Runnable
	KeyHandler keyH = new KeyHandler();
	
	//player default positition 
	int playerX = 100 ;
	int playerY = 100 ;
	int playerSpeed = 4 ;
	
	/* constructor */
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);//better rendering performance
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	/* create instance of thread and give to panel */
	public void startGameThread () {
		gameThread = new Thread (this);
		gameThread.start();//calls run
	}
	/* started by thread, our game loop */
	@Override
	public void run() {
		while (gameThread != null) {
			//System.out.println("game loop is running");
			//1 update - info such as character position
			update();
			//2 draw - draw the screen with the updated info
			repaint();
			
			//3 
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
	
	/* */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.white);
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		g2.dispose();//save memory
	}
}

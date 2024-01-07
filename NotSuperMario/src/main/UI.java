package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UI {

	/* variables */
	GamePanel gamePanel ;
	Font ARIAL_40, ARIAL_80_BOLD ;
	BufferedImage keyImage ;
	public boolean messageOn = false ;
	public String message = "" ;
	int messageTimer = 0;
	int messageTimerFrames = 120;//120FRAMES = 2 sec
	public boolean gameFinished = false ;//game over man
	
	/* constructor */
	public UI(GamePanel gamePanelIn) {
		this.gamePanel = gamePanelIn ;
		ARIAL_40 = new Font ("Arial",Font.PLAIN, 40);
		ARIAL_80_BOLD = new Font ("Arial",Font.BOLD, 80);
		try {
			keyImage = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* showMessage */
	public void showMessage (String textIn) {
		message = textIn ;
		messageOn = true ;
	}
	
	/* draw method */
	public void draw (Graphics2D g2) {
		
		if (gameFinished == true) {
			
			g2.setFont(ARIAL_40);
			g2.setColor(Color.WHITE);
			String text = "you found the treasure!";
			int textLength = (int) g2.getFontMetrics().getStringBounds(text,g2).getWidth() ;
			int x = (gamePanel.screenWidth / 2) - (textLength / 2);
			int y = (gamePanel.screenHeight / 2) - (gamePanel.tileSize * 3) ;
			g2.drawString(text, x, y);
			
			g2.setFont(ARIAL_80_BOLD);
			g2.setColor(Color.yellow);
			text = "Congratuations!";
			textLength = (int) g2.getFontMetrics().getStringBounds(text,g2).getWidth() ;
			x = (gamePanel.screenWidth / 2) - (textLength / 2);
			y = (gamePanel.screenHeight / 2) - (gamePanel.tileSize * 2) ;
			g2.drawString(text, x, y);
			
			/* stop the game */
			gamePanel.gameThread = null ;
		} 
		else {
			g2.setFont(ARIAL_40);
			g2.setColor(Color.WHITE);
			g2.drawImage(keyImage, gamePanel.tileSize/2,gamePanel.tileSize/2,gamePanel.tileSize,gamePanel.tileSize,null);
			//g2.drawString("key = " + gamePanel.player.numOfKeysInInventory, 25, 50);
			g2.drawString("x  " + gamePanel.player.numOfKeysInInventory, 74, 60);
		
			//messages
			if (messageOn) {
			g2.setFont(g2.getFont().deriveFont(30F));
			g2.drawString(message, gamePanel.tileSize/2, gamePanel.tileSize*5);
			messageTimer++;
				if (messageTimer > messageTimerFrames) {
					messageTimer = 0;
					messageOn = false;
				}
			}
		}
	}
}

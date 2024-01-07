package object;

import main.GamePanel;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class SuperObject {

	/* variables */
	public BufferedImage objectsImage;
	public String objectsName;
	public boolean collision = false ;//defaults to false, change inside specific object classes
	public int worldX ;
	public int worldY ;
	public Rectangle solidArea = new Rectangle (0,0,48,48);
	public int solidAreaDefaultX = 0, solidAreaDefaultY = 0;
	
	/*
	 * draw
	 */
	public void draw (Graphics2D g2, GamePanel gamePanel) {
		int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX ;
		int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY ;
		
		/* draw only tiles on screen , not whole world  */
		if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX
				&& worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX
				&& worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY
				&& worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
			g2.drawImage (objectsImage,screenX,screenY,gamePanel.tileSize,gamePanel.tileSize,null);
		}
	}
}
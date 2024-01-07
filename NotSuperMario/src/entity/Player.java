package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	/* variables */
	GamePanel gamePanel ;
	KeyHandler keyH ;
	public final int screenX ;//player's screen position will never change with world map
	public final int screenY ; //player's screen position will never change with world map
	
	/* constructor */
	public Player (GamePanel gamePanel, KeyHandler keyH) {
		this.gamePanel = gamePanel;
		this.keyH = keyH;
		screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);//put player in middle of screen
		screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);//put player in middle of screen
		/* define collision area of player */
		solidArea = new Rectangle ();
		/* for readability defined Rectangle vars below*/
		solidArea.x = gamePanel.tileSize * 1/6 ;
		solidArea.y = gamePanel.tileSize * 1/3;
		solidArea.height = gamePanel.tileSize * 2/3;
		solidArea.width = gamePanel.tileSize * 2/3;
		setDefaultValues ();
		getPlayerImage();
	}
	/* default values */
	public void setDefaultValues () {
		worldX = gamePanel.tileSize * 23 ;
		worldY = gamePanel.tileSize * 21 ;
		speed = 4;
		direction = "down";
	}
	
	/* get image */
	public void getPlayerImage () {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	/* player update method - update location and collision checks */
	public void update() {
		if (keyH.upPressed == true || keyH.downPressed == true ||
				keyH.leftPressed == true || keyH.rightPressed == true ) {
		if (keyH.upPressed == true) {
			direction = "up";
			
		}
		if (keyH.downPressed == true) {
			direction = "down";
			
		}
		if (keyH.leftPressed == true) {
			direction = "left";
			
		}
		if (keyH.rightPressed == true) {
			direction = "right";
			
		}
		
		//check tile collision
		collisionOn = false;
		gamePanel.collisionChecker.checkTile(this);
		
		//if collision = false, only then can proceed with the move
		if (collisionOn == false) {
			switch (direction) {
			case "up": worldY = worldY - speed ; break;
			case "down": worldY = worldY + speed ; break;
			case "left": worldX = worldX - speed ; break;
			case "right": worldX = worldX + speed ; break;
			}
		}
		spriteCounter++;
		if (spriteCounter > 12) {
			if (spriteNum == 1) {
				spriteNum = 2;
			}
			else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		}
	}
	
	/* draw method */
	public void draw (Graphics2D g2) {
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
		BufferedImage image = null;
		switch (direction ) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2 ;
			}
			break;	
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			break;		
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;		
		}
		g2.drawImage(image,screenX,screenY,gamePanel.tileSize,gamePanel.tileSize,null);
	}
}

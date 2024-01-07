package main;

import object.objectBoots;
import object.objectChest;
import object.objectDoor;
import object.objectKey;

public class ObjectSetter {

	/* vars */
	GamePanel gamePanel;
	
	/* constructor */
	ObjectSetter(GamePanel gamePanelIn) {
		this.gamePanel = gamePanelIn;
	}
	
	/* instantiate some objects and place on world map */
	public void setObject () {
		gamePanel.obj[0] = new objectKey();
		gamePanel.obj[0].worldX = 23 * gamePanel.tileSize ;//x tiles over
		gamePanel.obj[0].worldY = 7 * gamePanel.tileSize ;//y tiles down
		
		gamePanel.obj[1] = new objectKey();
		gamePanel.obj[1].worldX = 23 * gamePanel.tileSize ;//x tiles over
		gamePanel.obj[1].worldY = 40 * gamePanel.tileSize ;//y tiles down
		
		gamePanel.obj[2] = new objectKey();
		gamePanel.obj[2].worldX = 37 * gamePanel.tileSize ;//x tiles over
		gamePanel.obj[2].worldY = 7 * gamePanel.tileSize ;//y tiles down
		
		gamePanel.obj[3] = new objectDoor();
		gamePanel.obj[3].worldX = 10 * gamePanel.tileSize ;//x tiles over
		gamePanel.obj[3].worldY = 9 * gamePanel.tileSize ;//y tiles down
		
		gamePanel.obj[4] = new objectDoor();
		gamePanel.obj[4].worldX = 8 * gamePanel.tileSize ;//x tiles over
		gamePanel.obj[4].worldY = 28 * gamePanel.tileSize ;//y tiles down
		
		gamePanel.obj[5] = new objectDoor();
		gamePanel.obj[5].worldX = 12 * gamePanel.tileSize ;//x tiles over
		gamePanel.obj[5].worldY = 22 * gamePanel.tileSize ;//y tiles down
		
		gamePanel.obj[6] = new objectChest();
		gamePanel.obj[6].worldX = 10 * gamePanel.tileSize ;//x tiles over
		gamePanel.obj[6].worldY = 7 * gamePanel.tileSize ;//y tiles down
		
		gamePanel.obj[7] = new objectBoots();
		gamePanel.obj[7].worldX = 37 * gamePanel.tileSize ;//x tiles over
		gamePanel.obj[7].worldY = 42 * gamePanel.tileSize ;//y tiles down

	}
}

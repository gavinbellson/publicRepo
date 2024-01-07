package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gamePanel;

	/* constructor */
	public CollisionChecker (GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		
	}

	/* check collisions of any entities including players */
	public void checkTile (Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x ;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
	
		int entityLeftCol = entityLeftWorldX / gamePanel.tileSize ;
		int entityRightCol = entityRightWorldX / gamePanel.tileSize ;
		int entityTopRow = entityTopWorldY / gamePanel.tileSize ;
		int entityBottomRow = entityBottomWorldY / gamePanel.tileSize ;
		
		int tileNum1, tileNum2;
		
		switch (entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed) / (gamePanel.tileSize);
			tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol] [entityTopRow];//player's left shoulder
			tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol] [entityTopRow];//player's right shoulder
			if (gamePanel.tileManager.tile[tileNum1].collision == true
					|| gamePanel.tileManager.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed) / (gamePanel.tileSize);
			tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol] [entityBottomRow];//left shoulder
			tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol] [entityBottomRow];//right shoulder
			if (gamePanel.tileManager.tile[tileNum1].collision == true
					|| gamePanel.tileManager.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed) / (gamePanel.tileSize);
			tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol] [entityTopRow];//player's head
			tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol] [entityBottomRow];//player's feet
			if (gamePanel.tileManager.tile[tileNum1].collision == true
					|| gamePanel.tileManager.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed) / (gamePanel.tileSize);
			tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol] [entityTopRow];//head
			tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol] [entityBottomRow];//feet
			if (gamePanel.tileManager.tile[tileNum1].collision == true
					|| gamePanel.tileManager.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;	
		}
	}
}

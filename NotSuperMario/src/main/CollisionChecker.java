package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gamePanel;

	/* constructor */
	public CollisionChecker (GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		
	}

	/* check collisions of any entities including players. instead of
	 * left corner of player's rectangle, it is calculated to collide when
	 * the left "shoulder"  would hit. doesn't use rectangle.intersect
	 * as a memory saver, only checks the 2 tiles in the direction the player
	 * is moving.  when using objectChecking will take advantage of the
	 * retangle.intersect */
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
	
	/* check object collision 
	 * @param entityIn 
	 * @param player checks if the entity is player or NPC
	 * */
	public int checkObject (Entity entityIn, boolean isItPlayer) {
		int index = 999 ;
		
		for (int i = 0; i < gamePanel.obj.length ; i++) {
			if (gamePanel.obj[i] != null ) {
				//get entity's solidArea position
				entityIn.solidArea.x = entityIn.worldX + entityIn.solidArea.x ;
				entityIn.solidArea.y = entityIn.worldY + entityIn.solidArea.y ;
				//get object's solidArea position
				gamePanel.obj[i].solidArea.x = gamePanel.obj[i].worldX + gamePanel.obj[i].solidArea.x ;
				gamePanel.obj[i].solidArea.y = gamePanel.obj[i].worldY + gamePanel.obj[i].solidArea.y ;
			
				switch (entityIn.direction) {
				case "up":
					entityIn.solidArea.y = entityIn.solidArea.y - entityIn.speed ;
					if (entityIn.solidArea.intersects(gamePanel.obj[i].solidArea)) {
						System.out.println("up collision!");
						if (gamePanel.obj[i].collision == true) {
							entityIn.collisionOn = true;
						}
						if (isItPlayer == true) {
							index = i ;
						}
					}
					break;
				case "down":
					entityIn.solidArea.y = entityIn.solidArea.y + entityIn.speed ;
					if (entityIn.solidArea.intersects(gamePanel.obj[i].solidArea)) {
						System.out.println("down collision!");
						if (gamePanel.obj[i].collision == true) {
							entityIn.collisionOn = true;
						}
						if (isItPlayer == true) {
							index = i ;
						}
					}
					break;
				case "left":
					entityIn.solidArea.x = entityIn.solidArea.x - entityIn.speed ;
					if (entityIn.solidArea.intersects(gamePanel.obj[i].solidArea)) {
						System.out.println("left collision!");
						if (gamePanel.obj[i].collision == true) {
							entityIn.collisionOn = true;
						}
						if (isItPlayer == true) {
							index = i ;
						}
					}
					break;
				case "right":
					entityIn.solidArea.x = entityIn.solidArea.x + entityIn.speed ;
					if (entityIn.solidArea.intersects(gamePanel.obj[i].solidArea)) {
						System.out.println("right collision!");
						if (gamePanel.obj[i].collision == true) {
							entityIn.collisionOn = true;
						}
						if (isItPlayer == true) {
							index = i ;
						}
					}
					break;	
				}
				entityIn.solidArea.x = entityIn.solidAreaDefaultX;
				entityIn.solidArea.y = entityIn.solidAreaDefaultY;
				gamePanel.obj[i].solidArea.x = gamePanel.obj[i].solidAreaDefaultX;
				gamePanel.obj[i].solidArea.y = gamePanel.obj[i].solidAreaDefaultY;
			}
		}
		return index;
	}
}

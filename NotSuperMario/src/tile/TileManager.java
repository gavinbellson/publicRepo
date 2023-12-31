package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	
	/* class variables */
	GamePanel gamePanel;
	Tile[] tile;
	int mapTileNum [] [];

	/* constructor */
	public TileManager (GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		tile = new Tile[10];
		mapTileNum = new int [gamePanel.maxScreenCol] [gamePanel.maxScreenRow];
		getTileImage();
		loadMap("/maps/map01.txt");
	}
	
	/* get tile image method */
	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/* map loader */
	public void loadMap(String mapFilePath) {
		try {
			InputStream inputStream = getClass().getResourceAsStream(mapFilePath);
			BufferedReader br = new BufferedReader (new InputStreamReader(inputStream));
			int col = 0;
			int row = 0;
			while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
				String line = br.readLine();
				while (col < gamePanel.maxScreenCol) {
					String numbers [] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				if (col ==gamePanel.maxScreenCol) {
					col = 0;
					row++;
				}
			}
			br.close();//close bufferedreader
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* draw method */
	public void draw (Graphics2D g2) {
		//g2.drawImage (tile[0].image,0,0,gamePanel.tileSize,gamePanel.tileSize,null);
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
			int tileNum = mapTileNum [col] [row] ;
			System.out.println("tileNum = " + tileNum);
			
			g2.drawImage (tile[tileNum].image,x,y,gamePanel.tileSize,gamePanel.tileSize,null);
			col++;
			x = x + gamePanel.tileSize;
			
			if (col == gamePanel.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y = y + gamePanel.tileSize;
			}
		}
	}
}

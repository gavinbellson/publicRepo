package main;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Score extends Rectangle {

	/**
	 * score variables
	 */
	static int GAME_WIDTH ;
	static int GAME_HEIGHT ;
	int player1 ;
	int player2 ;
	
	/**
	 * constructor method
	 */
	Score (int GAME_WIDTH, int GAME_HEIGHT) {
		Score.GAME_WIDTH = GAME_WIDTH;
		Score.GAME_HEIGHT = GAME_HEIGHT;
	}
	/**
	 * draw method (only method needed since score doesn't move)
	 * @param Graphics g
	 */
	public void draw (Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font ("Consolas",Font.PLAIN, 60));
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		g.drawString(String.valueOf(player1), GAME_WIDTH/2 - 85, 50);
		g.drawString(String.valueOf(player2), GAME_WIDTH/2 + 20, 50);
	}
}

package main;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GameFrame extends JFrame {

	/**
	 * GameFrame variables 
	 */
	GamePanel panel ;
	
	/**
	 * constructor method
	 */
	GameFrame() {
		panel = new GamePanel ();
		this.add(panel);
		this.setTitle("KING PONG");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		
	}

}

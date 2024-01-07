package main;
import javax.swing.JFrame ;

public class Game {

	public static void main(String[] args) {
		JFrame window = new JFrame ();
		GamePanel gamePanel = new GamePanel() ;//create named instance of panel
		window.add(gamePanel);//add gamepanel to frame
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Jeremy 2D Treasure Hunt game copy");
		window.setVisible(true);
		window.pack();
		window.setLocationRelativeTo (null) ;//middle of screen
		gamePanel.setUpGame();//call BEFORE startGameThread
		gamePanel.startGameThread();
	}

}

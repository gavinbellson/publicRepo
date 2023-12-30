import javax.swing.JFrame ;

public class Game {

	public static void main(String[] args) {
		JFrame window = new JFrame ();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Jeremy copy game");
		window.setLocationRelativeTo(null);//middle of screen
		window.setVisible(true);
		window.add(new GamePanel());
		window.pack();

	}

}

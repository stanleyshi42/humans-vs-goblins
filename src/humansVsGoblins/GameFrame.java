package humansVsGoblins;

import javax.swing.*;

public class GameFrame extends JFrame {

	GameFrame() {
		//GamePanel gamePanel = new GamePanel();
		this.setTitle("Human Vs Goblin");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		//this.add(gamePanel);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		//gamePanel.startGameThread();
	}

}

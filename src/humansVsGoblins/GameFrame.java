package humansVsGoblins;

import javax.swing.*;

import entities.Goblin;
import entities.Player;

public class GameFrame extends JFrame {

	GameFrame() {
		//GamePanel gamePanel = new GamePanel();
		this.setTitle("Human Vs Goblin");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		Player player = new Player();
        Goblin enemy = new Goblin(200, 200, 10, 3, 1); 

        CombatWindow combatWindow = new CombatWindow(player, enemy);
		
		//this.add(gamePanel);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		//gamePanel.startGameThread();
	}

}

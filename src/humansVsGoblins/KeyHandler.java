package humansVsGoblins;

import entities.Goblin;
import entities.Player;
import tile.TileResource;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class KeyHandler extends MouseAdapter {
	private JPanel selectedPanel;
	private final JPanel gamePanel;
	private final Player player;
	private final TileResource tile;
	private final PossibleMove moves;
	private ArrayList<Goblin> goblins;
	int[][] mapTile;

	public KeyHandler(JPanel gamePanel, Player player, TileResource tile, PossibleMove moves, ArrayList<Goblin> goblins) {
		this.gamePanel = gamePanel;
		this.player = player;
		this.tile = tile;
		this.moves = moves;
		this.goblins = goblins;
		this.mapTile = tile.getMap();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JPanel panel = (JPanel) gamePanel.getComponentAt(e.getPoint());
		if (panel == null || panel == gamePanel) {
			return;
		}
		selectedPanel = panel;
		int coordX = Integer.parseInt(selectedPanel.getName().split(" ")[1]);
		int coordY = Integer.parseInt(selectedPanel.getName().split(" ")[0]);
		moves.createMoves();

		if (moves.getMoves().contains(selectedPanel.getName())) {
			if (mapTile[coordY][coordX] != 1){
				player.setGridY(coordY);
				player.setGridX(coordX);
				moves.createMoves();
				System.out.println(player.attack);

				// Check if player moved into a goblin
				checkCombat();

				for (Goblin g : goblins) {
					g.move();
				}
				// Check if goblin moved into player
				checkCombat();
			}



		}
	}

	// Check if the player is in the same space as a goblin and start combat if needed
	public void checkCombat() {
		for (Goblin g : goblins) {
			if (g.getX() == player.getGX() && g.getY() == player.getGY()) {
				new CombatWindow((GamePanel) gamePanel, player, g);
				goblins.remove(g);
				spawnGoblin();
				break;
			}
		}
	}

	// Spawn a goblin in an empty tile
	public void spawnGoblin() {
		Random random = new Random();
		int randX = 0;
		int randY = 0;
		boolean collision = false;

		// Generate random coordinates for an empty tile
		do {
			randX = random.nextInt(20);
			randY = random.nextInt(20);

			if (randX == player.getGX() && randY == player.getGY())
				collision = true;

			for (Goblin g : goblins) {
				if (randX == g.getX() && randY == g.getY()) {
					collision = true;
				}
			}

		} while (collision);

		goblins.add(new Goblin(randX, randY));
	}
}

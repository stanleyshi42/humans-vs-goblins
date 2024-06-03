package humansVsGoblins;

import entities.Goblin;
import entities.Player;
import tile.TileResource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/*
 * MouseHandler is used to gather mouse inputs from the player.
 * This class will move players where they request to on the board (if valid)
 * and call events to occur if collision occurs such as combat and loot windows.
 */

public class MouseHandler extends MouseAdapter {
	// Initializing variable for use
	private JPanel selectedPanel;
	private final GamePanel gamePanel;
	private final Player player;
	private final PossibleMove moves;
	int[][] mapTile;

	public MouseHandler(GamePanel gamePanel, Player player, TileResource tile, PossibleMove moves) {
		// loads all the components needed into the constructor using reference
		this.gamePanel = gamePanel;
		this.player = player;
		this.moves = moves;
		this.mapTile = tile.getMap();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JPanel panel = (JPanel) gamePanel.getComponentAt(e.getPoint());
		if (panel == null || panel == gamePanel) {
			return;
		}
		// Set selected panel to the panel being clicked on
		selectedPanel = panel;
		// panel are named based on the grid coordinates
		int coordX = Integer.parseInt(selectedPanel.getName().split(" ")[1]);
		int coordY = Integer.parseInt(selectedPanel.getName().split(" ")[0]);
		moves.createMoves();
		// If there is a possible move player can move there
		if (moves.getMoves().contains(selectedPanel.getName())) {
			// If a tree is not there player can move
			if (mapTile[coordY][coordX] != 1){
				// move player
				player.setGridY(coordY);
				player.setGridX(coordX);
				// Refresh the possible movement of player based on new location
				moves.createMoves();

				// Check if player moved into a goblin
				gamePanel.checkCombat();
				gamePanel.checkChest();

				// After player moved, the goblin can move
				for (Goblin g : gamePanel.getGoblins()) {
					g.move(mapTile);
				}
				// Check if goblin moved into player
				gamePanel.checkCombat();
			}
		}
	}
}

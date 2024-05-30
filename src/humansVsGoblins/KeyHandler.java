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
	private final GamePanel gamePanel;
	private final Player player;
	private final TileResource tile;
	private final PossibleMove moves;
	int[][] mapTile;

	public KeyHandler(GamePanel gamePanel, Player player, TileResource tile, PossibleMove moves) {
		this.gamePanel = gamePanel;
		this.player = player;
		this.tile = tile;
		this.moves = moves;
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

				// Check if player moved into a goblin
				gamePanel.checkCombat();

				for (Goblin g : gamePanel.getGoblins()) {
					g.move();
				}
				// Check if goblin moved into player

				gamePanel.checkCombat();

			}
		}
	}
}

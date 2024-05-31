package humansVsGoblins;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import entities.Goblin;
import entities.Player;
import tile.TileResource;

public class MovementListener implements KeyListener {

	private int key;
	private JPanel selectedPanel;
	private final GamePanel gamePanel;
	private final Player player;
	private final TileResource tile;
	private final PossibleMove moves;
	int[][] mapTile;

	public MovementListener(GamePanel gamePanel, Player player, TileResource tile, PossibleMove moves) {
		this.gamePanel = gamePanel;
		this.player = player;
		this.tile = tile;
		this.moves = moves;
		this.mapTile = tile.getMap();
	}

	private void movePlayer(int key) {
		
		JPanel panel;
		switch (key) {
			case KeyEvent.VK_W:
				panel = (JPanel) gamePanel.getComponentAt(new Point(player.getGX()*48, (player.getGY()*48)-48));
				break;
			case KeyEvent.VK_UP:
				panel = (JPanel) gamePanel.getComponentAt(new Point(player.getGX()*48, (player.getGY()*48)-48));
				break;
			case KeyEvent.VK_A:
				panel = (JPanel) gamePanel.getComponentAt(new Point((player.getGX()*48)-48, player.getGY()*48));
				break;
			case KeyEvent.VK_LEFT:
				panel = (JPanel) gamePanel.getComponentAt(new Point((player.getGX()*48)-48, player.getGY()*48));
				break;
			case KeyEvent.VK_S:
				panel = (JPanel) gamePanel.getComponentAt(new Point(player.getGX()*48, (player.getGY()*48)+48));
				break;
			case KeyEvent.VK_DOWN:
				panel = (JPanel) gamePanel.getComponentAt(new Point(player.getGX()*48, (player.getGY()*48)+48));
				break;
			case KeyEvent.VK_D:
				panel = (JPanel) gamePanel.getComponentAt(new Point((player.getGX()*48)+48, player.getGY()*48));
				break;
			case KeyEvent.VK_RIGHT:
				panel = (JPanel) gamePanel.getComponentAt(new Point((player.getGX()*48)+48, player.getGY()*48));
				break;
			default:
				return;
		}

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
					g.move(mapTile);
				}
				// Check if goblin moved into player

				gamePanel.checkCombat();

			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		return;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.key = e.getKeyCode();

		switch (key) {
			case KeyEvent.VK_W:
			case KeyEvent.VK_A:
			case KeyEvent.VK_S:
			case KeyEvent.VK_D:
			case KeyEvent.VK_UP:
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_RIGHT:
				movePlayer(key);
				break;
			case KeyEvent.VK_I:
				gamePanel.openInventory();
				break;
			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		return;
	}

}

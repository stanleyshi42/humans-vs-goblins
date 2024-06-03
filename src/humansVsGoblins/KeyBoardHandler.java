package humansVsGoblins;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import entities.Goblin;
import entities.Player;
import tile.TileResource;

/*
 * KeyBoardHandler is used to gather keyboard inputs from the player.
 * This class will move players where they request to on the board (if valid)
 * and call events to occur if collision occurs such as combat and loot windows.
 */

public class KeyBoardHandler implements KeyListener {

	private int key;						// Key that was pressed.
	private JPanel selectedPanel;			// The panel that the player wants to move to.
	private final GamePanel gamePanel;		// The game panel that called the listener.
	private final Player player;			// The player that is doing the moving.
	private final PossibleMove moves;		// The series of possible moves that can be made.
	int[][] mapTile;						// 2D array of all the tiles in the map.

	// Simple constructor, grab the panel, player, tile,
	// and possible moves that the player can make
	public KeyBoardHandler(GamePanel gamePanel, Player player, TileResource tile, PossibleMove moves) {
		this.gamePanel = gamePanel;
		this.player = player;
		this.moves = moves;
		this.mapTile = tile.getMap();
	}

	// movePlayer() will attempt to move the player where 
	// the user requested. Grabs the component in the specific
	// direction given, checks it's validity as a move, and if
	// valid, will proceed to that direction and trigger events
	// if collision occurs. Trigger events such as combat or loot
	// windows with chests. 
	private void movePlayer(int key) {
		
		// Check which key was pressed.
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

		// Gather coordinates of the requested move.
		selectedPanel = panel;
		int coordX = Integer.parseInt(selectedPanel.getName().split(" ")[1]);
		int coordY = Integer.parseInt(selectedPanel.getName().split(" ")[0]);
		moves.createMoves();

		// If the requested move is a valid move.
		if (moves.getMoves().contains(selectedPanel.getName())) {
			if (mapTile[coordY][coordX] != 1){
				
				// Move player's coordinates
				player.setGridY(coordY);
				player.setGridX(coordX);
				moves.createMoves();

				// Check if player moved into a goblin
				gamePanel.checkCombat();

				// Check if player moved into a chest
				gamePanel.checkChest();

				for (Goblin g : gamePanel.getGoblins()) {
					g.move(mapTile);
				}

				// Check if goblin moved into player
				gamePanel.checkCombat();

			}
		}
	}

	// Not needed, but needs to be defined
	@Override
	public void keyTyped(KeyEvent e) {
		return;
	}


	// keyPressed() will grab whatever key on the keyboard
	// was pressed during runtime and cause a method call
	// based on certain keys. movePlayer() for the movement keys
	// or openInventory() for 'I'
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

	// Not needed, but needs to be defined
	@Override
	public void keyReleased(KeyEvent e) {
		return;
	}

}

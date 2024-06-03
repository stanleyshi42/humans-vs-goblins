package humansVsGoblins;

import entities.Goblin;
import entities.GoblinAttributes;
import entities.Player;
import entities.Treasure;
import tile.TileResource;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * GamePanel represents the game screen/world that the player will interact with.
 * This panel will spawn goblins, chests, the player and call the inventory, loot windows,
 * and the combat windows. This class is the heart of the program where sprite updates,
 * player turns, goblins turns, and painting entities is managed.
 */
public class GamePanel extends JPanel implements Runnable {
	
	// Tile and Screen Settings
	final int tileSize = 16;
	final int scale = 3;
	public final int scaledTileSize = tileSize * scale;
	public final int maxScreenColumns = 20;
	public final int maxScreenRows = 20;
	final Dimension UNITSIZE = new Dimension(48, 48);
	final int screenWidth = scaledTileSize * maxScreenColumns;
	final int screenHeight = scaledTileSize * maxScreenRows;

	// Animation Setting
	public int spriteNum = 1;
	public int spriteCounter = 0;

	// Entities
	Player player = new Player();
	public static ArrayList<Goblin> goblins = new ArrayList<Goblin>();
	public static ArrayList<Treasure> chests = new ArrayList<Treasure>();

	// Tiles
	ArrayList<Tile> mapTiles = new ArrayList<>();
	TileResource tileResource = new TileResource(this);
	PossibleMove possibleMove = new PossibleMove(player, tileResource);

	// Kept as variables for sprite updates
	InventoryPanel inv;
	LootWindow lootWin;

	// Game Loop Variables
	Thread gameThread;
  
  	// Listeners
	MouseHandler mouse = new MouseHandler(this, player, tileResource, possibleMove);
  	KeyBoardHandler keyboard = new KeyBoardHandler(this, player, tileResource, possibleMove);

	public GamePanel() {

		for (int i = 0; i < maxScreenColumns * maxScreenRows; i++) {
			mapTiles.add(new Tile(scaledTileSize));
		}

		// Set world into a grid system
		setLayout(new GridLayout(maxScreenRows, maxScreenColumns, 0, 0));
		for (int i = 0; i < maxScreenColumns * maxScreenRows; i++) {
			JPanel panel = new JPanel();
			// Name each square as the coordinate for usage
			String name = String.format("%d %d", i / maxScreenRows, i % maxScreenColumns);
			panel.setName(name);
			panel.setPreferredSize(UNITSIZE);
			add(panel);
		}

		// Initialize basic JPanel settings
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.setVisible(true);
		this.setFocusable(true);
		this.addMouseListener(new MouseHandler(this, player, tileResource, possibleMove));

    	addListeners(); 			// Add listeners needed for movement
		possibleMove.createMoves();	// Show all the possible moves the player can make
		setUpChest();				// Setup the chests on the screen
		spawnGoblin();				// Spawn the goblins on the screen
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// If player hp > 0, continue game
		if (player.getHp()>0){
			// Draw tiles first
            tileResource.draw(g2);
			// Draw player
			player.draw(g2);
			// Draw goblins and chest
			for (Goblin gob : goblins) {
				gob.draw(g2);
			}
			for (Treasure trea : chests){
				trea.draw(g2);
			}
			possibleMove.draw(g2);
			g2.dispose();

		}
		// If hp is 0, end game
		else {
			gameOver(g2);
			g2.dispose();
			gameThread = null;
		}


	}

	// gameOver() will draw the game over screen upon player death.
	public void gameOver(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0,0,screenWidth,screenHeight);
		graphics.setColor(Color.red);
		graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 50));
		FontMetrics metrics = getFontMetrics(graphics.getFont());
		graphics.drawString("Game Over", (screenWidth - metrics.stringWidth("Game Over")) / 2, screenHeight / 2);
	}

	// Create a game thread that will run the game
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		// Refreshing system of 30 FPS
		double drawInterval = (double) 1000000000 / 30;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			// Delta is > 1 because it is frames per second
			// Refreshes 30 times per seconds
			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}
	// update() updates the spriteNum variable on every tick to let the other classes 
	// know when to swap images to create an animation in the LootWindow, InventoryPanel
	// and this GamePanel itself
	public void update() {
		spriteCounter++;

		if (spriteCounter > 15){
			if (spriteNum == 1){
				spriteNum = 2;
			} else if (spriteNum == 2){
				spriteNum = 1;
			}
			player.update(spriteNum);
			for (Goblin gob: goblins){
				gob.update(spriteNum);
			}

			if(inv != null)
				inv.updateSprites(spriteNum);
			
			if(lootWin != null)
				lootWin.updateSprites(spriteNum);
			
			spriteCounter = 0;
		}
	}

	// Create an inventory window.
	public void openInventory() {
		inv = new InventoryPanel(this, player);
	}

	// Create a loot window for a goblin or chest based on boolean received.
	public void openLootWindow(boolean chest) {
		lootWin = new LootWindow(this, player, chest);
	}

	// Add a new chest object to the list.
	public void addChest(Treasure t) {
		chests.add(t);
	}

	// Remove a chest object from the list.
	public void removeChest(Treasure t) {
		chests.remove(t);
	}

	// Create chests via Treasure class and add them
	// to the list of chests.
	public void setUpChest(){
		for (String ele : tileResource.getChest()){
			String[] re = ele.split(" ");
			addChest(new Treasure(Integer.parseInt(re[0]),Integer.parseInt(re[1]),Boolean.parseBoolean(re[2])));
		}
	}

	// Check if the player has collided with a chest,
	// if so and the chest is unlocked create a loot Window,
	// if the chest is locked and the player has a key,
	// create a loot window.
	public void checkChest() {
		for (Treasure t : chests) {
			if (t.getX() == player.getGX() && t.getY() == player.getGY()) {
				if (!t.getLocked()) {
					openLootWindow(true);
					removeChest(t);
				}
				else if(player.hasKeyInInventory()) {
					player.useKey();
					openLootWindow(true);
					removeChest(t);
				}
				break;
			}
		}
	}

	// Get the list of goblins.
	public ArrayList<Goblin> getGoblins() {
		return goblins;
	}

	// Add a goblin to the list of goblins.
	public void addGoblin(Goblin g) {
		goblins.add(g);
	}

	// Check if the player is in the same space as a goblin and start combat if needed.
	public void removeGoblin(Goblin g) {
		goblins.remove(g);
	}

	// Check if there is collision with a goblin, if so call a combat window.
	public void checkCombat() {
		for (Goblin g : getGoblins()) {
			if (g.getX() == player.getGX() && g.getY() == player.getGY()) {
				new CombatWindow((GamePanel) this, player, g);
				this.removeMouseListener(mouse);
				this.removeKeyListener(keyboard);
				removeGoblin(g);
				break;
			}
		}
	}

	// Spawn a goblin in an empty tile.
	public void spawnGoblin() {
		for (String ele : tileResource.getGoblin()){
			String[] re = ele.split(" ");
			int x = Integer.parseInt(re[0]);
			int y = Integer.parseInt(re[1]);
			int type = Integer.parseInt(re[2]);
			int [] attributes = GoblinAttributes.getAttributes(type);
			int hp = attributes[0];
			int atk = attributes[1];
			int def = attributes[2];
			addGoblin(new Goblin(x,y,hp,atk,def,type));
		}
	}

	// Add the listeners needed for this panel.
	public void addListeners() {
		this.addMouseListener(mouse);
        this.addKeyListener(keyboard);
	}

	// Get the MouseListener connected to this panel.
	public MouseHandler getMouse() {
		return mouse;
	}

	// Get the KeyListener connected to this panel.
	public KeyBoardHandler getKeyboard() {
		return keyboard;
	}
}
package humansVsGoblins;

import entities.Goblin;
import entities.Player;
import entities.Treasure;
import tile.TileResource;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import tile.TileResource;

import javax.swing.*;

/**
 * GamePanel
 */
public class GamePanel extends JPanel implements Runnable {

	final int tileSize = 16;
	final int scale = 3;

	public final int scaledTileSize = tileSize * scale;
	public final int maxScreenColumns = 20;
	public final int maxScreenRows = 20;

	final Dimension UNITSIZE = new Dimension(48, 48);

	final int screenWidth = scaledTileSize * maxScreenColumns;
	final int screenHeight = scaledTileSize * maxScreenRows;

	// Entities
	Player player = new Player();
	public static ArrayList<Goblin> goblins = new ArrayList<Goblin>();
	public static ArrayList<Treasure> chests = new ArrayList<Treasure>();

	ArrayList<Tile> mapTiles = new ArrayList<>();
	TileResource tileResource = new TileResource(this);

	PossibleMove possibleMove = new PossibleMove(player, tileResource);

	// Game Loop Variables
	Thread gameThread;
  
  // Listeners
	KeyHandler mouse = new KeyHandler(this, player, tileResource, possibleMove);
  MovementListener keyboard = new MovementListener(this, player, tileResource, possibleMove);

	public GamePanel() {
		tileResource.loadRandomMap();
		// goblins.add(new Goblin(7, 7));
		// goblins.add(new Goblin(1, 3));
		spawnGoblin();
		spawnGoblin();
		spawnGoblin();
		setUpChest();

		for (int i = 0; i < maxScreenColumns * maxScreenRows; i++) {
			mapTiles.add(new Tile(scaledTileSize));
		}

		setLayout(new GridLayout(maxScreenRows, maxScreenColumns, 0, 0));
		for (int i = 0; i < maxScreenColumns * maxScreenRows; i++) {
			JPanel panel = new JPanel();
			String name = String.format("%d %d", i / maxScreenRows, i % maxScreenColumns);
			panel.setName(name);
			panel.setPreferredSize(UNITSIZE);
			add(panel);
		}

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.setVisible(true);
		this.setFocusable(true);
		this.addMouseListener(new KeyHandler(this, player, tileResource, possibleMove));
    	addListeners();
		possibleMove.createMoves();
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (player.getHp()>0){
            tileResource.draw(g2);
			player.draw(g2);
			for (Goblin gob : goblins) {
				gob.draw(g2);
			}
			for (Treasure trea : chests){
				trea.draw(g2);
			}
			possibleMove.draw(g2);
			g2.dispose();

		}
		else {
			gameOver(g2);
			g2.dispose();
		}


	}
	public void gameOver(Graphics graphics) {
		//game over screen
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0,0,screenWidth,screenHeight);
		graphics.setColor(Color.red);
		graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 50));
		FontMetrics metrics = getFontMetrics(graphics.getFont());
		graphics.drawString("Game Over", (screenWidth - metrics.stringWidth("Game Over")) / 2, screenHeight / 2);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = (double) 1000000000 / 30;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}

	public void update() {

	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Goblin> getGoblins() {
		return goblins;
	}

	public void addGoblin(int randX, int randY) {
		goblins.add(new Goblin(randX, randY));
	}

	public void openInventory() {
		new InventoryPanel(this, player);
	}

	public void addChest(Treasure t) {
		chests.add(t);
	}
	public void removeChest(Treasure t) {
		chests.remove(t);
	}

	public void setUpChest(){
		for (String ele : tileResource.getChest()){
			System.out.println(ele);
			String[] re = ele.split(" ");
			addChest(new Treasure(Integer.parseInt(re[0]),Integer.parseInt(re[1]),Boolean.parseBoolean(re[2])));
		}
	}
	public void checkChest() {
		for (Treasure t : chests) {
			if (t.getX() == player.getGX() && t.getY() == player.getGY()) {
				System.out.println(t.getLocked());
				if (!t.getLocked()) {
					new LootWindow(this, player, true);
					removeChest(t);
				}
				else if(player.hasKeyInInventory()) {
					player.useKey();
					t.setUnlock();
					new LootWindow(this, player, true);
					removeChest(t);
				}
				break;
			}
		}
	}

    // Check if the player is in the same space as a goblin and start combat if needed
	public void removeGoblin(Goblin g) {
		goblins.remove(g);
	}

	public void checkCombat() {
		for (Goblin g : getGoblins()) {
			if (g.getX() == player.getGX() && g.getY() == player.getGY()) {
				new CombatWindow((GamePanel) this, player, g);
				this.removeMouseListener(mouse);
				this.removeKeyListener(keyboard);
				removeGoblin(g);
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
		boolean isOccopied = true;
		int[][] mapTiles = tileResource.getMap();

		// Generate random coordinates for an empty tile
		do {
			randX = random.nextInt(20);
			randY = random.nextInt(20);

			if (mapTiles[randY][randX] == 0)
				isOccopied = false;

			if (randX == player.getGX() && randY == player.getGY())
				isOccopied = true;

			for (Goblin g : getGoblins()) {
				if (randX == g.getX() && randY == g.getY()) {
					isOccopied = true;
				}
			}

		} while (isOccopied);

		addGoblin(randX, randY);
	}

	public void addListeners() {
		this.addMouseListener(mouse);
        this.addKeyListener(keyboard);
	}

	public KeyHandler getMouse() {
		return mouse;
	}

	public MovementListener getKeyboard() {
		return keyboard;
	}
}
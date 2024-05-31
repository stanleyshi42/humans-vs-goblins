package humansVsGoblins;

import entities.Goblin;
import entities.Player;
import tile.TileResource;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

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

	ArrayList<Tile> mapTiles = new ArrayList<>();
	TileResource tileResource = new TileResource(this);

	PossibleMove possibleMove = new PossibleMove(player, tileResource);

	// Game Loop Variables
	Thread gameThread;
	boolean paused = false;

	public GamePanel() {

		// goblins.add(new Goblin(7, 7));
		// goblins.add(new Goblin(1, 3));
		spawnGoblin();
		spawnGoblin();
		spawnGoblin();

		for (int i = 0; i < maxScreenColumns * maxScreenRows; i++) {
			mapTiles.add(new Tile(scaledTileSize));
		}

		setLayout(new GridLayout(maxScreenRows, maxScreenColumns, 1, 1));
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
		// this.addKeyListener(keyHandler);
		this.setFocusable(true);
		this.addMouseListener(new KeyHandler(this, player, tileResource, possibleMove));
		// this.addKeyListener(new MovementListener());
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
			possibleMove.draw(g2);
			g2.dispose();

		} else if (player.curHp == 0){
			tileResource.draw(g2);
			player.curHp = -1;
			g2.dispose();
		}else {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
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

	public void pauseGameThread() {
		paused = true;
	}

	public void unPauseGameThread() {
		paused = false;
	}

	@Override
	public void run() {
		double drawInterval = (double) 1000000000 / 30;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		while (gameThread != null) {
			if (paused)
				continue;

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

	public void removeGoblin(Goblin g) {
		goblins.remove(g);
	}

	// Check if player is in the same space as a goblin and start combat if needed
	public void checkCombat() {
		for (Goblin g : getGoblins()) {
			if (g.getX() == player.getGX() && g.getY() == player.getGY()) {
				new CombatWindow((GamePanel) this, player, g);
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
}
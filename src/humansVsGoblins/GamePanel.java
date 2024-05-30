package humansVsGoblins;

import entities.Goblin;
import entities.Player;
import tile.TileResource;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;

/**
 * GamePanel
 */
public class GamePanel extends JPanel implements Runnable {

    final int tileSize = 16;
    final int scale = 3;

    public final int scaledTileSize = tileSize * scale;
    public final int maxScreenColumns = 20;
    public final int maxScreenRows = 20;

    final Dimension UNITSIZE = new Dimension(48,48);

    final int screenWidth = scaledTileSize*maxScreenColumns;
    final int screenHeight = scaledTileSize*maxScreenRows;
    Player player = new Player();
    
    ArrayList<Goblin> goblins =new ArrayList<Goblin>();

    
    ArrayList<Tile> mapTiles = new ArrayList<>();
    Thread gameThread;
    TileResource tileResource = new TileResource(this);

    PossibleMove possibleMove = new PossibleMove(this,player,tileResource);

    boolean paused = false;

    public GamePanel() {
    	
    	goblins.add(new Goblin(19,5));
    	goblins.add(new Goblin(10,1));
    	
        for(int i = 0; i < maxScreenColumns*maxScreenRows; i++) {
            mapTiles.add(new Tile(scaledTileSize));
        }

        setLayout(new GridLayout(maxScreenRows,maxScreenColumns,1,1));
        for(int i = 0; i < maxScreenColumns*maxScreenRows; i++) {
            JPanel panel = new JPanel();
            String name = String.format("%d %d",
                    i / maxScreenRows, i % maxScreenColumns);
            panel.setName(name);
            panel.setPreferredSize(UNITSIZE);
            add(panel);
        }

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setVisible(true);
        //this.addKeyListener(keyHandler);
        this.setFocusable(true);
        this.addMouseListener(new KeyHandler(this,player, tileResource, possibleMove, goblins));
        possibleMove.createMoves();

    }

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		tileResource.draw(g2);
		player.draw(g2);
		for (Goblin gob : goblins) {
			gob.draw(g2);
		}
		possibleMove.draw(g2);
		g2.dispose();

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
            if(paused) continue;

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
}
package humansVsGoblins;

import entities.Player;
import tile.TileResource;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * GamePanel
 */
public class GamePanel extends JPanel implements Runnable {

    final int tileSize = 16;
    final int scale = 3;

    public final int scaledTileSize = tileSize * scale;
    public final int maxScreenColumns = 20;
    public final int maxScreenRows = 20;

    final int screenWidth = scaledTileSize*maxScreenColumns;
    final int screenHeight = scaledTileSize*maxScreenRows;
    Player player = new Player();
    
    ArrayList<Tile> mapTiles = new ArrayList<>();
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    TileResource tileResource = new TileResource(this);

    public GamePanel() {

        for(int i = 0; i < maxScreenColumns*maxScreenRows; i++) {
            mapTiles.add(new Tile(scaledTileSize));
        }

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setVisible(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        
        Graphics2D g2 = (Graphics2D)g;
        tileResource.draw(g2);
        g2.setColor(Color.BLUE);
        g2.fillRect(player.getX(), player.getY(), scaledTileSize,scaledTileSize);
        g2.dispose();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 /30;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){
        if (keyHandler.upPressed){
            player.setY(-5);
        } else if (keyHandler.downPressed){
            player.setY(5);
        } else if (keyHandler.rightPressed){
            player.setX(5);
        } else if (keyHandler.leftPressed){
            player.setX(-5);
        }
    }
}
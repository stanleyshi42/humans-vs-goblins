package humansVsGoblins;

import entities.Player;

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

    final int scaledTileSize = tileSize * scale;
    final int maxScreenColumns = 20;
    final int maxScreenRows = 16;

    final int screenWidth = scaledTileSize*maxScreenColumns;
    final int screenHeight = scaledTileSize*maxScreenRows;
    Player player = new Player();
    
    ArrayList<Tile> mapTiles = new ArrayList<>();
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();

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
        for(int i = 0; i < mapTiles.size(); i++) {
            int row = i/maxScreenColumns;
            int column = i%maxScreenColumns;
            g2.setColor(Color.WHITE);
            g2.fillRect(column*scaledTileSize, row*scaledTileSize, scaledTileSize, scaledTileSize);
            g2.setColor(Color.BLACK);
            g2.fillRect(column*scaledTileSize+2, row*scaledTileSize+2, scaledTileSize-4, scaledTileSize-4);
        }
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
        double nextDrawTime = System.nanoTime() + drawInterval;
        while(gameThread != null){
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){
        if (keyHandler.upPressed){
            player.setY(-4);
        } else if (keyHandler.downPressed){
            player.setY(4);
        } else if (keyHandler.rightPressed){
            player.setX(4);
        } else if (keyHandler.leftPressed){
            player.setX(-4);
        }
    }
}
package humansVsGoblins;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * GamePanel
 */
public class GamePanel extends JPanel {

    final int tileSize = 16;
    final int scale = 3;

    final int scaledTileSize = tileSize * scale;
    final int maxScreenColumns = 20;
    final int maxScreenRows = 16;

    final int screenWidth = scaledTileSize*maxScreenColumns;
    final int screenHeight = scaledTileSize*maxScreenRows;
    
    ArrayList<Tile> mapTiles = new ArrayList<>();

    GamePanel() {

        for(int i = 0; i < maxScreenColumns*maxScreenRows; i++) {
            mapTiles.add(new Tile(scaledTileSize));
        }

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setVisible(true);
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
    }
    
}
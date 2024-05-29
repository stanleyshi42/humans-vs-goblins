package humansVsGoblins;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InventoryPanel extends JPanel {
    final int tileSize = 16;
    final int scale = 3;

    final int scaledTileSize = tileSize * scale;
    final int maxScreenColumns = 3;
    final int maxScreenRows = 3;

    final int screenWidth = scaledTileSize*maxScreenColumns;
    final int screenHeight = scaledTileSize*maxScreenRows;

    ArrayList<Tile> inventoryTiles = new ArrayList<>();

    InventoryPanel() {

        for(int i = 0; i < maxScreenColumns*maxScreenRows; i++) {
            inventoryTiles.add(new Tile(scaledTileSize));
        }

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setVisible(true);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        JLabel title = new JLabel("INVENTORY");
        this.add(title);

        for(Tile t: inventoryTiles) {
            this.add(t);
        }

    }

    // @Override
    // protected void paintComponent(Graphics g) {
        
    //     Graphics2D g2 = (Graphics2D)g;
    //     for(int i = 0; i < inventoryTiles.size(); i++) {
    //         int row = i/maxScreenColumns;
    //         int column = i%maxScreenColumns;
    //         g2.setColor(Color.WHITE);
    //         g2.fillRect(column*scaledTileSize, row*scaledTileSize, scaledTileSize, scaledTileSize);
    //         g2.setColor(Color.BLACK);
    //         g2.fillRect(column*scaledTileSize+2, row*scaledTileSize+2, scaledTileSize-4, scaledTileSize-4);
    //     }
    // }

}

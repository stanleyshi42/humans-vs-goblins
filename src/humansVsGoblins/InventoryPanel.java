package humansVsGoblins;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class InventoryPanel extends JPanel {
    final int tileSize = 16;
    final int scale = 3;

    final int scaledTileSize = tileSize * scale;
    final int maxScreenColumns = 3;
    final int maxScreenRows = 3;

    ArrayList<Tile> inventoryTiles = new ArrayList<>();

    InventoryPanel() {

        for(int i = 0; i < maxScreenColumns*maxScreenRows; i++) {
            inventoryTiles.add(new Tile(scaledTileSize));
        }

        this.setPreferredSize(new Dimension(500, 400));
        this.setSize(new Dimension(500, 400));
        //this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.setVisible(true);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(10,10,10,10));

        JPanel titleCont = new JPanel();
        titleCont.setSize(new Dimension(400, 100));
        JLabel title = new JLabel("INVENTORY");
        //titleCont.setBackground(Color.BLACK);
        // title.setBackground(Color.BLACK);
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Sans-serif", Font.BOLD, 36));
        titleCont.add(title);
        this.add(titleCont);

        JPanel tileCont = new JPanel();
        tileCont.setLayout(new GridLayout(3, 3, 10, 5));
        //tileCont.setBackground(Color.BLACK);
        tileCont.setSize(new Dimension(300, 100));
        tileCont.setPreferredSize(new Dimension(400, 300));

        for(Tile t: inventoryTiles) {
            t.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            tileCont.add(t);
        }
        this.add(tileCont);

    }

}

package humansVsGoblins;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import items.Item;

public class InventoryPanel extends JPanel {
    final int tileSize = 16;
    final int scale = 3;

    final int scaledTileSize = tileSize * scale;
    final int maxScreenColumns = 3;
    final int maxScreenRows = 3;

    ArrayList<InventorySlot> inventoryTiles = new ArrayList<>();

    InventoryPanel() {

        ImageIcon sprite = new ImageIcon("Resources/player.png");
        inventoryTiles.add(new InventorySlot(scaledTileSize, new Item("Player", sprite)));
        for(int i = 1; i < maxScreenColumns*maxScreenRows; i++) {
            inventoryTiles.add(new InventorySlot(scaledTileSize, null));
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
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Sans-serif", Font.BOLD, 36));
        titleCont.add(title);
        this.add(titleCont);

        JPanel tileCont = new JPanel();
        tileCont.setLayout(new GridLayout(3, 3, 10, 5));
        tileCont.setSize(new Dimension(300, 100));
        tileCont.setPreferredSize(new Dimension(400, 300));

        for(InventorySlot t: inventoryTiles) {
            t.displayItem();
            tileCont.add(t);
        }
        this.add(tileCont);

    }

}

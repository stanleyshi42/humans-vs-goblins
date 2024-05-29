package humansVsGoblins;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import items.Item;

public class InventorySlot extends JButton {

    Item itemInSlot;
    
    InventorySlot(int scaledSize, Item item) {
        //this.setSize(new Dimension(scaledSize, scaledSize));
        //this.setPreferredSize(new Dimension(scaledSize, scaledSize));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setVerticalTextPosition   ( SwingConstants.BOTTOM ) ;
        //this.setVerticalAlignment      ( SwingConstants.TOP ) ;
        this.setBackground(Color.WHITE);
        this.setVisible(true);
        this.setFocusable(false);
        itemInSlot = item;
    }

    public void displayItem() {
        if(itemInSlot == null) return;

        ImageIcon sprite = itemInSlot.getSprite();
        Image unScaled = sprite.getImage();
        Image scaledImg = unScaled.getScaledInstance(sprite.getIconWidth()*3,
            sprite.getIconHeight()*3, java.awt.Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(scaledImg));
        this.setText(itemInSlot.getName());
    }
}

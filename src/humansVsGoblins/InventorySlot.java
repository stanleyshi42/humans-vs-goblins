package humansVsGoblins;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import items.Armor;
import items.Item;
import items.Potion;
import items.Weapon;

public class InventorySlot extends JButton {

    Item itemInSlot;
    
    InventorySlot(int scaledSize, Item item) {
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.setFont(new Font("Sans-serif", Font.BOLD, 16));
        this.setBackground(Color.WHITE);
        this.setVisible(true);
        this.setFocusable(false);
        itemInSlot = item;
        
    }

    private void displayStatsOfItem() {
        if(itemInSlot instanceof Potion) {
            this.setText("<html>"+itemInSlot.getName()+" <br /><center>Healing: "
            +((Potion)itemInSlot).healing+"</center></html>");
        }
        else if(itemInSlot instanceof Armor) {
            this.setText("<html>"+itemInSlot.getName()+" <br /><center>Defense: "
            +((Armor)itemInSlot).defense+"</center></html>");
        }
        else if(itemInSlot instanceof Weapon) {
            this.setText("<html>"+itemInSlot.getName()+" <br /><center>Attack: "
            +((Weapon)itemInSlot).attack+"</center></html>");
        }
        else {
            this.setText("<html>"+itemInSlot.getName()+"</html>");
        }

    }

    public void displayItem() {
        if(itemInSlot == null) return;

        ImageIcon sprite = itemInSlot.getSprite();
        if(sprite != null) {
            Image unScaled = sprite.getImage();
            Image scaledImg = unScaled.getScaledInstance(sprite.getIconWidth()*3,
                sprite.getIconHeight()*3, java.awt.Image.SCALE_SMOOTH);
            this.setIcon(new ImageIcon(scaledImg));
        }
        displayStatsOfItem();
    }

    public void displayMajorItem() {
        if(itemInSlot == null) return;

        ImageIcon sprite = itemInSlot.getSprite();
        if(sprite != null) {
            Image unScaled = sprite.getImage();
            Image scaledImg = unScaled.getScaledInstance(sprite.getIconWidth()*5,
                sprite.getIconHeight()*5, java.awt.Image.SCALE_SMOOTH);
            this.setIcon(new ImageIcon(scaledImg));
        }
        displayStatsOfItem();
    }
    
}

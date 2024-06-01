package humansVsGoblins;

import java.awt.Color;
import java.awt.Dimension;
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

/*
 * InventorySlot represents an Item object with JButton functionality.
 * Used with the LootWindow and InventoryPanel frames to equip items,
 * insert items into your inventory from chests or goblins, or use items
 * such as potions.
 */

public class InventorySlot extends JButton {

    Item itemInSlot;    // The Item that the InventorySlot is representing.
    int spriteNum;      // A sprite counter to keep track of which sprite of the item
                        // to display.
    
    // Constructor which initializes the functionality and
    // and look of a InventorySlot
    InventorySlot(Item item) {
        spriteNum = 1;
        this.setSize(new Dimension(100, 150));
        this.setPreferredSize(new Dimension(100, 150));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.setFont(new Font("Sans-serif", Font.BOLD, 15));
        this.setBackground(Color.WHITE);
        this.setVisible(true);
        this.setFocusable(false);
        itemInSlot = item;
        
    }

    // displayStatsOfItem() will display the name and stats
    // of the item that is occupying this InventorySlot.
    // Ex: A weapon item will now show it's attack stat
    // and it's name in the GUI.
    // If an Item has no stat such as a Key, then only
    // only the name will display.
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

    // displayItem() will insert the sprite into
    // the inventorySlot dedicated to the specific
    // item the inventorySlot represents.
    // It scales the sprite image by 3.
    // Used for minor inventorySlots like loot slots
    // and pouch slots.
    public void displayItem() {
        if(itemInSlot == null) return;

        ImageIcon sprite;
        if(spriteNum == 1)
            sprite = itemInSlot.getSprite1();
        else
            sprite = itemInSlot.getSprite2();

        if(sprite != null) {
            Image unScaled = sprite.getImage();
            Image scaledImg = unScaled.getScaledInstance(sprite.getIconWidth()*3,
                sprite.getIconHeight()*3, java.awt.Image.SCALE_SMOOTH);
            this.setIcon(new ImageIcon(scaledImg));
        }
        displayStatsOfItem();
    }

    // displayMajorItem() will insert the sprite into
    // the inventorySlot dedicated to the specific
    // item the inventorySlot represents.
    // It scales the sprite image by 5.
    // Used for major inventorySlots like currently
    // equipped items.
    public void displayMajorItem() {
        if(itemInSlot == null) return;

        this.setIconTextGap(8);
        this.setFont(new Font("Sans-serif", Font.BOLD, 17));

        ImageIcon sprite;
        if(spriteNum == 1)
            sprite = itemInSlot.getSprite1();
        else
            sprite = itemInSlot.getSprite2();

        if(sprite != null) {
            Image unScaled = sprite.getImage();
            Image scaledImg = unScaled.getScaledInstance(sprite.getIconWidth()*5,
                sprite.getIconHeight()*5, java.awt.Image.SCALE_SMOOTH);
            this.setIcon(new ImageIcon(scaledImg));
        }
        displayStatsOfItem();
    }

    // update() is given a spriteNum and
    // updates the spriteNum variable.
    // Used to alter which sprite will be
    // shown during runtime (animation)
    public void update(int spriteNum) {
        this.spriteNum = spriteNum;
    }

    // Simple getter
    public int getSpriteNum() {
        return spriteNum;
    }
    
}

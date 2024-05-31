package entities;

import javax.swing.*;
import java.awt.*;

public class Treasure extends Entity {
    boolean locked;
    Image image;
    ImageIcon icon;
    public Treasure(int x, int y, boolean locked){
        this.x = x;
        this.y = y;
        this.locked = locked;
        if (locked){
            this.icon = new ImageIcon("Resources/lockedChest.png");
            this.image = icon.getImage().getScaledInstance(icon.getIconWidth() * 3, icon.getIconHeight() * 3,
                    java.awt.Image.SCALE_SMOOTH);
        } else {
            this.icon = new ImageIcon("Resources/chest.png");
            this.image = icon.getImage().getScaledInstance(icon.getIconWidth() * 3, icon.getIconHeight() * 3,
                    java.awt.Image.SCALE_SMOOTH);
        }
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public boolean getLocked(){
        return this.locked;
    }

    public void setUnlock(){
        this.locked = false;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, this.x * 48, this.y * 48, 48, 48, null);
    }
}

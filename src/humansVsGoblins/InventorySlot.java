package humansVsGoblins;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class InventorySlot extends JButton {
    
    InventorySlot(int scaledSize) {
        this.setSize(new Dimension(scaledSize, scaledSize));
        this.setPreferredSize(new Dimension(scaledSize, scaledSize));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.setBackground(Color.WHITE);
        this.setVisible(true);
    }
}

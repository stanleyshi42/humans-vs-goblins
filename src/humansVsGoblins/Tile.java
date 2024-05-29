package humansVsGoblins;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class Tile extends Box {

    public Tile(int scaledSize) {
        super(BoxLayout.X_AXIS);
        this.setSize(new Dimension(scaledSize, scaledSize));
        this.setPreferredSize(new Dimension(scaledSize, scaledSize));
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        this.setBackground(Color.WHITE);
        this.setVisible(true);
    }
}

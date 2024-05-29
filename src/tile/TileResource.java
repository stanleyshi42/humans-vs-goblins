package tile;

import humansVsGoblins.GamePanel;
import javax.swing.*;
import java.awt.*;


public class TileResource {
    
    GamePanel gp;
    Tile[] tile;
    ImageIcon icon;
    String[] resourceContainer = {
            "Resources/grass.png",
            "Resources/tree.png",
            "Resources/chest.png",
            "Resources/lockedChest.png"
    };

    final int UNITSIZE = 48;
    
    public TileResource(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];

        getTileImage();
    }

    private void getTileImage() {

        for (int i = 0; i < resourceContainer.length; i++){
            tile[i] = new Tile();
            icon = new ImageIcon(resourceContainer[i]);
            tile[i].image = icon.getImage().getScaledInstance(icon.getIconWidth()*3,
                    icon.getIconHeight()*3, java.awt.Image.SCALE_SMOOTH);
        }

    }

    public void draw(Graphics2D g2){
        int x;
        int y = 0;
        for (int i = 0; i < gp.maxScreenRows; i++){
            x = UNITSIZE * i;
            for (int j = 0; j < gp.maxScreenColumns;j++){
                y = UNITSIZE * j;
                g2.drawImage(tile[0].image, x,y,gp.scaledTileSize, gp.scaledTileSize, null);
            }
        }
        g2.drawImage(tile[1].image, UNITSIZE*2,0,gp.scaledTileSize, gp.scaledTileSize, null);
        g2.drawImage(tile[2].image, UNITSIZE*3,0,gp.scaledTileSize, gp.scaledTileSize, null);
        g2.drawImage(tile[3].image, UNITSIZE*4,0,gp.scaledTileSize, gp.scaledTileSize, null);
        g2.drawImage(tile[3].image, UNITSIZE*5,0,gp.scaledTileSize, gp.scaledTileSize, null);

    }
}

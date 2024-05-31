package tile;

import humansVsGoblins.GamePanel;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;



public class TileResource {
    
    GamePanel gp;
    Tile[] tile;
    ImageIcon icon;
    String[] resourceContainer = {
            "Resources/grass.png", // tile[0]
            "Resources/tree.png", // tile[1]
            "Resources/chest.png", // tile[2]
            "Resources/lockedChest.png", // tile[3]
            "Resources/movement.png" // tile[4]
    };
    int mapTile[][];

    final int UNITSIZE = 48;
    
    public TileResource(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTile = new int[gp.maxScreenColumns][gp.maxScreenRows];
        createTileImage();
        loadMap();
    }


    public Tile[] getTile(){
        return tile;
    }

    public int[][] getMap(){
        return mapTile;
    }

    private void createTileImage() {

        for (int i = 0; i < resourceContainer.length; i++){
            tile[i] = new Tile();
            tile[i].name = resourceContainer[i].split("/")[1].split("\\.")[0];
            icon = new ImageIcon(resourceContainer[i]);
            tile[i].image = icon.getImage().getScaledInstance(icon.getIconWidth()*3,
                    icon.getIconHeight()*3, java.awt.Image.SCALE_SMOOTH);
        }

    }

    public void loadMap() {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Resources/maps/map02.txt")));
            for (int i = 0; i < gp.maxScreenRows; i++){
                String line = br.readLine();
                String numbers[] = line.split(" ");
                for (int j = 0; j < gp.maxScreenColumns; j++){
                    mapTile[i][j] = Integer.parseInt(numbers[j]);
                }
            }
        } catch (Exception e){
            System.out.println("Map Loaded Incorrectly");
        }
    }

    public void draw(Graphics2D g2){
        int x;
        int y;
        for (int i = 0; i < gp.maxScreenRows; i++){
            y = UNITSIZE * i;
            for (int j = 0; j < gp.maxScreenColumns;j++){
                x = UNITSIZE * j;
                int tileNumber = mapTile[i][j];
                g2.drawImage(tile[0].image, x,y,gp.scaledTileSize, gp.scaledTileSize, null);
                if (tileNumber != 0){
                    g2.drawImage(tile[tileNumber].image, x,y,gp.scaledTileSize, gp.scaledTileSize, null);
                }

            }
        }


    }
}

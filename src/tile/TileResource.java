package tile;

import humansVsGoblins.GamePanel;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.io.FileInputStream;
import java.util.ArrayList;


public class TileResource {
    GamePanel gp;
    Tile[] tile;
    ImageIcon icon;
    // list of paths to the background sprites
    String[] resourceContainer = {
            "Resources/grass.png", // tile[0]
            "Resources/tree.png", // tile[1]
    };
    int mapTile[][];
    // Stores chest location and type
    ArrayList<String> chest;
    // Stores goblin location and type
    ArrayList<String> goblin;

    final int UNITSIZE = 48;

    // Generate background sprites and load map
    public TileResource(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[2];
        chest = new ArrayList<>();
        goblin = new ArrayList<>();
        mapTile = new int[gp.maxScreenColumns][gp.maxScreenRows];
        createTileImage();
        loadRandomMap();
    }

    // Getters methods
    public ArrayList<String> getChest(){
        return chest;
    }
    public ArrayList<String> getGoblin(){
        return goblin;
    }
    public int[][] getMap(){
        return mapTile;
    }

    // Loads background sprites into tile
    private void createTileImage() {
        for (int i = 0; i < resourceContainer.length; i++){
            tile[i] = new Tile();
            tile[i].name = resourceContainer[i].split("/")[1].split("\\.")[0];
            icon = new ImageIcon(resourceContainer[i]);
            tile[i].image = icon.getImage().getScaledInstance(icon.getIconWidth()*3,
                    icon.getIconHeight()*3, java.awt.Image.SCALE_SMOOTH);
        }
    }

    // Loads map from the pool of maps
    public void loadRandomMap() {
        String[] mapFiles = {"Resources/maps/map01.txt", "Resources/maps/map02.txt" };
        Random random = new Random();
        int randomIndex = random.nextInt(mapFiles.length);
        loadMap(mapFiles[randomIndex]);
    }

    public void loadMap(String mapFilePath) {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(mapFilePath)));
            for (int i = 0; i < gp.maxScreenRows; i++){
                String line = br.readLine();
                String numbers[] = line.split(" ");
                for (int j = 0; j < gp.maxScreenColumns; j++){
                    // Get the data to know where tree are for collisions
                    mapTile[i][j] = Integer.parseInt(numbers[j]);
                    // add to the arraylist to where chest and goblins are
                    switch (mapTile[i][j]){
                        case 2:
                            // Normal Chest
                            chest.add(j + " " + i + " False");
                            break;
                        case 3:
                            // Lock Chest
                            chest.add(j + " " + i + " True");
                            break;
                        case 4:
                            // Goblin
                            goblin.add(j + " " + i + " 0");
                            break;
                        case 5:
                            // Red Cap
                            goblin.add(j + " " + i + " 1");
                            break;
                        case 6:
                            // Orc
                            goblin.add(j + " " + i + " 2");
                            break;
                        case 7:
                            // Hoarder
                            goblin.add(j + " " + i + " 3");
                            break;
                    }
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
                // Always draw the grass on every tiles
                g2.drawImage(tile[0].image, x,y,gp.scaledTileSize, gp.scaledTileSize, null);
                if (tileNumber == 1){
                    // draws tree where it is needed
                    g2.drawImage(tile[tileNumber].image, x,y,gp.scaledTileSize, gp.scaledTileSize, null);
                }

            }
        }


    }
}

package humansVsGoblins;

import entities.Player;
import tile.TileResource;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class PossibleMove {
    private final int UNITSIZE = 48;
    private final Player player;
    private final TileResource tile;

    private ArrayList<String> moves;
    private final int[][] mapTile;
    ImageIcon icon;
    Image image;

    public PossibleMove(Player player, TileResource tile) {
        this.player = player;
        this.tile = tile;
        this.mapTile = tile.getMap();
    }


    public void createMoves() {
        moves = new ArrayList<>();
        int[][] directions = {
                {0, -1},  // up
                {0, 1},   // down
                {1, 0},   // right
                {-1, 0}   // left
        };
        boolean[] canMove = {true, true, true, true};

        for (int i = 1; i <= player.speed; i++) {
            for (int d = 0; d < directions.length; d++) {
                if (canMove[d]) {
                    int newX = player.getGX() + directions[d][0] * i;
                    int newY = player.getGY() + directions[d][1] * i;

                    if (isValidMove(newX, newY)) {
                        moves.add(newY + " " + newX);
                    } else {
                        canMove[d] = false;
                    }
                }
            }
        }
    }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < 20 && y >= 0 && y < 20 && mapTile[y][x] != 1;
    }

    public ArrayList<String> getMoves(){
        return this.moves;
    }

    public void draw(Graphics2D g2){
        for (String ele : moves){
            g2.drawImage(tile.getTile()[4].image, UNITSIZE*Integer.parseInt(ele.split(" ")[1]),
                    UNITSIZE*Integer.parseInt(ele.split(" ")[0]),UNITSIZE, UNITSIZE, null);
        }

    }
}

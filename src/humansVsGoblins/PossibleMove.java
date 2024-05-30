package humansVsGoblins;

import entities.Player;
import tile.TileResource;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class PossibleMove {
    private final int UNITSIZE = 48;
    private final JPanel gamePanel;
    private final Player player;
    private final TileResource tile;

    private ArrayList<String> moves;
    ImageIcon icon;
    Image image;

    public PossibleMove(JPanel gamePanel, Player player, TileResource tile) {
        this.gamePanel = gamePanel;
        this.player = player;
        this.tile = tile;
    }


    public void createMoves() {
        moves = new ArrayList<>();
        int i = 1;
        boolean up = true;
        boolean down = true;
        boolean right = true;
        boolean left = true;
        while (i <= player.speed){
            if (up && player.getGY() != 0){
                if (!tile.getTiles().contains(STR."\{player.getGY()-i} \{player.getGX()}")){
                    moves.add(STR."\{player.getGY()-i} \{player.getGX()}");
                } else {
                    up = false;
                }
            }
            if (down && player.getGY() != 19){
                if (!tile.getTiles().contains(STR."\{player.getGY()+i} \{player.getGX()}")){
                    moves.add(STR."\{player.getGY()+i} \{player.getGX()}");
                } else {
                    down = false;
                }
            }
            if (right && player.getGX() != 19){
                if (!tile.getTiles().contains(STR."\{player.getGY()} \{player.getGX()+i}")){
                    moves.add(STR."\{player.getGY()} \{player.getGX()+i}");
                } else {
                    right = false;
                }
            }
            if (left && player.getGX() != 0){
                if (!tile.getTiles().contains(STR."\{player.getGY()} \{player.getGX()-i}")){
                    moves.add(STR."\{player.getGY()} \{player.getGX()-i}");
                } else {
                    left = false;
                }
            }
            i++;
        }

    }
    public ArrayList<String> getMoves(){
        return this.moves;
    }

    public void draw(Graphics2D g2){
        icon =  new ImageIcon("Resources/chest.png");
        image = icon.getImage().getScaledInstance(icon.getIconWidth()*3,
                icon.getIconHeight()*3, java.awt.Image.SCALE_SMOOTH);
        for (String ele : moves){
            g2.drawImage(image, UNITSIZE*Integer.parseInt(ele.split(" ")[1]),
                    UNITSIZE*Integer.parseInt(ele.split(" ")[0]),UNITSIZE, UNITSIZE, null);
        }

    }
}

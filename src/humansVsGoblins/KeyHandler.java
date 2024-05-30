package humansVsGoblins;

import entities.Player;
import tile.TileResource;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class KeyHandler extends MouseAdapter {
    private JPanel selectedPanel;
    private final JPanel gamePanel;
    private final Player player;
    private final TileResource tile;
    private final PossibleMove moves;

    public KeyHandler(JPanel gamePanel, Player player,TileResource tile, PossibleMove moves) {
        this.gamePanel = gamePanel;
        this.player = player;
        this.tile = tile;
        this.moves = moves;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JPanel panel = (JPanel) gamePanel.getComponentAt(e.getPoint());
        if (panel == null || panel == gamePanel) {
            return;
        }
        selectedPanel = panel;
        int coordX = Integer.parseInt(selectedPanel.getName().split(" ")[1]);
        int coordY = Integer.parseInt(selectedPanel.getName().split(" ")[0]);
        int speed = player.speed;
        moves.createMoves();

        if (moves.getMoves().contains(selectedPanel.getName())){
            boolean canMove = true;
            for (String ele : tile.getTilesCollision()){
                if(ele.equals(selectedPanel.getName())){
                    canMove = false;
                }
            }
            if (canMove){
                player.setGridY(coordY);
                player.setGridX(coordX);
                moves.createMoves();

            }
        }
    }
}

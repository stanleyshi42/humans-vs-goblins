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

    public KeyHandler(JPanel gamePanel, Player player,TileResource tile) {
        this.gamePanel = gamePanel;
        this.player = player;
        this.tile = tile;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JPanel panel = (JPanel) gamePanel.getComponentAt(e.getPoint());
        if (panel == null || panel == gamePanel) {
            return;
        }
        selectedPanel = panel;
        boolean canMove = true;
        for (String ele : tile.getTiles()){
            System.out.println(ele);
            System.out.println(selectedPanel.getName());
            if(ele.equals(selectedPanel.getName())){
                canMove = false;
            }
        }
        if (canMove){
            player.setGridY(Integer.parseInt(selectedPanel.getName().split(" ")[0]));
            player.setGridX(Integer.parseInt(selectedPanel.getName().split(" ")[1]));
        }
    }
}

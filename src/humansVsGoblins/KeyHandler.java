package humansVsGoblins;

import entities.Player;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class KeyHandler extends MouseAdapter {
    private JPanel selectedPanel;
    private final JPanel gamePanel;
    private final Player player;

    public KeyHandler(JPanel gamePanel, Player player) {
        this.gamePanel = gamePanel;
        this.player = player;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JPanel panel = (JPanel) gamePanel.getComponentAt(e.getPoint());
        if (panel == null || panel == gamePanel) {
            return;
        }
        selectedPanel = panel;
        player.setY(Integer.parseInt(selectedPanel.getName().split(" ")[0]) * 48);
        player.setX(Integer.parseInt(selectedPanel.getName().split(" ")[1]) * 48);
    }
}

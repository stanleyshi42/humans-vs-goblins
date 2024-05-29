package humansVsGoblins;

public class Launcher {
    
    public static void main(String[] args) {
        GameFrame window = new GameFrame();
        GamePanel panel = new GamePanel();
        InventoryPanel invPanel = new InventoryPanel(panel.getPlayer());
        window.add(panel);

        window.pack();
    }
    
}

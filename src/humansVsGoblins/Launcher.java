package humansVsGoblins;

public class Launcher {
    
    public static void main(String[] args) {
        GameFrame window = new GameFrame();
        GamePanel panel = new GamePanel();
        window.add(panel);

        window.pack();
    }
}

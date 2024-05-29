package humansVsGoblins;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MovementListener implements KeyListener {

	public static char key;

	public MovementListener() {

	}

	private static void movePlayer(char key) {
		// TODO make player move
		System.out.println("Player pressed: " + key);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		MovementListener.key = e.getKeyChar();
		key = Character.toLowerCase(key);

		switch (key) {
		case 'w':
		case 'a':
		case 's':
		case 'd':
			movePlayer(key);
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}

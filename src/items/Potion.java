package items;

import javax.swing.ImageIcon;

public class Potion {
	ImageIcon sprite;
	String name;
	int healing;

	public Potion(Item item) {
		switch (item) {
		case SMALL_POTION:
			this.name = "Small Potion";
			this.healing = 3;
			// TODO set sprite
			break;
		case MEDIUM_POTION:
			this.name = "Medium Potion";
			this.healing = 5;
			break;
		case LARGE_POTION:
			this.name = "Large Potion";
			this.healing = 8;
			break;
		default:
			break;
		}
	}
}

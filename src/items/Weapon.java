package items;

import javax.swing.ImageIcon;

public class Weapon {
	ImageIcon sprite;
	String name;
	int attack;

	public Weapon(Item item) {
		switch (item) {
		case WOODEN_SWORD:
			this.name = "Wooden Sword";
			this.attack = 2;
			break;
		case IRON_SWORD:
			this.name = "Iron Sword";
			this.attack = 4;
			break;
		case DIAMOND_SWORD:
			this.name = "Diamond Sword";
			this.attack = 6;
			break;
		default:
			break;
		}
	}
}

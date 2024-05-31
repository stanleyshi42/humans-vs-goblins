package items;

import javax.swing.ImageIcon;

public class Potion extends Item {
	public int healing;

	public Potion(ItemID itemId) {
		super("", itemId, null);

		switch (itemId) {
		case SMALL_POTION:
			this.setName("Small Potion");
			this.healing = 3;
			ImageIcon smallPot = new ImageIcon("Resources/lesserHealingPot.png");
			this.setSprite(smallPot);
			break;
		case MEDIUM_POTION:
			this.setName("Medium Potion");
			this.healing = 5;
			ImageIcon mediumPot = new ImageIcon("Resources/mediumHealingPot-1.png");
			this.setSprite(mediumPot);
			break;
		case LARGE_POTION:
			this.setName("Large Potion");
			this.healing = 8;
			ImageIcon largePot = new ImageIcon("Resources/greaterHealingPot.png");
			this.setSprite(largePot);
			break;
		default:
			break;
		}
	}
}

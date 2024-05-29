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
			// TODO set sprite
			ImageIcon smallPot = new ImageIcon("Resources/lesserHealingPot.png");
			this.setSprite(smallPot);
			break;
		case MEDIUM_POTION:
			this.setName("Medium Potion");
			this.healing = 5;
			//ImageIcon mediumPot = new ImageIcon("Resources/healingPot.png");
			//this.setSprite(mediumPot);
			this.setSprite(null);
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

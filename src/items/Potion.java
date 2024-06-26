package items;

import javax.swing.ImageIcon;

/*
 * Potion class that represents an Item object that can be used to heal
 * the player. Uses the ItemID enum to gather the specific sprites
 * and names for the healing potion that is requested. Specifically,
 * used to raise the player's hp stat.
 */

public class Potion extends Item {
	public int healing;	// Healing stat of the item. Used to heal player's HP one time.

	public Potion(ItemID itemId) {
		super("", itemId, null);

		switch (itemId) {
		
		// Small Potion
		// Healing: 3
		case SMALL_POTION:
			this.setName("Small Potion");
			this.healing = 3;
			ImageIcon smallPot = new ImageIcon("Resources/lesserHealingPot.png");
			this.setSprite1(smallPot);
			ImageIcon smallPot2 = new ImageIcon("Resources/lesserHealingPot2.png");
			this.setSprite2(smallPot2);
			break;
		// Medium Potion
		// Healing: 5
		case MEDIUM_POTION:
			this.setName("Medium Potion");
			this.healing = 5;
			ImageIcon mediumPot = new ImageIcon("Resources/mediumHealingPot-1.png");
			this.setSprite1(mediumPot);
			ImageIcon mediumPot2 = new ImageIcon("Resources/mediumHealingPot2.png");
			this.setSprite2(mediumPot2);
			break;
		// Large Potion
		// Healing: 8
		case LARGE_POTION:
			this.setName("Large Potion");
			this.healing = 8;
			ImageIcon largePot = new ImageIcon("Resources/greaterHealingPot.png");
			this.setSprite1(largePot);
			ImageIcon largePot2 = new ImageIcon("Resources/greaterHealingPot2.png");
			this.setSprite2(largePot2);
			break;
		default:
			break;
		}
	}
}

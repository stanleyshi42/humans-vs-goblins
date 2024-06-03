package items;

import javax.swing.ImageIcon;

/*
 * Armor class that represents an Item object that blocks damage in the game.
 * Uses the ItemID enum to gather the specific sprites and names for the 
 * Armor that is requested. Specifically, boosts the player's defense stat
 * while equipped.
 */

public class Armor extends Item {
	public int defense;

	public Armor(ItemID itemId) {
		super("", itemId, null);

		switch (itemId) {
		
		// Leather Armor
		// Defense: 2
		case LEATHER_ARMOR:
			this.name = "Leather Armor";
			this.defense = 2;
			ImageIcon leatherArmor = new ImageIcon("Resources/leatherArmor.png");
			ImageIcon leatherArmor2 = new ImageIcon("Resources/leatherArmor2.png");
			this.setSprite1(leatherArmor);
			this.setSprite2(leatherArmor2);
			break;
		// Iron Armor
		// Defense: 4
		case IRON_ARMOR:
			this.name = "Iron Armor";
			this.defense = 4;
			ImageIcon ironArmor = new ImageIcon("Resources/ironArmor.png");
			ImageIcon ironArmor2 = new ImageIcon("Resources/ironArmor2.png");
			this.setSprite1(ironArmor);
			this.setSprite2(ironArmor2);
			break;
		// Diamond Armor
		// Defense: 6
		case DIAMOND_ARMOR:
			this.name = "Diamond Armor";
			this.defense = 6;
			ImageIcon diamondArmor = new ImageIcon("Resources/diamondArmor.png");
			ImageIcon diamondArmor2 = new ImageIcon("Resources/diamondArmor2.png");
			this.setSprite1(diamondArmor);
			this.setSprite2(diamondArmor2);
			break;
		default:
			break;
		}
	}
}

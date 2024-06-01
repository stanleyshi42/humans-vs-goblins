package items;

import javax.swing.ImageIcon;

public class Armor extends Item {
	public int defense;

	public Armor(ItemID itemId) {
		super("", itemId, null);

		switch (itemId) {
		case LEATHER_ARMOR:
			this.name = "Leather Armor";
			this.defense = 2;
			ImageIcon leatherArmor = new ImageIcon("Resources/leatherArmor.png");
			this.setSprite1(leatherArmor);
			ImageIcon leatherArmor2 = new ImageIcon("Resources/leatherArmor2.png");
			this.setSprite2(leatherArmor2);
			break;
		case IRON_ARMOR:
			this.name = "Iron Armor";
			this.defense = 4;
			ImageIcon ironArmor = new ImageIcon("Resources/ironArmor.png");
			this.setSprite1(ironArmor);
			ImageIcon ironArmor2 = new ImageIcon("Resources/ironArmor2.png");
			this.setSprite2(ironArmor2);
			break;
		case DIAMOND_ARMOR:
			this.name = "Diamond Armor";
			this.defense = 6;
			ImageIcon diamondArmor = new ImageIcon("Resources/diamondArmor.png");
			this.setSprite1(diamondArmor);
			ImageIcon diamondArmor2 = new ImageIcon("Resources/diamondArmor2.png");
			this.setSprite2(diamondArmor2);
			break;
		default:
			break;
		}
	}
}

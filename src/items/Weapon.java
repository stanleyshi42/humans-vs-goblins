package items;

import javax.swing.ImageIcon;

public class Weapon extends Item {
	public int attack;

	public Weapon(ItemID itemId) {
		super("", itemId, null);

		switch (itemId) {
		case WOODEN_SWORD:
			this.setName("Wooden Sword");
			this.attack = 2;
			ImageIcon woodSword = new ImageIcon("Resources/woodenSword.png");
			this.setSprite(woodSword);
			break;
		case IRON_SWORD:
			this.setName("Iron Sword");
			this.attack = 4;
			ImageIcon ironSword = new ImageIcon("Resources/ironSword.png");
			this.setSprite(ironSword);
			break;
		case DIAMOND_SWORD:
			this.setName("Diamond Sword");
			this.attack = 6;
			ImageIcon diamondSword = new ImageIcon("Resources/diamondSword.png");
			this.setSprite(diamondSword);
			break;
		case BROAD_SWORD:
			this.setName("Broad Sword");
			this.attack = 3;
			ImageIcon broadSword = new ImageIcon("Resources/broadSword.png");
			this.setSprite(broadSword);
		default:
			break;
		}
	}
}

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
			this.setSprite1(woodSword);
			ImageIcon woodSword2 = new ImageIcon("Resources/woodenSword2.png");
			this.setSprite2(woodSword2);
			break;
		case IRON_SWORD:
			this.setName("Iron Sword");
			this.attack = 4;
			ImageIcon ironSword = new ImageIcon("Resources/ironSword.png");
			this.setSprite1(ironSword);
			ImageIcon ironSword2 = new ImageIcon("Resources/ironSword2.png");
			this.setSprite2(ironSword2);
			break;
		case DIAMOND_SWORD:
			this.setName("Diamond Sword");
			this.attack = 6;
			ImageIcon diamondSword = new ImageIcon("Resources/diamondSword.png");
			this.setSprite1(diamondSword);
			ImageIcon diamondSword2 = new ImageIcon("Resources/diamondSword2.png");
			this.setSprite2(diamondSword2);
			break;
		case BROAD_SWORD:
			this.setName("Broad Sword");
			this.attack = 3;
			ImageIcon broadSword = new ImageIcon("Resources/broadSword.png");
			this.setSprite1(broadSword);
			ImageIcon broadSword2 = new ImageIcon("Resources/broadSword2.png");
			this.setSprite2(broadSword2);
		default:
			break;
		}
	}
}

package items;

import javax.swing.ImageIcon;

public class Weapon extends Item {
	public int attack;

	public Weapon(ItemID itemId) {
		super("", null);
		this.id = itemId;
		switch (itemId) {
		case WOODEN_SWORD:
			this.setName("Wooden Sword");
			this.attack = 2;
			// TODO set sprite
			ImageIcon woodSword = new ImageIcon("Resources/woodenSword.png");
			this.setSprite(woodSword);
			break;
		case IRON_SWORD:
			this.setName("Iron Sword");
			this.attack = 4;
			//ImageIcon ironSword = new ImageIcon("Resources/ironSword.png");
			//this.setSprite(ironSword);
			this.setSprite(null);
			break;
		case DIAMOND_SWORD:
			this.setName("Diamond Sword");
			this.attack = 6;
			//ImageIcon diamondSword = new ImageIcon("Resources/diamondSword.png");
			//this.setSprite(diamondSword);
			this.setSprite(null);
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

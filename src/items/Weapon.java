package items;

public class Weapon extends Item {
	public int attack;

	public Weapon(ItemID item) {
		switch (item) {
		case WOODEN_SWORD:
			this.name = "Wooden Sword";
			this.attack = 2;
			// TODO set sprite
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

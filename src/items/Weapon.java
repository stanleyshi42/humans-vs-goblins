package items;

public class Weapon extends Item {
	int attack;

	public Weapon(ItemID item) {
		super("", null);
		switch (item) {
		case WOODEN_SWORD:
			this.setName("Wooden Sword");
			this.attack = 2;
			// TODO set sprite
			this.setSprite(null);
			break;
		case IRON_SWORD:
			this.setName("Iron Sword");
			this.attack = 4;
			this.setSprite(null);
			break;
		case DIAMOND_SWORD:
			this.setName("Diamond Sword");
			this.attack = 6;
			this.setSprite(null);
			break;
		default:
			break;
		}
	}
}

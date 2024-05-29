package items;

public class Potion extends Item {
	public int healing;

	public Potion(ItemID itemId) {
		super("", itemId, null);
		switch (itemId) {
		case SMALL_POTION:
			this.setName("Small Potion");
			this.healing = 3;
			// TODO set sprite
			this.setSprite(null);
			break;
		case MEDIUM_POTION:
			this.setName("Medium Potion");
			this.healing = 5;
			this.setSprite(null);
			break;
		case LARGE_POTION:
			this.setName("Large Potion");
			this.healing = 8;
			this.setSprite(null);
			break;
		default:
			break;
		}
	}
}

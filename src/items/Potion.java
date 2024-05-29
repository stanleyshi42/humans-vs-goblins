package items;

public class Potion extends Item {
	int healing;

	public Potion(ItemID item) {
		switch (item) {
		case SMALL_POTION:
			this.name = "Small Potion";
			this.healing = 3;
			// TODO set sprite
			break;
		case MEDIUM_POTION:
			this.name = "Medium Potion";
			this.healing = 5;
			break;
		case LARGE_POTION:
			this.name = "Large Potion";
			this.healing = 8;
			break;
		default:
			break;
		}
	}
}

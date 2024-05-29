package items;

public class Armor extends Item {
	public int defense;

	public Armor(ItemID item) {
		super("", null);
		switch (item) {
		case LEATHER_ARMOR:
			this.name = "Leather Armor";
			this.defense = 2;
			// TODO set sprite
			break;
		case IRON_ARMOR:
			this.name = "Iron Armor";
			this.defense = 4;
			break;
		case DIAMOND_ARMOR:
			this.name = "Diamond Armor";
			this.defense = 6;
			break;
		default:
			break;
		}
	}
}

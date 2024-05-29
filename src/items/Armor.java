package items;

public class Armor extends Item {
	public int defense;

	public Armor(ItemID itemId) {
		super("", itemId, null);
		this.id = itemId;
		switch (itemId) {
		case LEATHER_ARMOR:
			this.name = "Leather Armor";
			this.defense = 2;
			// TODO set sprite
			//ImageIcon leatherArmor = new ImageIcon("Resources/leatherArmor.png");
			//this.setSprite(leatherArmor);
			this.setSprite(null);
			break;
		case IRON_ARMOR:
			this.name = "Iron Armor";
			this.defense = 4;
			//ImageIcon ironArmor = new ImageIcon("Resources/ironArmor.png");
			//this.setSprite(ironArmor);
			this.setSprite(null);
			break;
		case DIAMOND_ARMOR:
			this.name = "Diamond Armor";
			this.defense = 6;
			//ImageIcon diamondArmor = new ImageIcon("Resources/diamondArmor.png");
			//this.setSprite(diamondArmor);
			this.setSprite(null);
			break;
		default:
			break;
		}
	}
}

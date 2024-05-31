package items;

import javax.swing.ImageIcon;

public class Key extends Item {
	public Key(ItemID itemId) {
		super("Key", itemId, null);

		switch(itemId) {
			case KEY:
				ImageIcon key = new ImageIcon("Resources/key.png");
				this.setSprite(key);
				break;
			default:
				break;
		}
	}
}

package items;

import javax.swing.ImageIcon;

public class Key extends Item {
	public Key(ItemID itemId) {
		super("Key", itemId, null);

		switch(itemId) {
			case KEY:
				ImageIcon key = new ImageIcon("Resources/key.png");
				this.setSprite1(key);
				ImageIcon key2 = new ImageIcon("Resources/key2.png");
				this.setSprite2(key2);
				break;
			default:
				break;
		}
	}
}

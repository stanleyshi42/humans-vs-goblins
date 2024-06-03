package items;

import javax.swing.ImageIcon;

/*
 * Key class that represents an Item object that can be used to unlock
 * locked chests. Uses the ItemID enum to gather the specific sprites
 * and names for the key item that is requested.
 */
public class Key extends Item {
	public Key(ItemID itemId) {
		super("Key", itemId, null);

		switch(itemId) {
			// Simple Key
			case KEY:
				ImageIcon key = new ImageIcon("Resources/key.png");
				this.setSprite1(key);
				ImageIcon key2 = new ImageIcon("Resources/key2.png");
				this.setSprite2(key2);
				break;
			// Room for more keys
			default:
				break;
		}
	}
}

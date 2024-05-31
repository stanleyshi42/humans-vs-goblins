package items;

import javax.swing.ImageIcon;

public class Key extends Item {
	public Key(ItemID itemId) {
		super("Key", itemId, null);
		this.setSprite(new ImageIcon("Resources/key.png"));

	}

}

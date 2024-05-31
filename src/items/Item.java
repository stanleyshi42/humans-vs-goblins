package items;

import javax.swing.ImageIcon;

public class Item {
	protected String name;
	protected ItemID id;
	protected ImageIcon sprite;

	public Item() {
		this("", null, null);
	}

	public Item(String n, ItemID id, ImageIcon spr) {
		this.name = n;
		this.id = id;
		this.sprite = spr;
	}

	public String getName() {
		return name;
	}

	public ImageIcon getSprite() {
		return sprite;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSprite(ImageIcon sprite) {
		this.sprite = sprite;
	}

	public ItemID getId() {
		return id;
	}

	public void setId(ItemID id) {
		this.id = id;
	}

}
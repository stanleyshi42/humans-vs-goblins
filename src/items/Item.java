package items;

import javax.swing.ImageIcon;

public class Item {
	protected String name;
	protected ItemID id;
	protected ImageIcon sprite;

	public Item() {
		
	}

	public Item(String n, ImageIcon spr) {
		this.name = n;
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
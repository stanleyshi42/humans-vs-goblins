package items;

import javax.swing.ImageIcon;

public class Item {
	protected String name;
	protected ItemID id;
	protected ImageIcon sprite1;
	protected ImageIcon sprite2;

	public Item() {
		this("", null, null);
	}

	public Item(String n, ItemID id, ImageIcon spr) {
		this.name = n;
		this.id = id;
		this.sprite1 = spr;
	}

	public String getName() {
		return name;
	}

	public ImageIcon getSprite1() {
		return sprite1;
	}
	public ImageIcon getSprite2() {
		return sprite2;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSprite1(ImageIcon sprite) {
		this.sprite1 = sprite;
	}

	public void setSprite2(ImageIcon sprite2) {
		this.sprite2 = sprite2;
	}

	public ItemID getId() {
		return id;
	}

	public void setId(ItemID id) {
		this.id = id;
	}

}
package items;

import javax.swing.ImageIcon;

public class Item {
	private String name;
	private ImageIcon sprite;
	
	public Item(String n, ImageIcon spr) {
		name = n;
		sprite = spr;
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

}
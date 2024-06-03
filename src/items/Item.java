package items;

import javax.swing.ImageIcon;

/*
 * Item class represents an object in the game that can be used
 * to either interact with objects, entities throughout the game,
 * or boost the player's stats. Broad class used to create a
 * template for further definition such as Weapon, Armor, Potion,
 * and a Key. 
 */

public class Item {
	protected String name;			// The name of the Item Ex: "Broad Sword"
	protected ItemID id;			// The itemID of the Item Ex: ItemID.BROAD_SWORD
	protected ImageIcon sprite1;	// The first sprite for the animation.
	protected ImageIcon sprite2;	// The second sprite for the animation.

	public Item() {
		this("", null, null);
	}

	public Item(String n, ItemID id, ImageIcon spr) {
		this.name = n;
		this.id = id;
		this.sprite1 = spr;
	}

	// Get name of Item
	public String getName() {
		return name;
	}

	// Get first sprite of Item
	public ImageIcon getSprite1() {
		return sprite1;
	}

	// Get second sprite of Item
	public ImageIcon getSprite2() {
		return sprite2;
	}

	// Set the name of the Item
	public void setName(String name) {
		this.name = name;
	}

	// Set the first sprite of the Item
	public void setSprite1(ImageIcon sprite) {
		this.sprite1 = sprite;
	}

	// Set the second sprite of the Item
	public void setSprite2(ImageIcon sprite2) {
		this.sprite2 = sprite2;
	}

	// Get the itemID of the item.
	public ItemID getId() {
		return id;
	}

	// Set the itemID of the item.
	public void setId(ItemID id) {
		this.id = id;
	}

}
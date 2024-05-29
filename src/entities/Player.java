package entities;

import java.util.HashMap;
import items.*;

public class Player extends Entity {
	int hp, attack, defense, speed;
	HashMap<String, Item> equipment = new HashMap<>(); // Equipped items, such as weapons, armor, etc.
	HashMap<Item, Integer> inventory = new HashMap<>();

	// Starting stats
	public Player() {
		this.x = 0;
		this.y = 0;
		this.hp = 10;
		this.attack = 5;
		this.defense = 5;
		this.speed = 1;
		this.equipment.put("weapon", new Weapon(ItemID.WOODEN_SWORD));
		this.equipment.put("armor", new Weapon(ItemID.LEATHER_ARMOR));
		this.inventory.put(new Potion(ItemID.SMALL_POTION), 3);
	}

	public void equipItem(Item item) {
		if (item instanceof Weapon) {
			equipWeapon(item);
		}
		else if (item instanceof Armor) {
			equipArmor(item);
		}
	}

	private void equipWeapon(Item weapon) {
		inventory.put(equipment.get("weapon"), 1); // Move the equipped item to inventory
		equipment.put("weapon", weapon); // Equip the new item

	}

	private void equipArmor(Item armor) {
		inventory.put(equipment.get("armor"), 1); // Move the equipped item to inventory
		equipment.put("armor", armor); // Equip the new item

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x += x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y += y;
	}

}

package entities;

import java.util.HashMap;
import items.*;

public class Player extends Entity {
	public int hp, attack, defense, speed;
	public HashMap<String, Item> equipment = new HashMap<>(); // Equipped items, such as weapons, armor, etc.
	public HashMap<Item, Integer> inventory = new HashMap<>();

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

	public void usePotion(Potion potion) {
		if (inventory.containsKey(potion)) {
			// TODO
		}
	}

	// Equips an item from inventory and adjust player's stats
	public void equipItem(Item item) {
		if (item instanceof Weapon) {
			equipWeapon((Weapon) item);
		} else if (item instanceof Armor) {
			equipArmor((Armor) item);
		}
	}

	private void equipWeapon(Weapon newWeapon) {
		Weapon currentWeapon = (Weapon) equipment.get("weapon");

		// Move the equipped item to inventory
		inventory.put(currentWeapon, 1);
		attack -= currentWeapon.attack;

		// Equip the new item
		equipment.put("weapon", newWeapon);
		inventory.remove(newWeapon);
		attack += newWeapon.attack;

	}

	private void equipArmor(Armor newArmor) {
		Armor currentArmor = (Armor) equipment.get("armor");

		// Move the equipped item to inventory
		inventory.put(currentArmor, 1);
		defense -= currentArmor.defense;

		// Equip the new item
		equipment.put("armor", newArmor);
		inventory.remove(newArmor);
		defense += newArmor.defense;
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

	@Override
	public String toString() {
		return "Player [hp=" + hp + ", attack=" + attack + ", defense=" + defense + ", speed=" + speed + ", equipment="
				+ equipment.toString() + ", inventory=" + inventory + "]";
	}

	// TODO delete this debugging stuff
	public static void main(String[] args) {
		Player player = new Player();
		System.out.println(player);

		Weapon weapon = new Weapon(ItemID.IRON_SWORD);

		player.inventory.put(weapon, 1);
		player.equipItem(weapon);
		System.out.println(player);

	}

}

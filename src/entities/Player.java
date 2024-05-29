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
		this.defense = 2;
		this.speed = 1;
		this.equipment.put("weapon", new Weapon(ItemID.WOODEN_SWORD));
		this.equipment.put("armor", new Weapon(ItemID.LEATHER_ARMOR));
		this.inventory.put(new Potion(ItemID.SMALL_POTION), 3);
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

	public void takeDamage(int damage) {
        damage -= defense;
        if (damage < 0) {
            damage = 0; 
        }

        hp -= damage;

        if (hp < 0) {
            hp = 0;
        }
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

	public int getAttack() {
		return attack;
	}

	public int getHp() {
		return hp;
	}

	public int getDefense() {
		return defense;
	}



}

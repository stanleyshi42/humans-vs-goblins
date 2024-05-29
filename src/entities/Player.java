package entities;

import java.util.ArrayList;
import java.util.HashMap;
import items.*;

public class Player extends Entity {
	public int maxHp, curHp, attack, defense, speed;
	public HashMap<String, Item> equipment = new HashMap<>(); // Equipped items, such as weapons, armor, etc.
	public ArrayList<Item> inventory = new ArrayList<>();

	// Starting stats
	public Player() {
		this.x = 0;
		this.y = 0;
		this.maxHp = 10;
		this.curHp = this.maxHp;
		this.attack = 5;
		this.defense = 2;
		this.speed = 1;
		this.equipment.put("weapon", new Weapon(ItemID.BROAD_SWORD));
		this.equipment.put("armor", new Armor(ItemID.LEATHER_ARMOR));
		this.inventory.add(new Potion(ItemID.SMALL_POTION));
		this.inventory.add(new Potion(ItemID.SMALL_POTION));
		this.inventory.add(new Potion(ItemID.SMALL_POTION));
	}

	// Use a potion to restore HP
	public void usePotion(Potion potion) {
		if (inventory.contains(potion)) {
			restoreHp(potion.healing);
			inventory.remove(potion);
		}
	}

	public void usePotion(ItemID potionId) {
		Potion potion = new Potion(potionId);

		for (Item i : inventory) {
			if (i.getId() == potionId) {
				restoreHp(potion.healing);
				inventory.remove(i);
				return;
			}
		}
	}

	public void restoreHp(int healing) {
		curHp += healing;
		if (curHp > maxHp)
			curHp = maxHp;
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
		inventory.add(currentWeapon);
		attack -= currentWeapon.attack;

		// Equip the new item
		equipment.put("weapon", newWeapon);
		inventory.remove(newWeapon);
		attack += newWeapon.attack;

	}

	private void equipArmor(Armor newArmor) {
		Armor currentArmor = (Armor) equipment.get("armor");

		// Move the equipped item to inventory
		inventory.add(currentArmor);
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

		curHp -= damage;

		if (curHp < 0) {
			curHp = 0;
		}
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public int getSpeed() {
		return speed;
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

	public int getHp() {
		return curHp;
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public HashMap<String, Item> getEquipment() {
		return equipment;
	}

	@Override
	public String toString() {
		return "Player [maxHp=" + maxHp + ", curHp=" + curHp + ", attack=" + attack + ", defense=" + defense
				+ ", speed=" + speed + ", equipment=" + equipment + ", inventory=" + inventory + "]";
	}

}

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
		this.defense = 5;
		this.speed = 1;
		this.equipment.put("weapon", new Weapon(ItemID.WOODEN_SWORD));
		this.equipment.put("armor", new Armor(ItemID.LEATHER_ARMOR));
		this.inventory.add(new Potion(ItemID.SMALL_POTION));
	}

	// Use a potion to restore HP
	public void usePotion(Potion potion) {
		// TODO
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Player [maxHp=" + maxHp + ", curHp=" + curHp + ", attack=" + attack + ", defense=" + defense
				+ ", speed=" + speed + ", equipment=" + equipment + ", inventory=" + inventory + "]";
	}

	// TODO delete this debugging stuff
	public static void main(String[] args) {
		Player player = new Player();
		System.out.println(player);

		Weapon weapon = new Weapon(ItemID.IRON_SWORD);
		player.inventory.add(weapon);
		Armor armor = new Armor(ItemID.DIAMOND_ARMOR);
		player.inventory.add(armor);
		player.curHp -= 5;
		player.usePotion(null);

		player.equipItem(weapon);
		player.equipItem(armor);
		System.out.println(player);

		System.out.print("Inventory: ");
		for (Item i : player.inventory) {
			System.out.print(i.getName() + ", ");
		}

	}

}

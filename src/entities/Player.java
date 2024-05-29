package entities;

import java.util.HashMap;
import items.*;

public class Player extends Entity {
	int hp, attack, defense, speed;
	Item equipment;
	HashMap<Item, Integer> inventory = new HashMap<>();

	// Starting stats
	public Player() {
		this.x = 0;
		this.y = 0;
		this.hp = 10;
		this.attack = 5;
		this.defense = 5;
		this.speed = 1;
		this.inventory.put(new Weapon(ItemID.WOODEN_SWORD), 1);
		this.inventory.put(new Potion(ItemID.SMALL_POTION), 2);
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

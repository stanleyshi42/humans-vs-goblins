package entities;

import java.util.ArrayList;
import items.Item;

public class Player extends Entity {
	int hp, attack, defense;
	ArrayList<Item> inventory;

	public Player() {
		this.x = 0;
		this.y = 0;
		this.hp = 10;
		this.attack = 5;
		this.defense = 5;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}
}

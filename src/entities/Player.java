package entities;

public class Player extends Entity {
	int x, y;
	int hp, attack, defense;

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

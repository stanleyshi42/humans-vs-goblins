package entities;

public class Goblin extends Entity {
	int hp, attack, defense;

	public Goblin(int x, int y, int hp, int attack, int defense) {
		this.x = x;
		this.y = y;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
	}

	@Override
	public void move() {
		// TODO AI movement logic

	}

}

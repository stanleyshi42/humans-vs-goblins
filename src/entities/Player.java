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
	public void move() {
		// TODO Auto-generated method stub

	}
}

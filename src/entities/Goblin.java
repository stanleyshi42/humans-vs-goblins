package entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Goblin extends Entity {
	int hp, attack, defense;
	ImageIcon icon;
	Image image;

	public Goblin(int x, int y) {
		this(x, y, 7, 4, 2);

	}

	public Goblin(int x, int y, int hp, int attack, int defense) {
		this.x = x;
		this.y = y;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
		this.icon = new ImageIcon("Resources/goblin.png");
		this.image = icon.getImage().getScaledInstance(icon.getIconWidth() * 3, icon.getIconHeight() * 3,
				java.awt.Image.SCALE_SMOOTH);
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

	public void draw(Graphics2D g2) {
		g2.drawImage(image, this.x * 48, this.y * 48, 48, 48, null);

	}

	// TODO implement movement algorithm
	// Randomly move
	public void move(int[][] mapTile) {
		Random random = new Random();
		Boolean moved = false;

		switch (random.nextInt(1)) {
		case 0:
			moveRight(mapTile);
			break;
		case 1:
			moveLeft(mapTile);

			break;
		case 2:
			moveUp(mapTile);

			break;
		case 3:
			moveDown(mapTile);
			break;
		}

	}

	// Check if a tile is occupied by an obstacle (trees, chests, etc.)
	public boolean isOccupied(int[][] mapTile, int xCoord, int yCoord) {
		if (mapTile[xCoord][yCoord] >= 1) {
			return true;
		}

		return false;
	}

	public void moveRight(int[][] mapTile) {
		int newX = x + 1;
		if (x < 19 && !isOccupied(mapTile, newX, this.y)) {
			x = newX;
		}

	}

	public void moveLeft(int[][] mapTile) {
		int newX = this.x - 1;
		if (this.x > 0 && !isOccupied(mapTile, newX, this.y)) {
			this.x -= 1;
		}

	}

	public void moveUp(int[][] mapTile) {
		int newY = this.y - 1;
		if (this.y > 0 && !isOccupied(mapTile, this.x, newY)) {
			this.y -= 1;
		}
	}

	public void moveDown(int[][] mapTile) {
		int newY = this.y + 1;
		if (this.y < 19 && !isOccupied(mapTile, this.x, newY)) {
			this.y += 1;
		}
	}

	public int getDefense() {
		return defense;
	}

	public int getAttack() {
		return attack;
	}

	public int getHp() {
		return hp;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	@Override
	public String toString() {
		return "Goblin [hp=" + hp + ", attack=" + attack + ", defense=" + defense + ", icon=" + icon + ", image="
				+ image + ", x=" + x + ", y=" + y + "]";
	}

}

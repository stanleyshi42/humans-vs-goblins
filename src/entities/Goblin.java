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
	public void move() {
		Random random = new Random();
		switch (random.nextInt(5)) {

		case 0:
			moveRight();
			break;
		case 1:
			moveLeft();
			break;
		case 2:
			moveUp();
			break;
		case 3:
			moveDown();
			break;
		// Don't move
		case 4:
			break;
		}
	}

	// TODO check if next space is occupied by something else
	public boolean checkCollision() {
		System.out.println(this);

		return false;
	}

	public void moveRight() {
		if (x < 19)
			this.x += 1;
	}

	public void moveLeft() {
		if (x > 0)
			this.x -= 1;
	}

	public void moveUp() {
		if (y > 0)
			this.y -= 1;
	}

	public void moveDown() {
		if (y < 19)
			this.y += 1;
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

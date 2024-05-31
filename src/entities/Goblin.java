package entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;

import humansVsGoblins.GamePanel;

public class Goblin extends Entity {
	int hp, attack, defense;
	ImageIcon icon1;
	ImageIcon icon2;
	Image image1;
	Image image2;
	int spriteNum;

	String[] goblin1 = {"Resources/goblin.png", "Resources/goblin-2.png"};
	String[] goblin2 = {"Resources/redcap-1.png", "Resources/redcap-2.png"};
	String[] goblin3 = {"Resources/orc-1.png", "Resources/orc-2.png"};
	String[] goblin4 = {"Resources/hoarder-1.png", "Resources/hoarder-2.png"};
	ArrayList<String[]> goblinList = new ArrayList<>(Arrays.asList(goblin1, goblin2,goblin3, goblin4));

	public Goblin(int x, int y) {
		this(x, y, 7, 4, 2,0);

	}

	public Goblin(int x, int y, int hp, int attack, int defense, int goblinNum) {
		this.x = x;
		this.y = y;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
		this.icon1 = new ImageIcon(goblinList.get(goblinNum)[0]);
		this.icon2 = new ImageIcon(goblinList.get(goblinNum)[1]);
		this.image1 = icon1.getImage().getScaledInstance(icon1.getIconWidth() * 3, icon1.getIconHeight() * 3,
				java.awt.Image.SCALE_SMOOTH);
		this.image2 = icon2.getImage().getScaledInstance(icon2.getIconWidth() * 3, icon2.getIconHeight() * 3,
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
		if (spriteNum == 1){
			System.out.println(this.x);
			g2.drawImage(image1, this.x*48,this.y*48,48, 48, null);
		} else {
			g2.drawImage(image2, this.x*48,this.y*48,48, 48, null);
		}

	}

	public void update(int spriteNum){
		this.spriteNum = spriteNum;
	}

	// TODO implement advanced movement algorithm
	// Randomly move, if the chosen move is invalid, try again
	public void move(int[][] mapTile) {
		Random random = new Random();
		Boolean moved = false;


		while (!moved) {
			switch (random.nextInt(6)) {
			case 0:
				moved = moveRight(mapTile);
				break;
			case 1:
				moved = moveLeft(mapTile);
				break;
			case 2:
				moved = moveUp(mapTile);
				break;
			case 3:
				moved = moveDown(mapTile);
				break;
			// Don't move
			case 4, 5:
				moved = true;
				break;
			}
		}
	}

	// Check if a tile is occupied by something (goblins, trees, chests, etc.)
	public boolean isOccupied(int[][] mapTile, int xCoord, int yCoord) {
		if (mapTile[yCoord][xCoord] >= 1) {
			return true;
		}
		for (Goblin g : GamePanel.goblins) {
			if (g.getX() == xCoord && g.getY() == yCoord)
				return true;
		}

		return false;
	}

	public boolean moveRight(int[][] mapTile) {
		int newX = x + 1;
		if (x < 19 && !isOccupied(mapTile, newX, this.y)) {
			x = newX;
			return true;
		}
		return false;
	}

	public boolean moveLeft(int[][] mapTile) {
		int newX = this.x - 1;
		if (this.x > 0 && !isOccupied(mapTile, newX, this.y)) {
			this.x -= 1;
			return true;
		}
		return false;
	}

	public boolean moveUp(int[][] mapTile) {
		int newY = this.y - 1;
		if (this.y > 0 && !isOccupied(mapTile, this.x, newY)) {
			this.y -= 1;
			return true;
		}
		return false;
	}

	public boolean moveDown(int[][] mapTile) {
		int newY = this.y + 1;
		if (this.y < 19 && !isOccupied(mapTile, this.x, newY)) {
			this.y += 1;
			return true;
		}
		return false;
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
		return "Goblin [hp=" + hp + ", attack=" + attack + ", defense=" + defense + ", icon=" + icon1 + ", image="
				+ image1 + ", x=" + x + ", y=" + y + "]";
	}

}

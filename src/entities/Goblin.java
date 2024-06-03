package entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;

import humansVsGoblins.GamePanel;

/*
 * Goblin class that represents a goblin entity on the map.
 * A goblin contains their own sprites based on the type of
 * goblin. A goblin has their own set of stats based on the
 * goblin type. Goblin stats include hp, attack, and defense.
 * Goblins have their own movement system, currently its based
 * on random decision.
 */

public class Goblin extends Entity {
	
	// Goblin stats.
	int hp, attack, defense;

	// Sprite related
	ImageIcon icon1;	// Sprite 1
	Image image1;

	ImageIcon icon2;	// Sprite 2
	Image image2;

	int spriteNum;		// Sprite counter used to change sprites during runtime.

	String[] goblin1 = {"Resources/goblin.png", "Resources/goblin-2.png"};		// First type of goblin sprite
	String[] goblin2 = {"Resources/redcap-1.png", "Resources/redcap-2.png"};	// Second type of goblin sprite
	String[] goblin3 = {"Resources/orc-1.png", "Resources/orc-2.png"};			// Third type of goblin sprite
	String[] goblin4 = {"Resources/hoarder-1.png", "Resources/hoarder-2.png"};	// Fourth type of goblin sprite

	// List of goblin types
	ArrayList<String[]> goblinList = new ArrayList<>(Arrays.asList(goblin1, goblin2,goblin3, goblin4));

	public Goblin(int x, int y) {
		this(x, y, 7, 4, 2,0);

	}

	public Goblin(int x, int y, int hp, int attack, int defense, int goblinNum) {
		
		// Initialize starting position
		this.x = x;
		this.y = y;

		// Initialize stats
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;

		// Initialize first and second sprites
		this.icon1 = new ImageIcon(goblinList.get(goblinNum)[0]);
		this.icon2 = new ImageIcon(goblinList.get(goblinNum)[1]);
		this.image1 = icon1.getImage().getScaledInstance(icon1.getIconWidth() * 3, icon1.getIconHeight() * 3,
				java.awt.Image.SCALE_SMOOTH);
		this.image2 = icon2.getImage().getScaledInstance(icon2.getIconWidth() * 3, icon2.getIconHeight() * 3,
				java.awt.Image.SCALE_SMOOTH);
	}

	// Reduce hp by variable "damage" amount.
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

	// Drawing the goblin on the GamePanel.
	public void draw(Graphics2D g2) {
		if (spriteNum == 1){
			g2.drawImage(image1, this.x*48,this.y*48,48, 48, null);
		} else {
			g2.drawImage(image2, this.x*48,this.y*48,48, 48, null);
		}

	}

	// Updating the sprite counter for animation.
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

	// Attempt to move the goblin to the right.
	public boolean moveRight(int[][] mapTile) {
		int newX = x + 1;
		if (x < 19 && !isOccupied(mapTile, newX, this.y)) {
			x = newX;
			return true;
		}
		return false;
	}

	// Attempt to move the goblin to the left.
	public boolean moveLeft(int[][] mapTile) {
		int newX = this.x - 1;
		if (this.x > 0 && !isOccupied(mapTile, newX, this.y)) {
			this.x -= 1;
			return true;
		}
		return false;
	}

	// Attempt to move the goblin to the up.
	public boolean moveUp(int[][] mapTile) {
		int newY = this.y - 1;
		if (this.y > 0 && !isOccupied(mapTile, this.x, newY)) {
			this.y -= 1;
			return true;
		}
		return false;
	}

	// Attempt to move the goblin to the down.
	public boolean moveDown(int[][] mapTile) {
		int newY = this.y + 1;
		if (this.y < 19 && !isOccupied(mapTile, this.x, newY)) {
			this.y += 1;
			return true;
		}
		return false;
	}

	// Get the defense stat.
	public int getDefense() {
		return defense;
	}

	// Get the attack stat.
	public int getAttack() {
		return attack;
	}

	// Get the hp stat.
	public int getHp() {
		return hp;
	}

	// Get the X coordinate.
	public int getX() {
		return this.x;
	}

	// Get the Y coordinate.
	public int getY() {
		return this.y;
	}

	// Get the first goblin sprite.
	public ImageIcon getGoblinImg(){
		return this.icon1;
	}

	@Override
	public String toString() {
		return "Goblin [hp=" + hp + ", attack=" + attack + ", defense=" + defense + ", icon=" + icon1 + ", image="
				+ image1 + ", x=" + x + ", y=" + y + "]";
	}

}

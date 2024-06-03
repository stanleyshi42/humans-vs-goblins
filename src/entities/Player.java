package entities;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import items.*;

import javax.swing.*;

/*
 * Player class represents the user and the actions that the user
 * can take throughout the game such as using potions, using keys,
 * equipping armor, and equipping weapons. Contains the stats of
 * the player such as hp, attack, defense and also contains the
 * sprites used to represent the player on the game world.
 */

public class Player extends Entity {

	// Player Stats.
	private final int maxHp,speed;
    private int curHp, defense, attack;
	private final HashMap<String, Item> equipment = new HashMap<>(); 	// Equipped items, such as weapons, armor, etc.
	private final ArrayList<Item> inventory = new ArrayList<>();		// Player's list of items in their inventory


	// Sprite related.
	ImageIcon icon;		// Sprite 1.
	Image image;
	ImageIcon icon2;	// Sprite 2.
	Image image2;

	int spriteNum;		// Sprite counter used to change sprites during runtime.

	// Starting stats.
	public Player() {

		// Initializing starting position.
		this.setGridX(1);
		this.setGridY(1);

		// Initializing stats and starting inventory/equipment.
		this.maxHp = 16;
		this.curHp = this.maxHp;
		this.attack = 6;
		this.defense = 2;
		this.speed = 1;
		this.equipment.put("weapon", new Weapon(ItemID.WOODEN_SWORD));
		this.equipment.put("armor", new Armor(ItemID.LEATHER_ARMOR));
		this.inventory.add(new Potion(ItemID.SMALL_POTION));
		this.inventory.add(new Potion(ItemID.SMALL_POTION));
		this.inventory.add(new Potion(ItemID.SMALL_POTION));
		this.inventory.add(new Key(ItemID.KEY));
		
		// Initializing sprites.
		this.icon = new ImageIcon("Resources/player-1.png");
		this.image = icon.getImage().getScaledInstance(icon.getIconWidth()*3,
				icon.getIconHeight()*3, java.awt.Image.SCALE_SMOOTH);
		this.icon2 = new ImageIcon("Resources/player-2.png");
		this.image2 = icon2.getImage().getScaledInstance(icon.getIconWidth()*3,
				icon2.getIconHeight()*3, java.awt.Image.SCALE_SMOOTH);
	}

	// Drawing the player on the GamePanel.
	public void draw(Graphics2D g2){

		if (spriteNum == 1){
			g2.drawImage(image, this.x,this.y,48, 48, null);
		} else {
			g2.drawImage(image2, this.x,this.y,48, 48, null);
		}

	}

	// Updating the sprite counter for animation.
	public void update(int spriteNum){
		this.spriteNum = spriteNum;
	}

	// Use a potion to restore the player's HP
	// Receives the potion object directly.
	public void usePotion(Potion potion) {
		if (curHp >= maxHp)
			return;

		if (inventory.contains(potion)) {
			restoreHp(potion.healing);
			inventory.remove(potion);
		}
	}

	// Use a potion to restore the player's HP
	// Receives the id of the potion.
	public void usePotion(ItemID potionId) {
		if (curHp >= maxHp)
			return;

		Potion potion = new Potion(potionId);

		for (Item i : inventory) {
			if (i.getId() == potionId) {
				restoreHp(potion.healing);
				inventory.remove(i);
				return;
			}
		}
	}

	// Restore the player's hp using
	// the int 'healing'.
	public void restoreHp(int healing) {
		curHp += healing;
		if (curHp > maxHp)
			curHp = maxHp;
	}

	// Equip an item from inventory and adjust player's stats accordingly.
	public void equipItem(Item item) {
		if (item instanceof Weapon) {
			equipWeapon((Weapon) item);
		} else if (item instanceof Armor) {
			equipArmor((Armor) item);
		}
	}

	// Equip a new weapon and return the previously
	// equipped weapon to the player's inventory.
	private void equipWeapon(Weapon newWeapon) {
		Weapon currentWeapon = (Weapon) equipment.get("weapon");

		int index = inventory.indexOf(newWeapon);
		// Move the equipped item to inventory
		attack -= currentWeapon.attack;

		// Equip the new item
		equipment.put("weapon", newWeapon);
		inventory.remove(index);
		attack += newWeapon.attack;
		inventory.add(index, currentWeapon);
	}

	// Equip a new armor piece and return the previously
	// equipped armor to the player's inventory.
	private void equipArmor(Armor newArmor) {
		Armor currentArmor = (Armor) equipment.get("armor");

		// Move the equipped item to inventory
		int index = inventory.indexOf(newArmor);
		defense -= currentArmor.defense;

		// Equip the new item
		equipment.put("armor", newArmor);
		inventory.remove(index);
		inventory.add(index, currentArmor);
		defense += newArmor.defense;
	}

	// Reduce hp by variable "damage" amount.
	public void takeDamage(int damage) {
		damage -= defense;
		if (damage < 0) {
			damage = 0;
		}

		curHp -= damage;

		if (curHp < 0) {
			curHp = 0;
		}
	}

	// Get MaxHP.
	public int getMaxHp() {
		return maxHp;
	}

	// Get Current HP
	public int getHp() {
		return curHp;
	}

	// Set Current HP.
	public void setHp(int hp){
		this.curHp = hp;
	}

	// Get attack stat.
	public int getAttack() {
		return attack;
	}

	// Get defense stat.
	public int getDefense() {
		return defense;
	}

	// Get speed stat.
	public int getSpeed() {
		return speed;
	}

	// Get GridX position.
	public int getGX() {
		return gX;
	}

	// Get GridY position.
	public int getGY() {
		return gY;
	}

	// Set GridX position.
	public void setGridX(int x){
		this.gX = x;
		this.x = x * 48;
	}

	// Set GridY position.
	public void setGridY(int y){
		this.gY = y;
		this.y = y * 48;
	}

	// Get the list of Items in player inventory.
	public ArrayList<Item> getInventory() {
		return inventory;
	}

	// Add a new item to player inventory.
	public void addItemToInventory(Item it) {
		inventory.add(it);
	}

	// Get items currently equipped by player.
	public HashMap<String, Item> getEquipment() {
		return equipment;
	}

	// hasKeyInInventory() will return if the player
	// has a Key object in their inventory. Used
	// to unlock chests.
	public boolean hasKeyInInventory() {
		for(Item it: inventory) {
			if(it instanceof Key) return true;
		}
		return false;
	}

	// useKey() will remove a singular key object
	// from the player's inventory if the player
	// has one. Used to unlock chests in the
	// CheckChest() method in GamePanel.java 
	public void useKey() {
		if(hasKeyInInventory()) {
			for(Item it: inventory) {
				if(it instanceof Key) {
					inventory.remove(it);
					return;
				}
			}
		}
	}

	@Override
	public String toString() {
		return "Player [maxHp=" + maxHp + ", curHp=" + curHp + ", attack=" + attack + ", defense=" + defense
				+ ", speed=" + speed + ", x=" + x + ", y=" + y + ", gX=" + gX + ", gY=" + gY + "]";
	}

}

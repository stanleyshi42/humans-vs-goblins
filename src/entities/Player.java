package entities;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import items.*;

import javax.swing.*;

public class Player extends Entity {
	private final int maxHp,speed;
    private int curHp,defense,attack;
	private final HashMap<String, Item> equipment = new HashMap<>(); // Equipped items, such as weapons, armor, etc.
	private final ArrayList<Item> inventory = new ArrayList<>();


	ImageIcon icon;
	ImageIcon icon2;
	Image image;
	Image image2;

	int spriteNum;

	// Starting stats
	public Player() {
		this.setGridX(1);
		this.setGridY(1);

		this.maxHp = 16;
		this.curHp = this.maxHp;
		this.attack = 5;
		this.defense = 2;
		this.speed = 1;
		this.equipment.put("weapon", new Weapon(ItemID.WOODEN_SWORD));
		this.equipment.put("armor", new Armor(ItemID.LEATHER_ARMOR));
		this.inventory.add(new Potion(ItemID.SMALL_POTION));
		this.inventory.add(new Potion(ItemID.SMALL_POTION));
		this.inventory.add(new Potion(ItemID.SMALL_POTION));
		this.inventory.add(new Key(ItemID.KEY));
		
		this.icon = new ImageIcon("Resources/player-1.png");
		this.image = icon.getImage().getScaledInstance(icon.getIconWidth()*3,
				icon.getIconHeight()*3, java.awt.Image.SCALE_SMOOTH);
		this.icon2 = new ImageIcon("Resources/player-2.png");
		this.image2 = icon2.getImage().getScaledInstance(icon.getIconWidth()*3,
				icon2.getIconHeight()*3, java.awt.Image.SCALE_SMOOTH);
	}

	public void draw(Graphics2D g2){

		if (spriteNum == 1){
			g2.drawImage(image, this.x,this.y,48, 48, null);
		} else {
			g2.drawImage(image2, this.x,this.y,48, 48, null);
		}

	}

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

	// Equips an item from inventory and adjust player's stats
	public void equipItem(Item item) {
		if (item instanceof Weapon) {
			equipWeapon((Weapon) item);
		} else if (item instanceof Armor) {
			equipArmor((Armor) item);
		}
	}

	// Equip a new weapon and return the previously equipped
	// weapon to the player's inventory.
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
		System.out.println(attack);
	}

	// Equip a new armor and return the previously equipped
	// armor to the player's inventory.
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

	public int getMaxHp() {
		return maxHp;
	}
	public void setHp(int hp){
		this.curHp = hp;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public int getSpeed() {
		return speed;
	}

	public int getGX() {
		return gX;
	}

	public void setGridX(int x){
		this.gX = x;
		this.x = x * 48;
	}
	public void setGridY(int y){
		this.gY = y;
		this.y = y * 48;
	}

	public int getGY() {
		return gY;
	}

	public int getHp() {
		return curHp;
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public void addItemToInventory(Item it) {
		inventory.add(it);
	}

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

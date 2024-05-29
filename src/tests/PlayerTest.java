package tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import entities.Player;
import items.*;

public class PlayerTest {

	@Test
	public void testEquipWeapon() {
		Player player = new Player();
		Weapon weapon = new Weapon(ItemID.IRON_SWORD);

		player.inventory.add(weapon);
		player.equipItem(weapon);

		assertEquals(player.equipment.get("weapon").getId(), ItemID.IRON_SWORD);
		assertFalse(player.inventory.contains(weapon));
		
	}
}

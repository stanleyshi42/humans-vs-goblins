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

		player.getInventory().add(weapon);
		player.equipItem(weapon);

		assertEquals(player.getEquipment().get("weapon").getId(), ItemID.IRON_SWORD);
		assertFalse(player.getInventory().contains(weapon));

	}
	@Test
	public void testEquipArmor() {
		Player player = new Player();
		Armor armor = new Armor(ItemID.DIAMOND_ARMOR);

		player.getInventory().add(armor);
		player.equipItem(armor);

		assertEquals(player.getEquipment().get("armor").getId(), ItemID.DIAMOND_ARMOR);
		assertFalse(player.getInventory().contains(armor));

	}

	@Test
	public void testUsePotion() {
		Player player = new Player();
		player.setHp(0);

		for (Item i : player.getInventory()) {
			if (i.getId() == ItemID.SMALL_POTION) {
				player.usePotion((Potion) i);
				assertEquals(3, player.getHp());
				assertFalse(player.getInventory().contains(i));
				break;
			}
		}

		player.getInventory().add(new Potion(ItemID.MEDIUM_POTION));
		player.setHp(0);
		player.usePotion(ItemID.MEDIUM_POTION);

		assertEquals(5, player.getHp());

	}

	@Test
	public void testSpawnPlayer() {
		Player player = new Player();
		assertEquals(1, player.getGX());
		assertEquals(1, player.getGY());
		assertEquals(16, player.getHp());
	}
	@Test
	public void testDamage() {
		Player player = new Player();
		player.takeDamage(15);
		// 16 - (15 - 2 (armor)) = 3
		assertEquals(3, player.getHp());
		player.takeDamage(15);
		// 3 - (15 - 2) = -10 ~> 0
		assertEquals(0, player.getHp());
	}

	@Test
	public void testHasKey() {
		Player player = new Player();
		assertTrue(player.hasKeyInInventory());
		player.useKey();
		assertFalse(player.hasKeyInInventory());
		player.useKey();
		assertFalse(player.hasKeyInInventory());
	}
}

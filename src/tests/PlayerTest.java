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

	@Test
	public void testUsePotion() {
		Player player = new Player();
		player.curHp = 0;

		for (Item i : player.inventory) {
			if (i.getId() == ItemID.SMALL_POTION) {
				player.usePotion((Potion) i);
				assertEquals(3, player.curHp);
				assertFalse(player.inventory.contains(i));
				break;
			}
		}

		player.inventory.add(new Potion(ItemID.MEDIUM_POTION));
		player.curHp = 0;
		player.usePotion(ItemID.MEDIUM_POTION);

		assertEquals(5, player.curHp);

	}
}

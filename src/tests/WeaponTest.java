package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import items.ItemID;
import items.Weapon;

public class WeaponTest {

	@Test
	public void testWeaponConstructor() {
		Weapon weapon = new Weapon(ItemID.IRON_SWORD);

		assertEquals(ItemID.IRON_SWORD, weapon.getId());
		assertEquals("Iron Sword", weapon.getName());
		assertEquals(4, weapon.attack);

	}
}

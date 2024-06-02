package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import items.*;
import org.junit.jupiter.api.Test;

public class ItemTest {

	@Test
	public void testWeaponConstructor() {
		Weapon weapon = new Weapon(ItemID.IRON_SWORD);

		assertEquals(ItemID.IRON_SWORD, weapon.getId());
		assertEquals("Iron Sword", weapon.getName());
		assertEquals(4, weapon.attack);

	}
	@Test
	public void testArmorConstructor() {
		Armor armor = new Armor(ItemID.IRON_ARMOR);

		assertEquals(ItemID.IRON_ARMOR, armor.getId());
		assertEquals("Iron Armor", armor.getName());
		assertEquals(4, armor.defense);

	}
	@Test
	public void testKeyConstructor() {
		Key key = new Key(ItemID.KEY);

		assertEquals(ItemID.KEY, key.getId());
		assertEquals("Key", key.getName());

	}
	@Test
	public void testPotConstructor() {
		Potion pots = new Potion(ItemID.SMALL_POTION);
		assertEquals(ItemID.SMALL_POTION, pots.getId());
		assertEquals("Small Potion", pots.getName());
		pots = new Potion(ItemID.LARGE_POTION);
		assertEquals(ItemID.LARGE_POTION, pots.getId());
		assertEquals("Large Potion", pots.getName());

	}
}

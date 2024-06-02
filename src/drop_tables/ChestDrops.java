package drop_tables;

import java.util.ArrayList;
import java.util.Random;

import items.*;

public class ChestDrops {
	public static Item roll() {
		Random random = new Random();
		int rand = random.nextInt(100) + 1; // 1-100
		if (isBetween(rand, 1, 30))
			return null; // Rolled no drop
		if (isBetween(rand, 31, 35))
			return new Weapon(ItemID.DIAMOND_SWORD);
		if (isBetween(rand, 35, 38))
			return new Weapon(ItemID.GREAT_SWORD);
		if (isBetween(rand, 39, 44))
			return new Armor(ItemID.DIAMOND_ARMOR);
		if (isBetween(rand, 45, 60))
			return new Potion(ItemID.MEDIUM_POTION);
		if (isBetween(rand, 61, 75))
			return new Potion(ItemID.LARGE_POTION);
		if (isBetween(rand, 76, 90))
			return new Key(ItemID.KEY);
		if (isBetween(rand, 91, 100))
			return new Potion(ItemID.SMALL_POTION);
		return null;

	}

	// Return multiple item drops
	public static ArrayList<Item> rollMultiple(int x) {
		ArrayList<Item> items = new ArrayList<>();

		for (int i = 0; i < x; i++) {
			items.add(roll());
		}
		return items;
	}

	private static boolean isBetween(int x, int lower, int upper) {
		return lower <= x && x <= upper;
	}
}

package drop_tables;

import java.util.ArrayList;
import java.util.Random;

import items.*;

public class ChestDrops {
	//TODO
	public static Item roll() {
		Random random = new Random();
		int rand = random.nextInt(100) + 1; // 1-100
		System.out.println(rand);
		if (isBetween(rand, 1, 20))
			return null; // Rolled no drop
		if (isBetween(rand, 21, 30))
			return new Weapon(ItemID.DIAMOND_SWORD);
		if (isBetween(rand, 31, 33))
			return new Weapon(ItemID.GREAT_SWORD);
		if (isBetween(rand, 34, 50))
			return new Armor(ItemID.DIAMOND_ARMOR);
		if (isBetween(rand, 51, 65))
			return new Potion(ItemID.MEDIUM_POTION);
		if (isBetween(rand, 66, 75))
			return new Potion(ItemID.LARGE_POTION);
		if (isBetween(rand, 76, 85))
			return new Key(ItemID.KEY);
		if (isBetween(rand, 86, 100))
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

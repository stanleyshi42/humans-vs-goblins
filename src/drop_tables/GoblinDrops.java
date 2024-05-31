package drop_tables;

import java.util.ArrayList;
import java.util.Random;

import items.*;

// Mostly static class
public final class GoblinDrops {

	private GoblinDrops() {

	}

	// Return an item drop
	public static Item roll() {
		Random random = new Random();
		int rand = random.nextInt(100) + 1; // 1-100

		if (isBetween(rand, 1, 20))
			return null; // Rolled no drop
		if (isBetween(rand, 21, 40)) 
			return new Weapon(ItemID.IRON_SWORD);
		if (isBetween(rand, 41, 50))
			return new Weapon(ItemID.BROAD_SWORD);
		if (isBetween(rand, 51, 90))
			return new Potion(ItemID.SMALL_POTION);
		if (isBetween(rand, 91, 100))
			return new Key(ItemID.KEY);
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

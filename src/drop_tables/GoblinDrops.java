package drop_tables;

import java.util.ArrayList;
import java.util.Random;

import items.*;

public final class GoblinDrops {

	public static ArrayList<ItemID> drops;

	// Static block
	// Called once when the class is loaded
	static {
		drops.add(ItemID.IRON_SWORD);
		drops.add(ItemID.BROAD_SWORD);
		drops.add(ItemID.IRON_ARMOR);
		drops.add(ItemID.SMALL_POTION);
		drops.add(ItemID.MEDIUM_POTION);
		drops.add(ItemID.KEY);
	}

	private GoblinDrops() {

	}

	// Return an item drop
	public ItemID roll() {
		Random random = new Random();
		int rand = random.nextInt(100) + 1; // 1-100

		if (isBetween(rand, 1, 20))
			return null; // Rolled no drop
		if (isBetween(rand, 21, 40))
			return ItemID.IRON_SWORD;
		if (isBetween(rand, 41, 50))
			return ItemID.BROAD_SWORD;
		if (isBetween(rand, 51, 90))
			return ItemID.SMALL_POTION;
		if (isBetween(rand, 91, 100))
			return ItemID.KEY;
		return null;

	}

	// Return multiple item drops
	public ArrayList<ItemID> rollMultiple(int x) {
		ArrayList<ItemID> items = new ArrayList<>();

		for (int i = 0; i < x; i++) {
			items.add(roll());
		}
		return items;
	}

	private static boolean isBetween(int x, int lower, int upper) {
		return lower <= x && x <= upper;
	}

}

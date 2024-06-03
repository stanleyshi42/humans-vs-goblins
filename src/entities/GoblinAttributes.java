package entities;

import java.util.HashMap;
import java.util.Map;
// Class holds the attributes of goblin based on the type
public class GoblinAttributes {
    private static final Map<Integer, int[]> goblinAttributes = new HashMap<>();

    static {
        // Goblin (Weak)
        goblinAttributes.put(0, new int[]{8, 5, 1}); // type 0: hp 8, atk 5, def 1
        // Red Cap Goblin (Stronger)
        goblinAttributes.put(1, new int[]{10, 8, 2}); // type 1: hp 10, atk 8, def 2
        // Orcs ( Tanky and Boss Type )
        goblinAttributes.put(2, new int[]{35, 9, 5});  // type 2: hp 35, atk 9, def 5
        // Hoarders (Week and Tanky )
        goblinAttributes.put(3, new int[]{48, 4, 4});  // type 3: hp 48, atk 4, def 4

    }

    public static int[] getAttributes(int type) {
        return goblinAttributes.getOrDefault(type, new int[]{0, 0}); // default to attack 0, hp 0
    }
}

package entities;

import java.util.HashMap;
import java.util.Map;
// Class holds the attributes of goblin based on the type
public class GoblinAttributes {
    private static final Map<Integer, int[]> goblinAttributes = new HashMap<>();

    static {
        // Goblin (Weak)
        goblinAttributes.put(0, new int[]{7,5,1}); // type 0: hp 10, atk 5, def 1
        // Red Cap Goblin (Stronger)
        goblinAttributes.put(1, new int[]{10, 7,2}); // type 1: hp 10, atk 7, def 2
        // Orcs ( Tanky and Boss Type )
        goblinAttributes.put(2, new int[]{30, 12,6});  // type 2: hp 30, atk 12, def 6
        // Hoarders (Week and Tanky )
        goblinAttributes.put(3, new int[]{50, 3,5});  // type 3: hp 50, atk 3, def 5

    }

    public static int[] getAttributes(int type) {
        return goblinAttributes.getOrDefault(type, new int[]{0, 0}); // default to attack 0, hp 0
    }
}

package entities;

import java.util.HashMap;
import java.util.Map;

public class GoblinAttributes {
    private static final Map<Integer, int[]> goblinAttributes = new HashMap<>();

    static {
        goblinAttributes.put(0, new int[]{7,5,1}); // type 0: hp 10, atk 5, def 1
        goblinAttributes.put(1, new int[]{10, 7,2}); // type 0: hp 10, atk , def
        goblinAttributes.put(2, new int[]{30, 12,6});  // type 0: hp 10, atk , def
        goblinAttributes.put(3, new int[]{50, 3,5});  // type 0: hp 10, atk , def

    }

    public static int[] getAttributes(int type) {
        return goblinAttributes.getOrDefault(type, new int[]{0, 0}); // default to attack 0, hp 0
    }
}


// Q6_DataStructureSearch.java
// Compare search in Array, HashSet and TreeSet

import java.util.*;

public class Q6_DataStructureSearch {
    public static boolean searchArray(int[] arr, int target) {
        for (int num : arr) if (num == target) return true;
        return false;
    }

    public static boolean searchHashSet(HashSet<Integer> set, int target) {
        return set.contains(target);
    }

    public static boolean searchTreeSet(TreeSet<Integer> set, int target) {
        return set.contains(target);
    }
}

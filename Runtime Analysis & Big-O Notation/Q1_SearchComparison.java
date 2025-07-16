
// Q1_SearchComparison.java
// Compare Linear Search vs Binary Search performance

import java.util.Arrays;

public class Q1_SearchComparison {
    public static boolean linearSearch(int[] arr, int target) {
        for (int num : arr) {
            if (num == target) return true;
        }
        return false;
    }

    public static boolean binarySearch(int[] arr, int target) {
        Arrays.sort(arr); // sort first
        return Arrays.binarySearch(arr, target) >= 0;
    }
}

// Counting Sort - Sort Student Ages
public class CountingSort_StudentAges {
    public static void countingSort(int[] arr, int maxVal) {
        int[] count = new int[maxVal + 1];
        for (int age : arr) count[age]++;

        int index = 0;
        for (int i = 0; i <= maxVal; i++) {
            while (count[i]-- > 0) {
                arr[index++] = i;
            }
        }
    }

    public static void main(String[] args) {
        int[] ages = {12, 15, 11, 10, 18, 14, 13, 16};
        countingSort(ages, 18);
        System.out.println("Sorted Student Ages:");
        for (int age : ages) {
            System.out.print(age + " ");
        }
    }
}
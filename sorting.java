import java.util.*;

public class sorting {
    // static int getMax(int[] arr) {
    //     int max = arr[0];
    //     for (int value : arr) {
    //         if (value > max) {
    //             max = value;
    //         }
    //     }
    //     return max;
    // }

    public static void main(String[] args){

        // Example number from the figure.
        int[] arr = {275, 87, 426, 61, 409, 170, 677, 503};
        int n = arr.length;
        int counter = 3;          //the number of digits in the largest value
        ArrayList<Integer>[] Array1 = new ArrayList[10];
        ArrayList<Integer>[] Array2 = new ArrayList[10];
            
        // 1. Initialize
        System.out.println("1. Initialization:");
        
        // Initialize each bucket
        for (int i = 0; i < 10; i++) {
            Array1[i] = new ArrayList<>();
            Array2[i] = new ArrayList<>();
        }
        System.out.println("Array1 and Array2 initialized with 10 buckets (0-9)");

        // 2. Initialize
        System.out.println("\n2. Iteration:");

        // First Pass - sort by the rightmost digit (ones place)
        // Distribute numbers into buckets based on the units digit
        for (int i = 0; i < n; i++) {
            int digit = arr[i] % 10;  // Get the units digit
            Array1[digit].add(arr[i]);
        }

        System.out.println("First Pass (by units digit):");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + ": ");
            for (int val : Array1[i]) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        // Second Pass - sort by the tens digit
        // Distribute numbers into buckets based on the tens digit
        for(int i = 0; i < 10; i++){
            for (int val : Array1[i]) {
                int tensDigit = (val / 10) % 10;  // Get the tens digit
                Array2[tensDigit].add(val);
            }
        }

        System.out.println("\nSecond Pass (by tens digit):");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + ": ");
            for (int val : Array2[i]) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        // Clear Array1 for the next pass
        for (int i = 0; i < 10; i++) {
            Array1[i].clear();
        }

        // Third Pass - sort by the hundreds digit
        // Distribute numbers into buckets based on the hundreds digit
        for(int i = 0; i < 10; i++){
            for (int val : Array2[i]) {
                int hundredsDigit = (val / 100) % 10;  // Get the hundreds digit
                Array1[hundredsDigit].add(val);
            }
        }

        System.out.println("\nThird Pass (by hundreds digit):");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + ": ");
            for (int val : Array1[i]) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        // 3. Reorder
        System.out.println("\n3. Reorder");
        int[] sortedArr = new int[n];
        int index = 0;
        
        for (int i = 0; i < 10; i++) {
            for (int val : Array1[i]) {
                sortedArr[index++] = val;
            }
        }
        
        System.out.println("Sorted array: " + Arrays.toString(sortedArr));
    }
}
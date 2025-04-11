import java.util.Arrays;
import java.util.Scanner;

public class RadixSortAlgorithm {
    public static void main(String[] args) {
        // Create Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Ask the user to input numbers
        System.out.println("Enter the numbers separated by spaces:");

        // Read the entire line as a string and split it into individual string elements
        String input = scanner.nextLine();

        // Split the input string by spaces and convert them to integers
        String[] inputArray = input.split(" ");
        int[] array = new int[inputArray.length];

        // Convert the input strings to integers and store them in the array
        for (int i = 0; i < inputArray.length; i++) {
            array[i] = Integer.parseInt(inputArray[i]);
        }

        // Print the initial array (Initialization step)
        System.out.println("\nInitialization:");
        printArray(array);

        // Perform radix sort on the array
        radixSort(array);

        // Print the final sorted array
        System.out.println("\nSorted List:");
        printArray(array);
    }

    // Method to print arrays
    public static void printArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    // Radix sort method
    public static void radixSort(int[] array) {
        // Get the maximum number to know how many digits we need to sort
        int max = getMax(array);

        // Get the second largest number
        int secondMax = getSecondMax(array, max);

        // Perform counting sort for every digit. The place value is 1, 10, 100, 1000, etc.
        // Loop until we've processed the second largest number's highest digit + 1 pass
        int maxDigits = getNumDigits(secondMax) + 1;
        for (int exp = 1; exp <= Math.pow(10, maxDigits - 1); exp *= 10) {
            countSortByDigit(array, exp);
        }
    }

    // Get the maximum number from the array
    public static int getMax(int[] array) {
        int max = array[0];
        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    // Get the second largest number
    public static int getSecondMax(int[] array, int max) {
        int secondMax = Integer.MIN_VALUE;
        for (int num : array) {
            if (num != max && num > secondMax) {
                secondMax = num;
            }
        }
        return secondMax;
    }

    // Get the number of digits in a number
    public static int getNumDigits(int num) {
        int digits = 0;
        while (num > 0) {
            num /= 10;
            digits++;
        }
        return digits;
    }

    // Counting sort based on a specific digit (represented by exp)
    public static void countSortByDigit(int[] array, int exp) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[10]; // There are 10 possible digits (0 to 9)

        // Store count of occurrences in count[]
        for (int i = 0; i < n; i++) {
            int digit = (array[i] / exp) % 10;
            count[digit]++;
        }

        // Change count[i] so that count[i] contains the actual position of this digit in output[]
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array by placing the numbers in the correct position
        for (int i = n - 1; i >= 0; i--) {
            int digit = (array[i] / exp) % 10;
            output[count[digit] - 1] = array[i];
            count[digit]--;
        }

        // Copy the sorted numbers back into the original array
        System.arraycopy(output, 0, array, 0, n);

        // Print the array after sorting by the current digit
        System.out.println("\nAfter sorting by digit (exp = " + exp + "):");
        printArray(array);
    }
}
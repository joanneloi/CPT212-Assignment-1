import java.util.Arrays;
import java.util.Scanner;

public class RadixSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input handling with validation
        String[] numbers;
        while (true) {
            System.out.println("Enter numbers separated by space:");
            String input = scanner.nextLine().trim();
            
            numbers = input.split("\\s+");
            numbers = Arrays.stream(numbers)
                    .filter(s -> !s.isEmpty())
                    .toArray(String[]::new);

            if (numbers.length > 0) break;
            System.out.println("Error: No numbers entered. Please try again.");
        }

        // Get maximum length and pad numbers
        int maxLength = getMaxLength(numbers);
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = padLeftZeros(numbers[i], maxLength);
        }

        // Initialize arrays
        String[] array1 = new String[numbers.length];
        String[] array2 = new String[numbers.length];
        System.arraycopy(numbers, 0, array1, 0, numbers.length);

        // Radix sort
        for (int digitIndex = maxLength - 1; digitIndex >= 0; digitIndex--) {
            System.out.println("\nAfter processing digit at index " + digitIndex + " (from right):");

            if (digitIndex == maxLength - 1) {
                // First pass - sort from input to array1
                sortByDigit(numbers, array1, digitIndex);
                System.out.println("Output from array1:");
                printArray(array1);
            } 
            else if ((maxLength - 1 - digitIndex) % 2 == 1) {
                // Odd passes - sort from array1 to array2
                sortByDigit(array1, array2, digitIndex);
                System.out.println("Output from array2:");
                printArray(array2);
            } 
            else {
                // Even passes - sort from array2 to array1
                sortByDigit(array2, array1, digitIndex);
                System.out.println("Output from array1:");
                printArray(array1);
            }
        }

        // Final output
        System.out.println("\nFinal sorted result:");
        if ((maxLength - 1) % 2 == 0) {
            printArray(array1);
        } else {
            printArray(array2);
        }

        scanner.close();
    }

    private static int getMaxLength(String[] array) {
        int maxLength = 0;
        for (String num : array) {
            maxLength = Math.max(maxLength, num.length());
        }
        return maxLength;
    }

    private static String padLeftZeros(String input, int length) {
        return String.format("%" + length + "s", input).replace(' ', '0');
    }

    private static void sortByDigit(String[] source, String[] dest, int digitIndex) {
        int[] counts = new int[10];
        String[][] buckets = new String[10][source.length];

        // Distribute into buckets
        for (String num : source) {
            int digit = Character.getNumericValue(num.charAt(digitIndex));
            buckets[digit][counts[digit]++] = num;
        }

        // Print buckets
        for (int i = 0; i < 10; i++) {
            System.out.print("Bucket " + i + ": [");
            for (int j = 0; j < counts[i]; j++) {
                if (j > 0) System.out.print(", ");
                System.out.print(buckets[i][j]);
            }
            System.out.println("] ");
        }

        // Collect from buckets
        int index = 0;
        for (int i = 0; i < 10; i++) {
            System.arraycopy(buckets[i], 0, dest, index, counts[i]);
            index += counts[i];
        }
    }

    private static void printArray(String[] array) {
        for (String num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
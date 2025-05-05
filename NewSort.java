import java.util.Arrays;

public class NewSort {
    public static void main(String[] args) {       
        // Get input numbers
        String[] numbers = {"275", "087", "426", "061", "409", "170", "677", "503"};

        // Pad numbers with leading zeros
        int maxLength = numbers[0].length();
        int count = numbers.length;

        // Perform radix sort
        String[]sorted = radixSort(numbers, maxLength, count);

        // Final output
        // Output result
        System.out.println("\nFinal sorted result:");
        System.out.println(String.join(" ", sorted));
    }

    private static String[] radixSort(String[] numbers, int maxLength, int count) {
        // Initialize arrays
        String[][] Array1 = new String[10][10];
        String[][] Array2 = new String[10][10];
        int[] Array1Count = new int[10];
        int[] Array2Count = new int[10];

        //System.arraycopy(numbers, 0, array1, 0, numbers.length);
        for (int digitIndex = maxLength - 1; digitIndex >= 0; digitIndex--) {
            // digit index 可以换一下，怪怪的，就是个位数是4（现在）
            System.out.println("\nAfter processing digit at index " + (digitIndex+1));

            if (digitIndex == maxLength - 1) {
                // First pass - sort from input to array1
                for (String num : numbers) {
                    int digit = Character.getNumericValue(num.charAt(digitIndex));
                    Array1[digit][Array1Count[digit]++] = num;
                }
                System.out.println("Array1 after pass:");
                printBuckets(Array1, Array1Count);
            } 
            else if ((maxLength - 1 - digitIndex) % 2 == 1) {
                // Odd passes - sort from array1 to array2
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < Array1Count[i]; j++) {
                        String num = Array1[i][j];
                        int digit = Character.getNumericValue(num.charAt(digitIndex));
                        Array2[digit][Array2Count[digit]++] = num;
                    }
                }
                System.out.println("Array2 after pass:");
                printBuckets(Array2, Array2Count);
                // Reset Array1Count for next pass 
                Arrays.fill(Array1Count, 0);
                // Reset Array1 for next pass
                for (int i = 0; i < 10; i++) {
                    Arrays.fill(Array1[i], null);
                }
            } 
            else {
                // Even passes - sort from array2 to array1
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < Array2Count[i]; j++) {
                        String num = Array2[i][j];
                        int digit = Character.getNumericValue(num.charAt(digitIndex));
                        Array1[digit][Array1Count[digit]++] = num;
                    }
                }
                System.out.println("Array1 after pass:");
                printBuckets(Array1, Array1Count);
                // Reset Array2Count for next pass
                Arrays.fill(Array2Count, 0);
                // Reset Array2 for next pass
                for (int i = 0; i < 10; i++) {
                    Arrays.fill(Array2[i], null);
                }
            }
        }

        String[] finalArray = new String[count];
        int index = 0;
        // Collect from buckets
        // Final pass - copy sorted numbers to the final array
        if ((maxLength - 1) % 2 == 0) {
            for (int j = 0; j < Array2Count[i]; j++) {
                    finalArray[index++] = Array2[i][j];
                }
            }
        }
        return finalArray;
    }

    public static void printBuckets(String[][] buckets, int[] counts) {
        for (int i = 0; i < buckets.length; i++) {
            System.out.print("Bucket " + i + ": ");
            for (int j = 0; j < counts[i]; j++) {
                System.out.print(buckets[i][j] + " ");
            }
            System.out.println();
        }
    }
}
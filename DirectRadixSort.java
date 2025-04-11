import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DirectRadixSort {
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

        // Radix sort
        ArrayList<String>[] sortedArray = radixSort(numbers, maxLength);

        // Final output
        System.out.println("\nFinal sorted result:");
        printArray(sortedArray);

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

    private static ArrayList<String>[] radixSort(String[] numbers, int maxLength) {
        // Initialize arrays
        ArrayList<String>[] Array1 = new ArrayList[10];
        ArrayList<String>[] Array2 = new ArrayList[10];

        //initialize each buckets
        for (int i = 0; i < 10; i++) {
            Array1[i] = new ArrayList<>();
            Array2[i] = new ArrayList<>();
        }

        //System.arraycopy(numbers, 0, array1, 0, numbers.length);

        for (int digitIndex = maxLength - 1; digitIndex >= 0; digitIndex--) {
            // digit index 可以换一下，怪怪的，就是个位数是4（现在）
            System.out.println("\nAfter processing digit at index " + (digitIndex+1) + "(from right): ");

            if (digitIndex == maxLength - 1) {
                // First pass - sort from input to array1
                sortByDigitFromInput(numbers, Array1, digitIndex);
                System.out.println("Output from Array1:");
                printArrayList(Array1);
            } 
            else if ((maxLength - 1 - digitIndex) % 2 == 1) {
                // Odd passes - sort from array1 to array2
                for (int i = 0; i < 10; i++) {
                    Array2[i].clear();
                }
                sortByDigit(Array1, Array2, digitIndex);
                System.out.println("Output from Array2:");
                printArrayList(Array2);
            } 
            else {
                // Even passes - sort from array2 to array1
                for (int i = 0; i < 10; i++) {
                    Array1[i].clear();
                }
                sortByDigit(Array2, Array1, digitIndex);
                System.out.println("Output from Array1:");
                printArrayList(Array1);
            }
        }
        // Final pass - copy sorted numbers to the final array
        if ((maxLength - 1) % 2 == 0) {
            return Array1;
        } else {
            return Array2;
        }
    }

    private static void sortByDigitFromInput(String[] source, ArrayList<String>[] dest, int digitIndex) {
        //distribute into dest
        for (String num : source) {
            int digitnumber = Character.getNumericValue(num.charAt(digitIndex));
            dest[digitnumber].add(num);
        }
    }

    private static void sortByDigit(ArrayList<String>[] source, ArrayList<String>[] dest, int digitIndex) {
        //distribute into dest
        for (int i = 0; i < 10; i++) {
            for (String num : source[i]) {
                int digitnumber = Character.getNumericValue(num.charAt(digitIndex));
                dest[digitnumber].add(num);
            }
        }
    }

    private static void printArrayList(ArrayList<String>[] lists) {
        for (int i = 0; i < 10; i++) {
            System.out.print(i + ": ");
                for (String val : lists[i]){
                    System.out.print(val + " ");
                }
            System.out.println();
        }
    }

    private static void printArray(ArrayList<String>[] Array) {
        for (int i = 0; i < 10; i++) {
            for (String num : Array[i]) {
                System.out.print(num + " ");
            }
        }
        System.out.println();
    }
}
import java.util.ArrayList;
import java.util.Scanner;

public class RadixSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Asking the user to enter a list of numbers
        System.out.println("Enter numbers separated by space:");
        String input = scanner.nextLine();
        String[] numbers = input.split(" ");

        // Convert input to ArrayList of Strings
        ArrayList<String> array = new ArrayList<>();
        for (String num : numbers) {
            array.add(num);
        }

        // Get maximum length for padding
        int maxLength = getMaxLength(array);

        // Pad numbers with leading zeros for uniformity
        for (int i = 0; i < array.size(); i++) {
            array.set(i, padLeftZeros(array.get(i), maxLength));
        }

        // Perform radix sort from least significant digit to most
        for (int digitIndex = maxLength - 1; digitIndex >= 0; digitIndex--) {
            System.out.println("After processing digit at index " + digitIndex + " (from right):");
            array = sortByDigit(array, digitIndex);
            printBuckets(array, digitIndex);
        }

        // Final sorted result
        System.out.println("\nFinal sorted result:");
        for (String num : array) {
            System.out.print(num + " ");
        }
        scanner.close();
    }

    // Get the maximum number of digits
    private static int getMaxLength(ArrayList<String> array) {
        int maxLength = 0;
        for (String num : array) {
            if (num.length() > maxLength) {
                maxLength = num.length();
            }
        }
        return maxLength;
    }

    // Pad string with leading zeros to match max length
    private static String padLeftZeros(String input, int length) {
        while (input.length() < length) {
            input = "0" + input;
        }
        return input;
    }

    // Sort array by digit at given index
    private static ArrayList<String> sortByDigit(ArrayList<String> array, int digitIndex) {
        ArrayList<ArrayList<String>> buckets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayList<>());
        }

        for (String num : array) {
            int digit = Character.getNumericValue(num.charAt(digitIndex));
            buckets.get(digit).add(num);
        }

        ArrayList<String> sorted = new ArrayList<>();
        for (ArrayList<String> bucket : buckets) {
            sorted.addAll(bucket);
        }

        return sorted;
    }

    // Print bucket state (for visualization)
    private static void printBuckets(ArrayList<String> array, int digitIndex) {
        ArrayList<ArrayList<String>> buckets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayList<>());
        }

        for (String num : array) {
            int digit = Character.getNumericValue(num.charAt(digitIndex));
            buckets.get(digit).add(num);
        }

        for (int i = 0; i < buckets.size(); i++) {
            System.out.print("Bucket " + i + ": ");
            if (buckets.get(i).isEmpty()) {
                System.out.println("[]");
            } else {
                System.out.println(buckets.get(i));
            }
        }
    }
}


import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListSort {
    public static void main(String[] args) {       
        // Create a scanner object for user input
        Scanner scanner = new Scanner(System.in);
        // Get input numbers
        String[] numbers;
        while (true) {
            // Sample input: 112 564 789 123 456 125 098 234 666
            System.out.println("Enter numbers separated by space: The numbers should be in same length");
            String input = scanner.nextLine().trim();
            numbers = input.split("\\s+");
            if (numbers.length > 0) break; // Ensure input is not empty
            System.out.println("Error: No numbers entered. Please try again.");
        }

        // Get length of the numbers
        int maxLength = numbers[0].length();
        int count = numbers.length;

        // Perform radix sort
        String[] sorted = radixSort(numbers, maxLength, count);

        // Final output 
        System.out.println("\nFinal sorted result:");
        System.out.println(String.join(" ", sorted));

        scanner.close();
    }

    private static String[] radixSort(String[] numbers, int maxLength, int count) {

        // 1. Initialization
        System.out.println("1. Initialization");

        // Create 10 buckets for each array
        ArrayList<ArrayList<String>> Array1 = new ArrayList<>();
        ArrayList<ArrayList<String>> Array2 = new ArrayList<>();

        // Initialize buckets
        for (int i = 0; i < 10; i++) {
            Array1.add(new ArrayList<>());
            Array2.add(new ArrayList<>());
        }

        // Print initial state of buckets
        System.out.println("10 buckets created in Array1 and Array2");
        System.out.println();

        // 2. Initialization
        System.out.println("2. Initialization");
        for (int digitIndex = maxLength - 1; digitIndex >= 0; digitIndex--) {
            System.out.println("\nAfter processing digit at index " + (digitIndex + 1));

            if (digitIndex == maxLength - 1) {
                // First pass - distribute from input to Array1
                for (String num : numbers) {
                    int digit = Character.getNumericValue(num.charAt(digitIndex));
                    Array1.get(digit).add(num);
                }
                System.out.println("Array1 after pass:");
                printBuckets(Array1);
            } else if ((maxLength - 1 - digitIndex) % 2 == 1) {
                // Odd pass - Array1 to Array2
                for (int i = 0; i < 10; i++) {
                    for (String num : Array1.get(i)) {
                        int digit = Character.getNumericValue(num.charAt(digitIndex));
                        Array2.get(digit).add(num);
                    }
                }
                System.out.println("Array2 after pass:");
                printBuckets(Array2);
                // Clear Array1 for next round
                for (ArrayList<String> bucket : Array1) bucket.clear();
            } else {
                // Even pass - Array2 to Array1
                for (int i = 0; i < 10; i++) {
                    for (String num : Array2.get(i)) {
                        int digit = Character.getNumericValue(num.charAt(digitIndex));
                        Array1.get(digit).add(num);
                    }
                }
                System.out.println("Array1 after pass:");
                printBuckets(Array1);
                // Clear Array2 for next round
                for (ArrayList<String> bucket : Array2) bucket.clear();
            }
        }

        // Collect final sorted array
        System.out.println("3. Reorder");
        String[] finalArray = new String[count];
        int index = 0;

        // if last pass was written to Array1, collect from Array1
        // else collect from Array2
        if ((maxLength - 1) % 2 == 0) {
            for (ArrayList<String> bucket : Array1) {
                for (String num : bucket) {
                    finalArray[index++] = num;
                }
            }
        } else {
            for (ArrayList<String> bucket : Array2) {
                for (String num : bucket) {
                    finalArray[index++] = num;
                }
            }
        }

        return finalArray;
    }

    // Method to print the contents of the buckets
    public static void printBuckets(ArrayList<ArrayList<String>> buckets) {
        for (int i = 0; i < buckets.size(); i++) {
            System.out.print("Bucket " + i + ": ");
            for (String s : buckets.get(i)) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
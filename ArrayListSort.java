import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListSort {
    public static void main(String[] args) {       
        // Get input numbers
        Scanner scanner = new Scanner(System.in);
        // Get input numbers
        String[] numbers;
        while (true) {
            // Sample input: 112 564 789 123 456 125 098 234 666
            System.out.println("Enter numbers separated by space: The numbers should be in same length");
            String input = scanner.nextLine().trim();
            numbers = input.split("\\s+");
            if (numbers.length > 0) break;
            System.out.println("Error: No numbers entered. Please try again.");
        }

        // 
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

        System.out.println("1. Initialization");
        // Initialize buckets
        ArrayList<ArrayList<String>> Array1 = new ArrayList<>();
        ArrayList<ArrayList<String>> Array2 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Array1.add(new ArrayList<>());
            Array2.add(new ArrayList<>());
        }

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

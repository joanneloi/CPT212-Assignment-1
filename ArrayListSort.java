import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListSort {
    public static void main(String[] args) {       
        // Create a scanner object for user input
        Scanner scanner = new Scanner(System.in);
        String[] numbers;

        // Loop to ensure the user enters valid input (non-empty numbers and same-length numbers)
        while (true) {
            // Sample input: 112 564 789 123 456 125 098 234 666
            System.out.println("Enter numbers separated by space: The numbers should be in same length");
            String input = scanner.nextLine().trim();
            numbers = input.split("\\s+"); // Split the input string into an array by spaces

            // Check if there are any numbers entered, if not, prompt again
            if (numbers.length > 0) break; 
            System.out.println("Error: No numbers entered. Please try again.");
        }

        // Get the length of the numbers (assuming all numbers have the same length)
        int maxLength = numbers[0].length();
        int count = numbers.length; // Store the total number of elements in the input

        // Call the radixSort method to sort the numbers and return the sorted result
        String[] sorted = radixSort(numbers, maxLength, count);

        // Final output 
        System.out.println("\nFinal sorted result:");
        System.out.println(String.join(" ", sorted));
        
        scanner.close();
    }

    private static String[] radixSort(String[] numbers, int maxLength, int count) {

        // 1. Initialization
        System.out.println("1. Initialization");

        // Create two sets of 10 buckets (Array1 and Array2), for digits 0-9, to perform sorting by digits
        ArrayList<ArrayList<String>> Array1 = new ArrayList<>();
        ArrayList<ArrayList<String>> Array2 = new ArrayList<>();

        // Initialize 10 empty buckets for both Array1 and Array2 (one for each digit 0-9)
        for (int i = 0; i < 10; i++) {
            Array1.add(new ArrayList<>()); // Array1 stores intermediate results for odd passes
            Array2.add(new ArrayList<>()); // Array2 stores intermediate results for even passes
        }

        // Print initial state of the buckets before any sorting operation
        System.out.println("10 buckets created in Array1 and Array2");
        System.out.println();

        // 2. Initialization
        // Sorting by each digit from least significant to most significant (Radix Sort)
        System.out.println("2. Initialization");
        for (int digitIndex = maxLength - 1; digitIndex >= 0; digitIndex--) {
            System.out.println("\nAfter processing digit at index " + (digitIndex + 1));

            // First pass (distribute the numbers into Array1 based on the current digit)
            if (digitIndex == maxLength - 1) {
                // Iterate through each number and extract the digit at current position (digitIndex)
                for (String num : numbers) {
                    int digit = Character.getNumericValue(num.charAt(digitIndex)); // Extract the digit at the current index
                    Array1.get(digit).add(num); // Place the number in the corresponding bucket in Array1 based on the digit
                }
                System.out.println("Array1 after pass:");
                printBuckets(Array1);
            } else if ((maxLength - 1 - digitIndex) % 2 == 1) {
                 // Odd passes: Move numbers from Array1 to Array2 based on the current digit (digitIndex)
                for (int i = 0; i < 10; i++) {
                    for (String num : Array1.get(i)) {
                        int digit = Character.getNumericValue(num.charAt(digitIndex));
                        Array2.get(digit).add(num); // Add the number to the corresponding bucket in Array2
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

        // 3. Reorder
        // Collect final sorted array
        System.out.println("3. Reorder");

        // Array to hold the final sorted numbers
        String[] finalArray = new String[count];
        int index = 0;

        // After processing all the digits, check which array (Array1 or Array2) contains the final sorted result
        if ((maxLength - 1) % 2 == 0) {
            // If the last pass used Array1, collect numbers from Array1
            for (ArrayList<String> bucket : Array1) {
                for (String num : bucket) {
                    finalArray[index++] = num;  // Add the number to the final sorted array
                }
            }
        } else {
            // If the last pass used Array2, collect numbers from Array2
            for (ArrayList<String> bucket : Array2) {
                for (String num : bucket) {
                    finalArray[index++] = num; // Add the number to the final sorted array
                }
            }
        }

        // Return the final sorted array
        return finalArray;
    }

    // Method to print the contents of the buckets (Array1 or Array2) after each pass
    public static void printBuckets(ArrayList<ArrayList<String>> buckets) {
        // Loop through each bucket and print its contents
        for (int i = 0; i < buckets.size(); i++) {
            System.out.print("Bucket " + i + ": ");
            // Iterate through each number in the current bucket and print it
            for (String s : buckets.get(i)) {
                System.out.print(s + " "); // Print each number in the bucket
            }
            System.out.println();  // Print a new line after printing the contents of each bucket
        }
        System.out.println();
    }
}
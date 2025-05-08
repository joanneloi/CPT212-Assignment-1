import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListSortCounter {
    static int counter = 0; // Counter for primitive operations
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] numbers; // Declare the array to store the numbers

        while (true) {
            System.out.println("Enter numbers separated by space: The numbers should be in same length");

            String input = scanner.nextLine().trim(); // Read full line of input and trim leading/trailing spaces
            counter += 3; // Count operation: nextLine, trim, assignment

            numbers = input.split("\\s+"); // Split the input string into an array of strings
            counter += 2; // Count operation: nextLine, trim, assignment
            
            counter++; // Count the comparison for the length of numbers
            if (numbers.length > 0) { // Check if the array is entered at least one number
                break;
            }

            System.out.println("Error: No numbers entered. Please try again.");
        }

        int maxLength = numbers[0].length(); // Get the length of the first number
        counter++; // Count assignment operation

        int count = numbers.length; // Store total count of numbers entered by user
        counter++; // Count assignment operation

        String[] sorted = radixSort(numbers, maxLength, count); // Call radixSort and returned sorted array
        counter += 2; // Count operation: method call + assignment

        System.out.println("\nFinal sorted result:");
        System.out.println(String.join(" ", sorted)); // Print the sorted array

        scanner.close();
        System.out.println("Total primitive operations: " + counter); // Display the total operations counted
    }

    private static String[] radixSort(String[] numbers, int maxLength, int count) {

        System.out.println("1. Initialization");

        ArrayList<ArrayList<String>> Array1 = new ArrayList<>();
        ArrayList<ArrayList<String>> Array2 = new ArrayList<>();
        counter += 2; // Count two initialization operations

        counter += 2; // Count upcoming assignment and loop comparison
        for (int i = 0; i < 10; i++) {
            counter += 3; // Count loop condition, assignment and addition: i++ (i=i+1)
            Array1.add(new ArrayList<>());
            Array2.add(new ArrayList<>());
            counter += 2; // Count add() operation
        }

        System.out.println("2. Initialization");

        counter += 3; // Count assignment of digitIndex, loop condition and initial comparison
        for (int digitIndex = maxLength - 1; digitIndex >= 0; digitIndex--) { 
            counter += 3; // Count loop compare, assignment and subtraction: (digitIndex--)

            counter += 2; // Count subtraction and comparison to check if digitIndex is the first pass
            if (digitIndex == maxLength - 1) { // If this is the first pass
                counter += 2; // Count assignment and comparison
                for (String num : numbers) {        //equivalent to for (int i = 0; i < words.length; i++)
                    counter += 3; // Count loop condition, assignment and addition: i++ (i=i+1)
                    int digit = Character.getNumericValue(num.charAt(digitIndex));
                    Array1.get(digit).add(num);
                    counter += 5; // Count assignment, getNumericValue, charAt, get(), add()
                }
            } else if ((maxLength - 1 - digitIndex) % 2 == 1) {
                counter += 4; // Count subtraction, subtraction, modulus, comparison

                counter += 2; // Count assignment and comparison
                for (int i = 0; i < 10; i++) {
                    counter += 3; // Count loop condition, assignment and addition: i++ (i=i+1)
                    counter += 2; // Count assignment and comparison for nested loop
                    for (String num : Array1.get(i)) {              // equivalent to for (int j = 0; j < Array1.get(i).size(); j++)
                        counter += 4; // loop condition, assignment and addition: i++ (i=i+1), get operation
                        int digit = Character.getNumericValue(num.charAt(digitIndex));
                        Array2.get(digit).add(num);
                        counter += 5; // Count assignment, getNumericValue, charAt, get(), add()
                    }
                }

                counter += 2; // Count assignment and comparison
                for (ArrayList<String> bucket : Array1) { 
                    counter += 3; // Count loop condition, assignment and addition: i++ (i=i+1)
                    bucket.clear(); // Remove all elements from the bucket
                    counter++; // Count clear() operation
                }
                

            } else {
                counter += 4; // Count subtraction, subtraction, modulus, comparison
                counter += 2; // Count assignment and comparison
                for (int i = 0; i < 10; i++) {
                    counter += 3; // Count loop condition, assignment and addition: i++ (i=i+1)
                    counter += 2; // Count assignment and comparison
                    for (String num : Array2.get(i)) {
                        counter += 3; // Count loop condition, assignment and addition: i++ (i=i+1)
                        int digit = Character.getNumericValue(num.charAt(digitIndex)); // Extract digit
                        Array1.get(digit).add(num); // Place number into Array1 bucket
                        counter += 5; // Count assignment, getNumericValue, charAt, get(), add()
                    }
                }
                counter += 2; // Count assignment and comparison
                for (ArrayList<String> bucket : Array2) {
                    counter += 3; // Count loop condition, assignment and addition: i++ (i=i+1)
                    bucket.clear(); // Remove all elements from the bucket
                    counter++; // Count clear() operation
                }
            }
        }

        System.out.println("3. Reorder");

        String[] finalArray = new String[count]; // Initialize array to store final sorted numbers
        counter++; // Count assignment operation

        int index = 0;
        counter++; // initialization

        counter += 3; // Count subtraction, modulus, comparison to decide read from which bucket list
        if ((maxLength - 1) % 2 == 0) {
            counter += 2; // Count assignment and comparison
            for (ArrayList<String> bucket : Array1) {
                counter += 3; // Count loop condition, assignment and addition: i++ (i=i+1)
                counter += 2; // Count assignment and comparison
                for (String num : bucket) {
                    counter += 3; // Count loop condition, assignment and addition: i++ (i=i+1)
                    finalArray[index++] = num;
                    counter += 2; // Count assignment and increment
                }
            }
        } else {
            counter += 2; // Count assignment and comparison
            for (ArrayList<String> bucket : Array2) {
                counter += 3; // Count loop condition, assignment and addition: i++ (i=i+1)
                counter += 2; // Count assignment and comparison
                for (String num : bucket) {
                    counter += 3; // Count loop condition, assignment and addition: i++ (i=i+1)
                    finalArray[index++] = num;
                    counter += 2; // Count assignment and comparison
                }
            }
        }
        counter++; // Count return operation
        return finalArray; // Return the sorted array
    }
}
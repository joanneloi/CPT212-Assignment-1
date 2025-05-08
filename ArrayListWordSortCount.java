import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListWordSortCount {
    static int counter = 0; // Counter for primitive operations

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words; // Array to store the words

        while (true) {
            System.out.println("Enter words separated by space (any length):");
            String input = scanner.nextLine().trim(); 
            counter += 3; // Count: nextLine(), trim(), assignment

            words = input.toLowerCase().split("\\s+"); // Convert to lowercase and split on whitespace
            counter += 3; // Count: assignment + toLowerCase() + split() operation 
            counter++; // Count: comparison
            if (words.length > 0) break;
            System.out.println("Error: No words entered. Please try again."); // Error message
        }

        // Find longest word length for padding
        int maxLength = 0;
        counter++; // Count: assignment
        counter += 2; // Count: assignment + comparison
        for (String word : words) { //equivalent to for (int i = 0; i < words.length; i++)
            counter += 3; // Count: loop condition, assignment and addition: i++ (i=i+1)
            counter++; // Count: comparison
            if (word.length() > maxLength) {
                maxLength = word.length(); // update maxlength
                counter++; // Count: assignment
            }
        }

        // Pad words to uniform length
        counter +=2; // Count: assignment + comparison
        for (int i = 0; i < words.length; i++) {
            counter += 3; // Count: loop condition, assignment and addition: i++ (i=i+1)
            words[i] = String.format("%-" + maxLength + "s", words[i]); // padding
            counter += 2; // Count: assignment + format() operation
        }

        // Sort the words using radix sort
        String[] sorted = radixSort(words, maxLength);
        counter += 2; // Count: assignment + radixSort call

        // Final output (trim padding)
        System.out.println("\nFinal sorted result:");
        counter += 2; // Count: assignment + comparison
        for (String word : sorted) {
            counter += 3; // Count: loop condition, assignment and addition: i++ (i=i+1)
            System.out.print(word.trim() + " ");
        }

        System.out.println("\n\nTotal primitive operations: " + counter);
        scanner.close();
    }

    private static String[] radixSort(String[] words, int maxLength) {
        System.out.println("1. Initialization");

        ArrayList<ArrayList<String>> Array1 = new ArrayList<>();
        ArrayList<ArrayList<String>> Array2 = new ArrayList<>();
        counter += 2; // Count two assignments

        // Initialize 27 buckets (0 for space, 1-26 for a-z)
        counter += 2; // Count: assignment + comparison
        for (int i = 0; i < 27; i++) {
            counter += 3; // loop condition, assignment and addition: i++ (i=i+1)
            Array1.add(new ArrayList<>());
            Array2.add(new ArrayList<>());
            counter += 2; // Count: two add() operations
        }

        System.out.println("2. Sorting");
        counter += 2; // Count: assignment + comparison
        // Process characters from least significant (last) to most significant (first)
        for (int digitIndex = maxLength - 1; digitIndex >= 0; digitIndex--) {
            counter += 3; // Count: loop condition, assignment and substraction
            System.out.println("\nAfter processing character at index " + (digitIndex + 1));
            
            counter += 2; // Count: comparison and arithmetic operation
            if (digitIndex == maxLength - 1) {
                counter += 2; // Count: assignment + comparison
                for (String word : words) {
                    counter += 3; // Count: loop condition, assignment and addition: i++ (i=i+1)
                    int bucketIndex = getBucketIndex(word.charAt(digitIndex));
                    counter += 3; // Count: assignment + getBucketIndex + charAt
                    Array1.get(bucketIndex).add(word); // Add word to bucket
                    counter += 2; // Count: get() operation + add() operation
                }
            } else if ((maxLength - 1 - digitIndex) % 2 == 1) {
                counter += 4; // Count: 2 substract + modulus + comparison
                counter += 2; // Count: assignment + comparison
                for (int i = 0; i < 27; i++) {
                    counter += 3; // Count: loop condition, assignment and addition: i++ (i=i+1)
                    counter += 2; // Count: assignment + comparison
                    for (String word : Array1.get(i)) {             // equivalent to for (int j = 0; j < Array1.get(i).size(); j++)
                        counter += 4; // Count: loop condition, assignment and addition: i++ (i=i+1), get operation
                        int bucketIndex = getBucketIndex(word.charAt(digitIndex));
                        counter += 2; // Count: assignment + getBucketIndex call +charAt
                        Array2.get(bucketIndex).add(word);
                        counter += 2; // Count: get() operation + add() operation
                    }
                }
                counter += 2; // Count: assignment + comparison
                for (ArrayList<String> bucket : Array1) {
                    counter += 3; // Count: loop condition, assignment and addition: i++ (i=i+1)
                    bucket.clear(); // Remove all elements from the bucket
                    counter++; // Count: clear() operation
                }
            } else {
                counter += 4; // Count: 2 substract + modulus + comparison (for else if)
                counter += 2; // Count: assignment + comparison
                for (int i = 0; i < 27; i++) {
                    counter += 3; // Count: loop condition, assignment and addition: i++ (i=i+1)
                    counter += 2; // Count: assignment + comparison
                    for (String word : Array2.get(i)) {
                        counter += 4; // Count: loop condition, assignment and addition: i++ (i=i+1), get operation
                        int bucketIndex = getBucketIndex(word.charAt(digitIndex));
                        counter += 3; // Count: assignment + getBucketIndex call + charAt
                        Array1.get(bucketIndex).add(word);
                        counter += 2; // Count: get operation + add operation
                    }
                }
                counter += 2; // Count: assignment + comparison
                for (ArrayList<String> bucket : Array2) {
                    counter += 3; // Count: loop condition, assignment and addition: i++ (i=i+1)
                    bucket.clear();
                    counter++; // Count: clear() operation
                }
            }
        }

        System.out.println("3. Reorder");
        String[] finalArray = new String[words.length];
        counter++; // Count:  assignment
        int index = 0;
        counter++; // Count: assignment

        counter += 3; // Count: substract, modulus, comparison
        if ((maxLength - 1) % 2 == 0) {
            counter += 2; // Count: assignment + comparison
            for (int i = 0; i < 27; i++) {
                counter += 3; // Count: loop condition, assignment and addition: i++ (i=i+1)
                counter += 2; // Count: assignment + comparison
                for (String word : Array1.get(i)) {
                    counter += 4; // Count: loop condition, assignment and addition: i++ (i=i+1), get operation
                    finalArray[index++] = word;
                    counter += 2; // Count: assignment + increment
                }
            }
        } else {
            counter += 2; // Count: assignment + comparison
            for (int i = 0; i < 27; i++) {
                counter += 3; /// Count: loop condition, assignment and addition: i++ (i=i+1)
                counter += 2; // Count: assignment + comparison
                for (String word : Array2.get(i)) {
                    counter += 4; // Count: loop condition, assignment and addition: i++ (i=i+1), get operation
                    finalArray[index++] = word;
                    counter += 2; // Count: assignment + increment
                }
            }
        }
        counter++; // Count: return operation
        return finalArray; // Return the sorted array
    }

    // Function to map characters to bucket index
    private static int getBucketIndex(char c) {
        counter += 2; // Count: comparison + return
        if (c == ' ') return 0;
        counter += 2; // Count: 2 arrithmetic operations
        return c - 'a' + 1; // Map 'a' - 'z' to 1 - 26
    }

}

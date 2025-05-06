import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListWordSortCount {
    static int counter = 0; // Counter for primitive operations

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words;

        while (true) {
            System.out.println("Enter words separated by space (any length):");
            String input = scanner.nextLine().trim();
            counter += 3; // assignment
            words = input.toLowerCase().split("\\s+");
            counter += 3; // assignment + toLowerCase + split operation 
            if (words.length > 0) break;
            counter++; // comparison
            System.out.println("Error: No words entered. Please try again.");
        }

        // Pad words with spaces to match the longest word
        int maxLength = 0;
        counter++; // assignment
        for (String word : words) {
            counter++; // loop condition
            if (word.length() > maxLength) {
                counter++; // comparison
                maxLength = word.length();
                counter++; // assignment
            }
        }

        counter++; // assignment
        for (int i = 0; i < words.length; i++) {
            counter += 2; // loop condition + increment
            words[i] = String.format("%-" + maxLength + "s", words[i]); // padding
            counter += 2; // assignment + format operation
        }

        String[] sorted = radixSort(words, maxLength);
        counter += 2; // assignment + radixSort call

        // Final output (trim padding)
        System.out.println("\nFinal sorted result:");
        for (String word : sorted) {
            counter++; // loop condition
            System.out.print(word.trim() + " ");
        }

        System.out.println("\n\nTotal primitive operations: " + counter);

        scanner.close();
    }

    private static String[] radixSort(String[] words, int maxLength) {
        System.out.println("1. Initialization");

        ArrayList<ArrayList<String>> Array1 = new ArrayList<>();
        ArrayList<ArrayList<String>> Array2 = new ArrayList<>();
        counter += 2; // assignments

        counter++; // assignment
        for (int i = 0; i < 27; i++) {
            counter += 2; // loop condition + increment
            Array1.add(new ArrayList<>());
            Array2.add(new ArrayList<>());
            counter += 2; // add operations
        }

        System.out.println("2. Sorting");
        counter++; // assignment
        for (int digitIndex = maxLength - 1; digitIndex >= 0; digitIndex--) {
            counter += 2; // loop condition + decrement
            System.out.println("\nAfter processing character at index " + (digitIndex + 1));

            if (digitIndex == maxLength - 1) {
                counter++; // comparison
                for (String word : words) {
                    counter++; // loop condition
                    int bucketIndex = getBucketIndex(word.charAt(digitIndex));
                    counter += 3; // assignment + getBucketIndex + charAt
                    Array1.get(bucketIndex).add(word);
                    counter += 2; // get operation + add operation
                }
            } else if ((maxLength - 1 - digitIndex) % 2 == 1) {
                counter += 4; // 2 substract + modulus + comparison
                counter++; // assignment
                for (int i = 0; i < 27; i++) {
                    counter += 2; // loop condition + increment
                    for (String word : Array1.get(i)) {
                        counter += 2; // nested loop + get operation
                        int bucketIndex = getBucketIndex(word.charAt(digitIndex));
                        counter += 2; // assignment + getBucketIndex call +charAt
                        Array2.get(bucketIndex).add(word);
                        counter += 2; // get operation + add operation
                    }
                }
                for (ArrayList<String> bucket : Array1) {
                    counter++; // loop
                    bucket.clear();
                    counter++;
                }
            } else {
                for (int i = 0; i < 27; i++) {
                    counter += 2;
                    for (String word : Array2.get(i)) {
                        counter++; // nested loop
                        int bucketIndex = getBucketIndex(word.charAt(digitIndex));
                        counter += 2;
                        Array1.get(bucketIndex).add(word);
                        counter++;
                    }
                }
                System.out.println("Array1 after pass:");
                printBuckets(Array1);
                for (ArrayList<String> bucket : Array2) {
                    counter++; // loop
                    bucket.clear();
                    counter++;
                }
            }
        }

        System.out.println("3. Reorder");
        String[] finalArray = new String[words.length];
        counter++; // assignment
        int index = 0;
        counter++;

        if ((maxLength - 1) % 2 == 0) {
            for (int i = 0; i < 27; i++) {
                counter += 2;
                for (String word : Array1.get(i)) {
                    counter++;
                    finalArray[index++] = word;
                    counter += 2;
                }
            }
        } else {
            for (int i = 0; i < 27; i++) {
                counter += 2;
                for (String word : Array2.get(i)) {
                    counter++;
                    finalArray[index++] = word;
                    counter += 2;
                }
            }
        }

        return finalArray;
    }

    private static int getBucketIndex(char c) {
        counter++; // comparison
        if (c == ' ') return 0;
        counter++; // conversion
        return c - 'a' + 1;
    }

    public static void printBuckets(ArrayList<ArrayList<String>> buckets) {
        for (int i = 0; i < buckets.size(); i++) {
            counter += 2; // loop
            if (!buckets.get(i).isEmpty()) {
                counter++; // condition
                if (i == 0) {
                    System.out.print("Bucket [space]: ");
                } else {
                    char label = (char) ('A' + i - 1);
                    counter += 2;
                    System.out.print("Bucket " + label + ": ");
                }
                for (String word : buckets.get(i)) {
                    counter++; // loop
                    System.out.print(word.trim() + " ");
                }
                System.out.println();
            }
        }
        System.out.println();
    }
}

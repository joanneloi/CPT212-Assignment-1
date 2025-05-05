import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListWordSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words;

        while (true) {
            System.out.println("Enter words separated by space (any length):");
            String input = scanner.nextLine().trim();
            words = input.split("\\s+");
            if (words.length > 0) break;
            System.out.println("Error: No words entered. Please try again.");
        }

        // Pad words with spaces to match the longest word
        int maxLength = 0;
        for (String word : words) {
            if (word.length() > maxLength) maxLength = word.length();
        }

        for (int i = 0; i < words.length; i++) {
            words[i] = String.format("%-" + maxLength + "s", words[i]); // pad with spaces
        }

        String[] sorted = radixSort(words, maxLength);

        // Final output (trim padding)
        System.out.println("\nFinal sorted result:");
        for (String word : sorted) {
            System.out.print(word.trim() + " ");
        }

        scanner.close();
    }

    private static String[] radixSort(String[] words, int maxLength) {
        System.out.println("1. Initialization");

        ArrayList<ArrayList<String>> Array1 = new ArrayList<>();
        ArrayList<ArrayList<String>> Array2 = new ArrayList<>();

        for (int i = 0; i < 256; i++) {
            Array1.add(new ArrayList<>());
            Array2.add(new ArrayList<>());
        }

        System.out.println("2. Sorting");
        for (int digitIndex = maxLength - 1; digitIndex >= 0; digitIndex--) {
            System.out.println("\nAfter processing character at index " + (digitIndex + 1));

            if (digitIndex == maxLength - 1) {
                for (String word : words) {
                    int ascii = word.charAt(digitIndex);
                    Array1.get(ascii).add(word);
                }
                System.out.println("Array1 after pass:");
                printBuckets(Array1);
            } else if ((maxLength - 1 - digitIndex) % 2 == 1) {
                for (int i = 0; i < 256; i++) {
                    for (String word : Array1.get(i)) {
                        int ascii = word.charAt(digitIndex);
                        Array2.get(ascii).add(word);
                    }
                }
                System.out.println("Array2 after pass:");
                printBuckets(Array2);
                for (ArrayList<String> bucket : Array1) bucket.clear();
            } else {
                for (int i = 0; i < 256; i++) {
                    for (String word : Array2.get(i)) {
                        int ascii = word.charAt(digitIndex);
                        Array1.get(ascii).add(word);
                    }
                }
                System.out.println("Array1 after pass:");
                printBuckets(Array1);
                for (ArrayList<String> bucket : Array2) bucket.clear();
            }
        }

        System.out.println("3. Reorder");
        String[] finalArray = new String[words.length];
        int index = 0;

        if ((maxLength - 1) % 2 == 0) {
            for (int i = 0; i < 256; i++) {
                for (String word : Array1.get(i)) {
                    finalArray[index++] = word;
                }
            }
        } else {
            for (int i = 0; i < 256; i++) {
                for (String word : Array2.get(i)) {
                    finalArray[index++] = word;
                }
            }
        }

        return finalArray;
    }

    public static void printBuckets(ArrayList<ArrayList<String>> buckets) {
        for (int i = 0; i < buckets.size(); i++) {
            if (!buckets.get(i).isEmpty()) {
                char label = (char) i;
                if (Character.isLetterOrDigit(label)) {
                    System.out.print("Bucket " + label + ": ");
                } else if (label == ' ') {
                    System.out.print("Bucket [space]: ");
                } else {
                    System.out.print("Bucket ?: ");
                }
                for (String word : buckets.get(i)) {
                    System.out.print(word.trim() + " ");
                }
                System.out.println();
            }
        }
        System.out.println();
    }
}

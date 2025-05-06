import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListSortCounter {
    static long operationCount = 0; // Global counter

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] numbers;

        while (true) {
            System.out.println("Enter numbers separated by space: The numbers should be in same length");
            String input = scanner.nextLine().trim();
            numbers = input.split("\\s+");
            if (numbers.length > 0) break;
            System.out.println("Error: No numbers entered. Please try again.");
        }

        int maxLength = numbers[0].length();
        int count = numbers.length;

        // Reset operation count
        operationCount = 0;

        // Perform radix sort
        String[] sorted = radixSort(numbers, maxLength, count);

        // Final output
        System.out.println("\nFinal sorted result:");
        System.out.println(String.join(" ", sorted));

        // Output primitive operation count
        System.out.println("\nTotal primitive operations: " + operationCount);

        scanner.close();
    }

    private static String[] radixSort(String[] numbers, int maxLength, int count) {
        ArrayList<ArrayList<String>> Array1 = new ArrayList<>();
        ArrayList<ArrayList<String>> Array2 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Array1.add(new ArrayList<>());
            Array2.add(new ArrayList<>());
            operationCount += 4; // loop + add
        }

        for (int digitIndex = maxLength - 1; digitIndex >= 0; digitIndex--) {
            operationCount += 2; // for loop + comparison
            if (digitIndex == maxLength - 1) {
                for (String num : numbers) {
                    int digit = Character.getNumericValue(num.charAt(digitIndex));
                    operationCount += 5; // access, charAt, get, add, assignment
                    Array1.get(digit).add(num);
                }
            } else if ((maxLength - 1 - digitIndex) % 2 == 1) {
                for (int i = 0; i < 10; i++) {
                    for (String num : Array1.get(i)) {
                        int digit = Character.getNumericValue(num.charAt(digitIndex));
                        Array2.get(digit).add(num);
                        operationCount += 7; // loop, get, charAt, numeric value, get, add, assignment
                    }
                }
                for (ArrayList<String> bucket : Array1) {
                    bucket.clear();
                    operationCount += 1;
                }
            } else {
                for (int i = 0; i < 10; i++) {
                    for (String num : Array2.get(i)) {
                        int digit = Character.getNumericValue(num.charAt(digitIndex));
                        Array1.get(digit).add(num);
                        operationCount += 7;
                    }
                }
                for (ArrayList<String> bucket : Array2) {
                    bucket.clear();
                    operationCount += 1;
                }
            }
        }

        // Collect final sorted array
        String[] finalArray = new String[count];
        int index = 0;
        operationCount += 2;

        if ((maxLength - 1) % 2 == 0) {
            for (ArrayList<String> bucket : Array1) {
                for (String num : bucket) {
                    finalArray[index++] = num;
                    operationCount += 2;
                }
            }
        } else {
            for (ArrayList<String> bucket : Array2) {
                for (String num : bucket) {
                    finalArray[index++] = num;
                    operationCount += 2;
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

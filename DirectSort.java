import java.util.Arrays;
import java.util.Scanner;

public class DirectSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input handling
        String[] numbers;
        while (true) {
            System.out.println("Enter numbers separated by space:");
            String input = scanner.nextLine().trim();
            numbers = input.split("\\s+");
            numbers = Arrays.stream(numbers).filter(s -> !s.isEmpty()).toArray(String[]::new);
            if (numbers.length > 0) break;
            System.out.println("Error: No numbers entered. Please try again.");
        }

        // Pad numbers with leading zeros
        int maxLength = getMaxLength(numbers);
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = padLeftZeros(numbers[i], maxLength);
        }

        // Radix sort
        String[] sortedArray = radixSort(numbers, maxLength);

        // Output
        System.out.println("\nFinal sorted result:");
        System.out.println(String.join(" ", sortedArray));

        scanner.close();
    }

    private static int getMaxLength(String[] array) {
        return Arrays.stream(array).mapToInt(String::length).max().orElse(0);
    }

    private static String padLeftZeros(String input, int length) {
        return String.format("%" + length + "s", input).replace(' ', '0');
    }

    private static String[] radixSort(String[] numbers, int maxLength) {
        String[] Array1 = Arrays.copyOf(numbers, numbers.length);
        String[] Array2 = new String[numbers.length];
        int[] count = new int[10]; // Tracks bucket sizes

        for (int digitIndex = maxLength - 1; digitIndex >= 0; digitIndex--) {
            Arrays.fill(count, 0);

            // Count occurrences of each digit
            for (String num : Array1) {
                int digit = Character.getNumericValue(num.charAt(digitIndex));
                count[digit]++;
            }

            // Compute bucket offsets (prefix sum)
            int total = 0;
            for (int i = 0; i < 10; i++) {
                int oldCount = count[i];
                count[i] = total;
                total += oldCount;
            }

            // Distribute elements into Array2
            for (String num : Array1) {
                int digit = Character.getNumericValue(num.charAt(digitIndex));
                Array2[count[digit]++] = num;
            }

            // Swap Array1 and Array2 for next pass
            String[] temp = Array1;
            Array1 = Array2;
            Array2 = temp;

            System.out.println("\nAfter processing digit at index " + (digitIndex + 1) + ":");
            System.out.println(String.join(" ", Array1));
        }

        return Array1;
    }
}
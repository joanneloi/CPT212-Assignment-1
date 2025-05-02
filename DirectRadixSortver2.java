import java.util.Arrays;
import java.util.Scanner;

public class DirectRadixSortver2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Initialize
        System.out.println("1. Initialize");
        
        // Input handling with validation
        String[] numbers;
        while (true) {
            System.out.println("Enter numbers separated by space:");
            String input = scanner.nextLine().trim();
            
            numbers = input.split("\\s+");
            numbers = Arrays.stream(numbers)
                    .filter(s -> !s.isEmpty())
                    .toArray(String[]::new);

            if (numbers.length > 0) break;
            System.out.println("Error: No numbers entered. Please try again.");
        }

        System.out.print("Example: ");
        printArray(numbers);
        
        // Get maximum length and pad numbers if needed
        int maxLength = getMaxLength(numbers);
        
        // Initialize Array1 and Array2 (buckets for each digit 0-9)
        String[][] array1 = new String[10][];
        String[][] array2 = new String[10][];
        
        // Initialize empty buckets for both arrays
        for (int i = 0; i < 10; i++) {
            array1[i] = new String[0];
            array2[i] = new String[0];
        }
        
        // Display the initial empty buckets
        System.out.println("\nArray1");
        displayBuckets(array1);
        
        System.out.println("\nArray2");
        displayBuckets(array2);
        
        // 2. Iteration
        System.out.println("\n2. Iteration");
        
        // Place all numbers initially in array1 bucket 0
        for (String num : numbers) {
            array1[0] = addToArray(array1[0], num);
        }
        
        // Perform radix sort from right to left
        String[][] currentArray = array1;
        String[][] nextArray = array2;
        
        for (int pos = 1; pos <= maxLength; pos++) {
            System.out.println("Pass " + pos + " (sorting by position " + pos + " from right)");
            
            // Clear the next array before each pass
            for (int i = 0; i < 10; i++) {
                nextArray[i] = new String[0];
            }
            
            // Process all numbers from current array in order
            for (int i = 0; i < 10; i++) {
                for (String num : currentArray[i]) {
                    // Calculate digit position from right
                    int digitPos = num.length() - pos;
                    
                    // Determine bucket index
                    int bucketIndex;
                    if (digitPos < 0) {
                        bucketIndex = 0; // Place in first bucket if position doesn't exist
                    } else {
                        char digitChar = num.charAt(digitPos);
                        bucketIndex = Character.getNumericValue(digitChar);
                    }
                    
                    // Add the number to the appropriate bucket in the next array
                    nextArray[bucketIndex] = addToArray(nextArray[bucketIndex], num);
                }
            }
            
            // Display the array after this pass
            System.out.println(pos % 2 == 1 ? "Array2" : "Array1");
            displayBuckets(nextArray);
            
            // Swap the arrays for the next pass
            String[][] temp = currentArray;
            currentArray = nextArray;
            nextArray = temp;
        }
        
        // 3. Reorder - Collect the sorted numbers
        System.out.println("\n3. Reorder");
        StringBuilder sortedList = new StringBuilder("Sorted list: ");
        
        for (int i = 0; i < 10; i++) {
            for (String num : currentArray[i]) {
                sortedList.append(num).append(" ");
            }
        }
        
        System.out.println(sortedList.toString().trim());

        scanner.close();
    }

    private static int getMaxLength(String[] array) {
        int maxLength = 0;
        for (String num : array) {
            maxLength = Math.max(maxLength, num.length());
        }
        return maxLength;
    }  

    // Helper method to add a number to an array
    private static String[] addToArray(String[] array, String num) {
        String[] newArray = new String[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = num;
        return newArray;
    }
    
    // Method to display buckets in the required format
    private static void displayBuckets(String[][] buckets) {
        for (int i = 0; i < 10; i++) {
            System.out.print("bucket " + i + ": ");
            
            if (buckets[i].length > 0) {
                for (int j = 0; j < buckets[i].length; j++) {
                    System.out.print(buckets[i][j]);
                    if (j < buckets[i].length - 1) {
                        System.out.print(", ");
                    }
                }
            }
            System.out.println();
        }
    }

    private static void printArray(String[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
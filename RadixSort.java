public class RadixSort {
    public static void main(String[] args) {
        // Initialize the input array with the example numbers
        String[] numbers = {"275", "087", "426", "061", "409", "170", "677", "503"};
        
        System.out.println("1. Initialize");
        System.out.println("Example: 275, 087, 426, 061, 409, 170, 677, 503");
        
        // Initialize Array1 and Array2 (buckets for each digit 0-9)
        // Each bucket will store numbers horizontally as shown in the figure
        String[][] array1 = new String[10][]; // First array of buckets
        String[][] array2 = new String[10][]; // Second array of buckets
        
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
        
        System.out.println("\n2. Iteration");
        
        // First Pass - Sort by the last digit (ones place)
        System.out.println("First Pass");
        for (String num : numbers) {
            // Get the last digit (ones place)
            int digit = Character.getNumericValue(num.charAt(2));
            
            // Add the number to the appropriate bucket in array1
            array1[digit] = addToArray(array1[digit], num);
        }
        
        // Display array1 after first pass
        System.out.println("Array1");
        displayBuckets(array1);
        
        // Second Pass - Sort by the middle digit (tens place)
        System.out.println("\nSecond Pass");
        // Clear array2 before second pass
        for (int i = 0; i < 10; i++) {
            array2[i] = new String[0];
        }
        
        // Process all numbers from array1 in order
        for (int i = 0; i < 10; i++) {
            for (String num : array1[i]) {
                // Get the middle digit (tens place)
                int digit = Character.getNumericValue(num.charAt(1));
                
                // Add the number to the appropriate bucket in array2
                array2[digit] = addToArray(array2[digit], num);
            }
        }
        
        // Display array2 after second pass
        System.out.println("Array2");
        displayBuckets(array2);
        
        // Third Pass - Sort by the first digit (hundreds place)
        System.out.println("\nThird Pass");
        // Clear array1 before third pass
        for (int i = 0; i < 10; i++) {
            array1[i] = new String[0];
        }
        
        // Process all numbers from array2 in order
        for (int i = 0; i < 10; i++) {
            for (String num : array2[i]) {
                // Get the first digit (hundreds place)
                int digit = Character.getNumericValue(num.charAt(0));
                
                // Add the number to the appropriate bucket in array1
                array1[digit] = addToArray(array1[digit], num);
            }
        }
        
        // Display array1 after third pass
        System.out.println("Array1");
        displayBuckets(array1);
        
        // 3. Reorder - Collect the sorted numbers
        System.out.println("\n3. Reorder");
        StringBuilder sortedList = new StringBuilder("Sorted list: ");
        for (int i = 0; i < 10; i++) {
            for (String num : array1[i]) {
                sortedList.append(num).append(" ");
            }
        }
        
        System.out.println(sortedList.toString().trim());
    }
    
    // Helper method to add a number to an array
    private static String[] addToArray(String[] array, String num) {
        String[] newArray = new String[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = num;
        return newArray;
    }
    
    // Helper method to display buckets in the required format
    private static void displayBuckets(String[][] buckets) {
        // Display bucket indices
        for (int i = 0; i < 10; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        
        // Find the maximum bucket size to determine how many rows to display
        int maxSize = 0;
        for (String[] bucket : buckets) {
            if (bucket.length > maxSize) {
                maxSize = bucket.length;
            }
        }
        
        // Display the numbers in each bucket
        for (int row = 0; row < maxSize; row++) {
            for (int i = 0; i < 10; i++) {
                if (row < buckets[i].length) {
                    System.out.print(buckets[i][row] + "\t");
                } else {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }
}
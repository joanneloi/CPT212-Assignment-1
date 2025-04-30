public class RadixSortWords {
    public static void main(String[] args) {
        // Initialize the input array with example words
        String[] words = {"apple", "banana", "cat", "dog", "elephant", "fox", "grape", "hippo"};
        
        System.out.println("1. Initialize");
        System.out.println("Example: " + String.join(", ", words));
        
        // Find the maximum word length to determine number of passes
        int maxLength = 0;
        for (String word : words) {
            if (word.length() > maxLength) {
                maxLength = word.length();
            }
        }
        
        // Initialize Array1 and Array2 (buckets for each letter a-z)
        String[][] array1 = new String[26][]; // First array of buckets
        String[][] array2 = new String[26][]; // Second array of buckets
        
        // Initialize empty buckets for both arrays
        for (int i = 0; i < 26; i++) {
            array1[i] = new String[0];
            array2[i] = new String[0];
        }
        
        // Display the initial empty buckets
        System.out.println("\nArray1");
        displayBucketsVertical(array1);
        
        System.out.println("\nArray2");
        displayBucketsVertical(array2);
        
        System.out.println("\n2. Iteration");
        
        // Start sorting from the rightmost character (least significant)
        for (int pass = maxLength - 1; pass >= 0; pass--) {
            // Clear the target array before each pass
            String[][] targetArray = ((maxLength - 1 - pass) % 2 == 0) ? array1 : array2;
            String[][] sourceArray = ((maxLength - 1 - pass) % 2 == 0) ? array2 : array1;
            
            for (int i = 0; i < targetArray.length; i++) {
                targetArray[i] = new String[0];
            }
            
            // Process all words from source array
            if (pass == maxLength - 1) {
                // First pass - use original words array
                for (String word : words) {
                    int charPos = pass;
                    char c = (charPos < word.length()) ? word.charAt(charPos) : ' '; // Treat missing as space (comes before 'a')
                    int bucket = (c == ' ') ? 0 : c - 'a' + 1; // Space goes to bucket 0, 'a' to 1, etc.
                    
                    targetArray[bucket] = addToArray(targetArray[bucket], word);
                }
            } else {
                // Subsequent passes - use source array
                for (int i = 0; i < sourceArray.length; i++) {
                    for (String word : sourceArray[i]) {
                        int charPos = pass;
                        char c = (charPos < word.length()) ? word.charAt(charPos) : ' '; // Treat missing as space
                        int bucket = (c == ' ') ? 0 : c - 'a' + 1;
                        
                        targetArray[bucket] = addToArray(targetArray[bucket], word);
                    }
                }
            }
            
            // Display the array after each pass
            System.out.println("\nPass " + (maxLength - pass));
            System.out.println(((maxLength - 1 - pass) % 2 == 0) ? "Array1" : "Array2");
            displayBucketsVertical(((maxLength - 1 - pass) % 2 == 0) ? array1 : array2);
        }
        
        // 3. Reorder - Collect the sorted words from the final array
        System.out.println("\n3. Reorder");
        String[][] finalArray = (maxLength % 2 == 1) ? array1 : array2;
        
        StringBuilder sortedList = new StringBuilder("Sorted list: ");
        for (int i = 0; i < 26; i++) {
            for (String word : finalArray[i]) {
                sortedList.append(word).append(" ");
            }
        }
        
        System.out.println(sortedList.toString().trim());
    }
    
    // Helper method to add a word to an array
    private static String[] addToArray(String[] array, String word) {
        String[] newArray = new String[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = word;
        return newArray;
    }
    
    // Helper method to display buckets in vertical format with horizontal numbers
    private static void displayBucketsVertical(String[][] buckets) {
        // Display each bucket vertically
        for (int i = 0; i < 26; i++) {
            char bucketLabel = (i == 0) ? ' ' : (char)('a' + i - 1);
            System.out.print(bucketLabel + ": ");
            
            // Display all words in the bucket horizontally
            for (String word : buckets[i]) {
                System.out.print(word + " ");
            }
            System.out.println();
        }
    }
}
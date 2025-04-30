public class WordRadixSort {
    public static void main(String[] args) {
        // Initialize the input array with example words
        String[] words = {"cat", "dog", "apple", "banana", "zebra", "fish", "elephant", "ant"};
        
        System.out.println("1. Initialize");
        System.out.print("Example: ");
        for (int i = 0; i < words.length; i++) {
            System.out.print(words[i]);
            if (i < words.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        
        // Find the maximum length word to determine number of passes
        int maxLength = 0;
        for (String word : words) {
            if (word.length() > maxLength) {
                maxLength = word.length();
            }
        }
        
        // Initialize Array1 and Array2 (buckets for each letter a-z)
        // Each bucket will store words horizontally as shown in the figure
        String[][] array1 = new String[26][]; // First array of buckets (a-z)
        String[][] array2 = new String[26][]; // Second array of buckets (a-z)
        
        // Initialize empty buckets for both arrays
        for (int i = 0; i < 26; i++) {
            array1[i] = new String[0];
            array2[i] = new String[0];
        }
        
        // Display the initial empty buckets in the requested format
        System.out.println("\nArray1");
        displayBucketsHorizontal(array1);
        
        System.out.println("\nArray2");
        displayBucketsHorizontal(array2);
        
        System.out.println("\n2. Iteration");
        
        // Place all words initially in array1 bucket 0 (equivalent to no sorting yet)
        for (String word : words) {
            array1[0] = addToArray(array1[0], word);
        }
        
        // Perform radix sort from right to left (least significant to most significant character)
        String[][] currentArray = array1;
        String[][] nextArray = array2;
        
        for (int pos = 1; pos <= maxLength; pos++) {
            System.out.println("Pass " + pos + " (sorting by position " + pos + " from right)");
            
            // Clear the next array before each pass
            for (int i = 0; i < 26; i++) {
                nextArray[i] = new String[0];
            }
            
            // Process all words from current array in order
            for (int i = 0; i < 26; i++) {
                for (String word : currentArray[i]) {
                    // Calculate character position from right
                    int charPos = word.length() - pos;
                    
                    // If the word is shorter than the current position, put it in bucket 0
                    // This ensures shorter words come before longer words with the same prefix
                    int bucketIndex;
                    if (charPos < 0) {
                        bucketIndex = 0; // Place in first bucket if position doesn't exist
                    } else {
                        // Get the character at the position and convert to bucket index (a=0, b=1, etc.)
                        char ch = word.charAt(charPos);
                        bucketIndex = ch - 'a';
                    }
                    
                    // Add the word to the appropriate bucket in the next array
                    nextArray[bucketIndex] = addToArray(nextArray[bucketIndex], word);
                }
            }
            
            // Display the array after this pass in the horizontal format
            System.out.println(pos % 2 == 1 ? "Array2" : "Array1");
            displayBucketsHorizontal(nextArray);
            
            // Swap the arrays for the next pass
            String[][] temp = currentArray;
            currentArray = nextArray;
            nextArray = temp;
        }
        
        // 3. Reorder - Collect the sorted words in alphabetical order
        System.out.println("\n3. Reorder");
        
        // Create padded versions of the words for proper alphabetical sorting
        String[] paddedWords = new String[words.length];
        String[] originalWords = new String[words.length];
        int index = 0;
        
        // Collect all words from the final array
        for (int i = 0; i < 26; i++) {
            for (String word : currentArray[i]) {
                if (index < originalWords.length) {
                    originalWords[index++] = word;
                }
            }
        }
        
        // Create right-padded versions of the words
        for (int i = 0; i < originalWords.length; i++) {
            if (originalWords[i] != null) {
                paddedWords[i] = padRightWithSpaces(originalWords[i], maxLength);
            }
        }
        
        // Sort the padded words alphabetically
        for (int i = 0; i < paddedWords.length - 1; i++) {
            for (int j = i + 1; j < paddedWords.length; j++) {
                if (paddedWords[i] != null && paddedWords[j] != null && 
                    paddedWords[i].compareTo(paddedWords[j]) > 0) {
                    // Swap padded words
                    String tempPadded = paddedWords[i];
                    paddedWords[i] = paddedWords[j];
                    paddedWords[j] = tempPadded;
                    
                    // Swap original words too
                    String tempOriginal = originalWords[i];
                    originalWords[i] = originalWords[j];
                    originalWords[j] = tempOriginal;
                }
            }
        }
        
        // Display the sorted list
        StringBuilder sortedList = new StringBuilder("Sorted list: ");
        for (String word : originalWords) {
            if (word != null) {
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
    
    // Helper method to pad a word with spaces to the right
    private static String padRightWithSpaces(String word, int length) {
        StringBuilder sb = new StringBuilder(word);
        for (int i = word.length(); i < length; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }
    
    // Method to display buckets horizontally as shown in the example
    private static void displayBucketsHorizontal(String[][] buckets) {
        for (int i = 0; i < 26; i++) {
            char bucketLabel = (char)('a' + i);
            System.out.print(bucketLabel + ": ");
            
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
}
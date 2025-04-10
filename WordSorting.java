import java.util.*;

public class WordSorting {
    public static void main(String[] args) {
        // Example words to sort
        String[] words = {"apple", "cat", "dog", "banana", "elephant", "fox", "zebra"};
        int n = words.length;
        
        System.out.println("Original words: " + Arrays.toString(words));
        
        // 1. Initialize
        System.out.println("\n1. Initialization:");
        
        // Find the maximum length word to determine how many passes we need
        int maxLength = 0;
        for (String word : words) {
            if (word.length() > maxLength) {
                maxLength = word.length();
            }
        }
        System.out.println("Maximum word length: " + maxLength);
        
        // Create two arrays of buckets
        // We need 27 buckets: 0 for empty space (shorter words), 1-26 for letters a-z
        // This is a modification from the original 10 buckets for digits
        ArrayList<String>[] Array1 = new ArrayList[27];
        ArrayList<String>[] Array2 = new ArrayList[27];
            
        // Initialize each bucket
        for (int i = 0; i < 27; i++) {
            Array1[i] = new ArrayList<>();
            Array2[i] = new ArrayList<>();
        }
        System.out.println("Array1 and Array2 initialized with 27 buckets (0 for empty, 1-26 for a-z)");
        
        // 2. Iteration
        System.out.println("\n2. Iteration:");
        
        // Initialize by placing all words in Array1[0]
        // This is different from the number sorting where we distributed by ones digit first
        for (String word : words) {
            Array1[0].add(word);
        }
        
        // For each character position, starting from the rightmost (least significant)
        // This maintains the same approach as the original algorithm
        ArrayList<String>[] currentArray = Array1;
        ArrayList<String>[] nextArray = Array2;
        
        for (int charPos = maxLength - 1; charPos >= 0; charPos--) {
            System.out.println("\nPass for character position " + charPos + " (from right):");
            
            // Clear the next array for this pass
            for (int i = 0; i < 27; i++) {
                nextArray[i].clear();
            }
            
            // Distribute words from current array to next array based on character at charPos
            // This is similar to the digit-based distribution in the original algorithm
            for (int i = 0; i < 27; i++) {
                for (String word : currentArray[i]) {
                    int bucketIndex;
                    
                    // If the word is shorter than the current position, put it in bucket 0
                    // This is a modification to handle variable-length words
                    if (charPos >= word.length()) {
                        bucketIndex = 0; // Empty space has lower priority than any letter
                    } else {
                        // Calculate bucket index: 'a' -> 1, 'b' -> 2, ..., 'z' -> 26
                        // This replaces the digit extraction in the original algorithm
                        bucketIndex = word.charAt(charPos) - 'a' + 1;
                    }
                    
                    nextArray[bucketIndex].add(word);
                }
            }
            
            // Print the next array after this pass
            for (int i = 0; i < 27; i++) {
                if (!nextArray[i].isEmpty()) {
                    String bucketName = (i == 0) ? "Empty" : String.valueOf((char)('a' + i - 1));
                    System.out.print("Bucket " + bucketName + ": ");
                    for (String word : nextArray[i]) {
                        System.out.print(word + " ");
                    }
                    System.out.println();
                }
            }
            
            // Swap current and next arrays for the next pass
            // This maintains the same approach as the original algorithm
            ArrayList<String>[] temp = currentArray;
            currentArray = nextArray;
            nextArray = temp;
        }
        
        // 3. Reorder - collect the sorted words
        System.out.println("\n3. Reorder");
        String[] sortedWords = new String[n];
        int index = 0;
        
        for (int i = 0; i < 27; i++) {
            for (String word : currentArray[i]) {
                sortedWords[index++] = word;
            }
        }
        
        System.out.println("Sorted words: " + Arrays.toString(sortedWords));
    }
}
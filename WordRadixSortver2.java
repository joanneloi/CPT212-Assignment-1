import java.util.Arrays;
import java.util.Scanner;

public class WordRadixSortver2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Initialize
        System.out.println("1. Initialize");
        
        // Input handling with validation
        String[] words;
        while (true) {
            System.out.println("Enter words separated by space:");
            String input = scanner.nextLine().trim();
            
            words = input.split("\\s+");
            words = Arrays.stream(words)
                    .filter(s -> !s.isEmpty())
                    .toArray(String[]::new);

            if (words.length > 0) break;
            System.out.println("Error: No words entered. Please try again.");
        }

        System.out.print("Example: ");
        printArray(words);
        
        // Get maximum length
        int maxLength = getMaxLength(words);
        
        // Initialize Array1 and Array2 (buckets for each letter a-z)
        String[][] array1 = new String[26][];
        String[][] array2 = new String[26][];
        
        // Initialize empty buckets for both arrays
        for (int i = 0; i < 26; i++) {
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
        
        // Place all words initially in array1 bucket 0
        for (String word : words) {
            array1[0] = addToArray(array1[0], word);
        }
        
        // Perform radix sort from right to left
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
                    
                    // Determine bucket index
                    int bucketIndex;
                    if (charPos < 0) {
                        bucketIndex = 0; // Place in first bucket if position doesn't exist
                    } else {
                        char ch = word.charAt(charPos);
                        // Check if the character is a lowercase letter
                        if (ch >= 'a' && ch <= 'z') {
                            bucketIndex = ch - 'a';
                        } else if (ch >= 'A' && ch <= 'Z') {
                            // Convert uppercase to lowercase for sorting
                            bucketIndex = Character.toLowerCase(ch) - 'a';
                        } else {
                            // For non-alphabetic characters, place in bucket 0
                            bucketIndex = 0;
                        }
                    }
                    
                    // Add the word to the appropriate bucket in the next array
                    nextArray[bucketIndex] = addToArray(nextArray[bucketIndex], word);
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

        scanner.close();
    }

    private static int getMaxLength(String[] array) {
        int maxLength = 0;
        for (String str : array) {
            maxLength = Math.max(maxLength, str.length());
        }
        return maxLength;
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
    
    // Method to display buckets in the required format
    private static void displayBuckets(String[][] buckets) {
        for (int i = 0; i < 26; i++) {
            char bucketLabel = (char)('a' + i);
            System.out.print("bucket " + bucketLabel + ": ");
            
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
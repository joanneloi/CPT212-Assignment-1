import java.util.Arrays;
import java.util.Scanner;

public class RadixSortComplexityAnalysis {
    // Counters for operations
    private static long digitSortCounter = 0;
    private static long wordSortCounter = 0;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Radix Sort Complexity Analysis");
        System.out.println("=============================");
        
        // Ask user which algorithm to analyze
        System.out.println("Which algorithm would you like to analyze?");
        System.out.println("1. Digit-Based Radix Sort");
        System.out.println("2. Word-Based Radix Sort");
        
        int choice = getIntInput(scanner, "Enter your choice (1-2): ", 1, 2);
        
        // Ask user for input sizes to test
        System.out.println("\nEnter the number of input sizes you want to test:");
        int numSizes = getIntInput(scanner, "Number of input sizes: ", 1, 10);
        
        int[] sizes = new int[numSizes];
        System.out.println("\nEnter each input size:");
        for (int i = 0; i < numSizes; i++) {
            sizes[i] = getIntInput(scanner, "Size " + (i+1) + ": ", 1, 10000);
        }
        
        // Arrays to store operation counts
        long[] operations = new long[numSizes];
        
        // Run the analysis based on user choice
        if (choice == 1) {
            System.out.println("\nAnalyzing Digit-Based Radix Sort...");
            for (int i = 0; i < numSizes; i++) {
                int n = sizes[i];
                String[] numbers = generateRandomNumbers(n);
                
                digitSortCounter = 0; // Reset counter
                radixSortDigits(numbers);
                operations[i] = digitSortCounter;
                
                System.out.println("Input size: " + n + ", Operations: " + digitSortCounter);
            }
            
            // Print data for plotting
            System.out.println("\nData for plotting:");
            System.out.println("Input Size, Digit Sort Operations");
            for (int i = 0; i < numSizes; i++) {
                System.out.println(sizes[i] + ", " + operations[i]);
            }
            analyzeComplexity(sizes, operations, "Digit-Based Radix Sort");
        } else {
            System.out.println("\nAnalyzing Word-Based Radix Sort...");
            for (int i = 0; i < numSizes; i++) {
                int n = sizes[i];
                String[] words = generateRandomWords(n);
                
                wordSortCounter = 0; // Reset counter
                radixSortWords(words);
                operations[i] = wordSortCounter;
                
                System.out.println("Input size: " + n + ", Operations: " + wordSortCounter);
            }
            
            // Print data for plotting
            System.out.println("\nData for plotting:");
            System.out.println("Input Size, Word Sort Operations");
            for (int i = 0; i < numSizes; i++) {
                System.out.println(sizes[i] + ", " + operations[i]);
            }
            analyzeComplexity(sizes, operations, "Word-Based Radix Sort");
        }
        
        scanner.close();
    }
    
    // Digit-based radix sort with operation counting
    private static void radixSortDigits(String[] numbers) {
        digitSortCounter++; // Assignment operation
        
        // Find the maximum length number to determine number of passes
        int maxLength = 0;
        digitSortCounter++; // Assignment operation
        
        for (String num : numbers) {
            digitSortCounter++; // Loop iteration
            if (num.length() > maxLength) {
                digitSortCounter++; // Comparison operation
                maxLength = num.length();
                digitSortCounter++; // Assignment operation
            }
        }
        digitSortCounter++; // Final loop condition check
        
        // Initialize Array1 and Array2 (buckets for each digit 0-9)
        String[][] array1 = new String[10][];
        String[][] array2 = new String[10][];
        digitSortCounter += 2; // Two array initializations
        
        // Initialize empty buckets for both arrays
        for (int i = 0; i < 10; i++) {
            digitSortCounter++; // Loop condition check
            array1[i] = new String[0];
            array2[i] = new String[0];
            digitSortCounter += 2; // Two array assignments
        }
        digitSortCounter++; // Final loop condition check
        
        // Place all numbers initially in array1 bucket 0
        for (String num : numbers) {
            digitSortCounter++; // Loop iteration
            array1[0] = addToArrayDigit(array1[0], num);
            digitSortCounter++; // Array assignment
        }
        digitSortCounter++; // Final loop condition check
        
        // Perform radix sort from right to left
        String[][] currentArray = array1;
        String[][] nextArray = array2;
        digitSortCounter += 2; // Two array assignments
        
        for (int pos = 1; pos <= maxLength; pos++) {
            digitSortCounter++; // Loop condition check
            
            // Clear the next array before each pass
            for (int i = 0; i < 10; i++) {
                digitSortCounter++; // Loop condition check
                nextArray[i] = new String[0];
                digitSortCounter++; // Array assignment
            }
            digitSortCounter++; // Final loop condition check
            
            // Process all numbers from current array in order
            for (int i = 0; i < 10; i++) {
                digitSortCounter++; // Loop condition check
                
                for (String num : currentArray[i]) {
                    digitSortCounter++; // Loop iteration
                    
                    // Calculate digit position from right
                    int digitPos = num.length() - pos;
                    digitSortCounter += 2; // Subtraction and assignment
                    
                    // Determine bucket index
                    int bucketIndex;
                    if (digitPos < 0) {
                        digitSortCounter++; // Comparison operation
                        bucketIndex = 0; // Place in first bucket if position doesn't exist
                        digitSortCounter++; // Assignment operation
                    } else {
                        digitSortCounter++; // Comparison operation (else branch)
                        char digitChar = num.charAt(digitPos);
                        bucketIndex = Character.getNumericValue(digitChar);
                        digitSortCounter += 3; // Character access, conversion, and assignment
                    }
                    
                    // Add the number to the appropriate bucket in the next array
                    nextArray[bucketIndex] = addToArrayDigit(nextArray[bucketIndex], num);
                    digitSortCounter++; // Array assignment
                }
                digitSortCounter++; // Final inner loop condition check
            }
            digitSortCounter++; // Final outer loop condition check
            
            // Swap the arrays for the next pass
            String[][] temp = currentArray;
            currentArray = nextArray;
            nextArray = temp;
            digitSortCounter += 3; // Three array assignments
        }
        digitSortCounter++; // Final loop condition check
    }
    
    // Word-based radix sort with operation counting
    private static void radixSortWords(String[] words) {
        wordSortCounter++; // Assignment operation
        
        // Find the maximum length word to determine number of passes
        int maxLength = 0;
        wordSortCounter++; // Assignment operation
        
        for (String word : words) {
            wordSortCounter++; // Loop iteration
            if (word.length() > maxLength) {
                wordSortCounter++; // Comparison operation
                maxLength = word.length();
                wordSortCounter++; // Assignment operation
            }
        }
        wordSortCounter++; // Final loop condition check
        
        // Initialize Array1 and Array2 (buckets for each letter a-z)
        String[][] array1 = new String[26][];
        String[][] array2 = new String[26][];
        wordSortCounter += 2; // Two array initializations
        
        // Initialize empty buckets for both arrays
        for (int i = 0; i < 26; i++) {
            wordSortCounter++; // Loop condition check
            array1[i] = new String[0];
            array2[i] = new String[0];
            wordSortCounter += 2; // Two array assignments
        }
        wordSortCounter++; // Final loop condition check
        
        // Place all words initially in array1 bucket 0
        for (String word : words) {
            wordSortCounter++; // Loop iteration
            array1[0] = addToArrayWord(array1[0], word);
            wordSortCounter++; // Array assignment
        }
        wordSortCounter++; // Final loop condition check
        
        // Perform radix sort from right to left
        String[][] currentArray = array1;
        String[][] nextArray = array2;
        wordSortCounter += 2; // Two array assignments
        
        for (int pos = 1; pos <= maxLength; pos++) {
            wordSortCounter++; // Loop condition check
            
            // Clear the next array before each pass
            for (int i = 0; i < 26; i++) {
                wordSortCounter++; // Loop condition check
                nextArray[i] = new String[0];
                wordSortCounter++; // Array assignment
            }
            wordSortCounter++; // Final loop condition check
            
            // Process all words from current array in order
            for (int i = 0; i < 26; i++) {
                wordSortCounter++; // Loop condition check
                
                for (String word : currentArray[i]) {
                    wordSortCounter++; // Loop iteration
                    
                    // Calculate character position from right
                    int charPos = word.length() - pos;
                    wordSortCounter += 2; // Subtraction and assignment
                    
                    // Determine bucket index
                    int bucketIndex;
                    if (charPos < 0) {
                        wordSortCounter++; // Comparison operation
                        bucketIndex = 0; // Place in first bucket if position doesn't exist
                        wordSortCounter++; // Assignment operation
                    } else {
                        wordSortCounter++; // Comparison operation (else branch)
                        char ch = word.charAt(charPos);
                        if (ch >= 'a' && ch <= 'z') {
                            wordSortCounter += 2; // Two comparison operations
                            bucketIndex = ch - 'a';
                        } else if (ch >= 'A' && ch <= 'Z') {
                            wordSortCounter += 2; // Two comparison operations
                            bucketIndex = Character.toLowerCase(ch) - 'a';
                        } else {
                            wordSortCounter += 2; // Two comparison operations
                            bucketIndex = 0;
                        }
                        wordSortCounter += 3; // Character access, calculation, and assignment
                    }
                    
                    // Add the word to the appropriate bucket in the next array
                    nextArray[bucketIndex] = addToArrayWord(nextArray[bucketIndex], word);
                    wordSortCounter++; // Array assignment
                }
                wordSortCounter++; // Final inner loop condition check
            }
            wordSortCounter++; // Final outer loop condition check
            
            // Swap the arrays for the next pass
            String[][] temp = currentArray;
            currentArray = nextArray;
            nextArray = temp;
            wordSortCounter += 3; // Three array assignments
        }
        wordSortCounter++; // Final loop condition check
        
        // Reorder - Create padded versions for alphabetical sorting
        String[] paddedWords = new String[words.length];
        String[] originalWords = new String[words.length];
        int index = 0;
        wordSortCounter += 3; // Three variable initializations
        
        // Collect all words from the final array
        for (int i = 0; i < 26; i++) {
            wordSortCounter++; // Loop condition check
            
            for (String word : currentArray[i]) {
                wordSortCounter++; // Loop iteration
                
                if (index < originalWords.length) {
                    wordSortCounter++; // Comparison operation
                    originalWords[index++] = word;
                    wordSortCounter += 2; // Array assignment and increment
                }
            }
            wordSortCounter++; // Final inner loop condition check
        }
        wordSortCounter++; // Final outer loop condition check
        
        // Create right-padded versions of the words
        for (int i = 0; i < originalWords.length; i++) {
            wordSortCounter++; // Loop condition check
            
            if (originalWords[i] != null) {
                wordSortCounter++; // Comparison operation
                paddedWords[i] = padRightWithSpaces(originalWords[i], maxLength);
                wordSortCounter++; // Array assignment
            }
        }
        wordSortCounter++; // Final loop condition check
        
        // Sort the padded words alphabetically (bubble sort)
        for (int i = 0; i < paddedWords.length - 1; i++) {
            wordSortCounter++; // Loop condition check
            
            for (int j = i + 1; j < paddedWords.length; j++) {
                wordSortCounter++; // Loop condition check
                
                if (paddedWords[i] != null && paddedWords[j] != null && 
                    paddedWords[i].compareTo(paddedWords[j]) > 0) {
                    wordSortCounter += 3; // Three comparison operations
                    
                    // Swap padded words
                    String tempPadded = paddedWords[i];
                    paddedWords[i] = paddedWords[j];
                    paddedWords[j] = tempPadded;
                    wordSortCounter += 3; // Three array assignments
                    
                    // Swap original words too
                    String tempOriginal = originalWords[i];
                    originalWords[i] = originalWords[j];
                    originalWords[j] = tempOriginal;
                    wordSortCounter += 3; // Three array assignments
                }
            }
            wordSortCounter++; // Final inner loop condition check
        }
        wordSortCounter++; // Final outer loop condition check
    }
    
    // Helper method to add a number to an array (for digit sort)
    private static String[] addToArrayDigit(String[] array, String num) {
        digitSortCounter++; // Method call
        
        String[] newArray = new String[array.length + 1];
        digitSortCounter += 2; // Array creation and length access
        
        System.arraycopy(array, 0, newArray, 0, array.length);
        digitSortCounter++; // System.arraycopy call
        
        newArray[array.length] = num;
        digitSortCounter += 2; // Array access and assignment
        
        return newArray;
    }
    
    // Helper method to add a word to an array (for word sort)
    private static String[] addToArrayWord(String[] array, String word) {
        wordSortCounter++; // Method call
        
        String[] newArray = new String[array.length + 1];
        wordSortCounter += 2; // Array creation and length access
        
        System.arraycopy(array, 0, newArray, 0, array.length);
        wordSortCounter++; // System.arraycopy call
        
        newArray[array.length] = word;
        wordSortCounter += 2; // Array access and assignment
        
        return newArray;
    }
    
    // Helper method to pad a word with spaces to the right
    private static String padRightWithSpaces(String word, int length) {
        wordSortCounter++; // Method call
        
        StringBuilder sb = new StringBuilder(word);
        wordSortCounter++; // StringBuilder creation
        
        for (int i = word.length(); i < length; i++) {
            wordSortCounter++; // Loop condition check
            sb.append(' ');
            wordSortCounter++; // Append operation
        }
        wordSortCounter++; // Final loop condition check
        
        return sb.toString();
    }
    
    // Generate random numbers with varying digit lengths
    private static String[] generateRandomNumbers(int n) {
        String[] numbers = new String[n];
        java.util.Random random = new java.util.Random();
        
        for (int i = 0; i < n; i++) {
            // Determine random digit length between 1 and 6
            int digitLength = random.nextInt(6) + 1;
            
            // Generate random number with the determined digit length
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < digitLength; j++) {
                // First digit shouldn't be 0 unless it's a single-digit number
                int digit;
                if (j == 0 && digitLength > 1) {
                    digit = random.nextInt(9) + 1; // 1-9
                } else {
                    digit = random.nextInt(10); // 0-9
                }
                sb.append(digit);
            }
            
            numbers[i] = sb.toString();
        }
        
        return numbers;
    }
    
    // Generate random words with varying lengths
    private static String[] generateRandomWords(int n) {
        String[] words = new String[n];
        java.util.Random random = new java.util.Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        
        for (int i = 0; i < n; i++) {
            // Determine random word length between 1 and 10
            int wordLength = random.nextInt(10) + 1;
            
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < wordLength; j++) {
                char c = alphabet.charAt(random.nextInt(alphabet.length()));
                sb.append(c);
            }
            
            words[i] = sb.toString();
        }
        
        return words;
    }
    
    // Helper method to get integer input with validation
    private static int getIntInput(Scanner scanner, String prompt, int min, int max) {
        int input;
        while (true) {
            System.out.print(prompt);
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
                if (input >= min && input <= max) {
                    break;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
        return input;
    }
    
    // Helper method to analyze complexity based on operation counts
    private static void analyzeComplexity(int[] sizes, long[] operations, String algorithmName) {
        System.out.println("\nComplexity Analysis for " + algorithmName + ":");
        
        // Check if growth is linear (O(n))
        boolean isLinear = true;
        double[] linearRatios = new double[sizes.length];
        
        for (int i = 0; i < sizes.length; i++) {
            linearRatios[i] = (double) operations[i] / sizes[i];
            System.out.println("Size: " + sizes[i] + ", Operations/n: " + linearRatios[i]);
        }
        
        // Check consistency of linear ratios
        if (sizes.length > 1) {
            double avgRatio = Arrays.stream(linearRatios).average().getAsDouble();
            for (double ratio : linearRatios) {
                if (Math.abs(ratio - avgRatio) / avgRatio > 0.3) { // Allow 30% deviation
                    isLinear = false;
                    break;
                }
            }
        }
        
        // Check if growth is quadratic (O(n²))
        boolean isQuadratic = true;
        double[] quadraticRatios = new double[sizes.length];
        
        for (int i = 0; i < sizes.length; i++) {
            quadraticRatios[i] = (double) operations[i] / (sizes[i] * sizes[i]);
            System.out.println("Size: " + sizes[i] + ", Operations/n²: " + quadraticRatios[i]);
        }
        
        // Check consistency of quadratic ratios
        if (sizes.length > 1) {
            double avgRatio = Arrays.stream(quadraticRatios).average().getAsDouble();
            for (double ratio : quadraticRatios) {
                if (Math.abs(ratio - avgRatio) / avgRatio > 0.3) { // Allow 30% deviation
                    isQuadratic = false;
                    break;
                }
            }
        }
        
        // Determine complexity
        System.out.println("\nBased on the analysis:");
        if (isLinear) {
            System.out.println("The time complexity of " + algorithmName + " appears to be O(n)");
        } else if (isQuadratic) {
            System.out.println("The time complexity of " + algorithmName + " appears to be O(n²)");
        } else {
            System.out.println("The time complexity of " + algorithmName + " is neither clearly O(n) nor O(n²)");
            System.out.println("Further analysis may be needed");
        }
    }
}
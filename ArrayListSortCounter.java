import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListSortCounter {
    static int counter = 0; // Counter for primitive operations
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] numbers;

        while (true) {
            System.out.println("Enter numbers separated by space: The numbers should be in same length");

            String input = scanner.nextLine().trim();
            counter += 3; // nextLine, trim, assignment

            numbers = input.split("\\s+");
            counter += 2; // split, assignment
            
            counter++; // comparison
            if (numbers.length > 0) {
                break;
            }

            System.out.println("Error: No numbers entered. Please try again.");
        }

        int maxLength = numbers[0].length();
        counter++; // assignment

        int count = numbers.length;
        counter++; //assignment

        String[] sorted = radixSort(numbers, maxLength, count);
        counter += 2; // method call + assignment

        System.out.println("\nFinal sorted result:");
        System.out.println(String.join(" ", sorted));

        scanner.close();
        System.out.println("Total primitive operations: " + counter);
    }

    private static String[] radixSort(String[] numbers, int maxLength, int count) {

        System.out.println("1. Initialization");

        ArrayList<ArrayList<String>> Array1 = new ArrayList<>();
        ArrayList<ArrayList<String>> Array2 = new ArrayList<>();
        counter += 2; // 2 initializations

        counter += 2; // assignment and loop compare
        for (int i = 0; i < 10; i++) {
            Array1.add(new ArrayList<>());
            Array2.add(new ArrayList<>());
            counter += 4; // loop compare, increment, add(), add()
        }

        System.out.println("2. Initialization");

        counter += 3; // assignment, arrithmetic, comparison
        for (int digitIndex = maxLength - 1; digitIndex >= 0; digitIndex--) {
            counter += 2; // compare, decrement

            counter += 2; // compare and subtraction
            if (digitIndex == maxLength - 1) {
                for (String num : numbers) {
                    counter++; //nested loop
                    int digit = Character.getNumericValue(num.charAt(digitIndex));
                    Array1.get(digit).add(num);
                    counter += 5; // assignment, getNumericValue, charAt, get, add
                }
            } else if ((maxLength - 1 - digitIndex) % 2 == 1) {
                counter += 4; // subtraction, subtraction, modulus, comparison

                counter += 2; // assignment, comparison
                for (int i = 0; i < 10; i++) {
                    counter += 2; // compare + increment

                    for (String num : Array1.get(i)) {
                        counter += 2; // nested loop + get operation
                        int digit = Character.getNumericValue(num.charAt(digitIndex));
                        Array2.get(digit).add(num);
                        counter += 5; // assignment, getNumericValue, charAt, get, add
                    }
                }

                for (ArrayList<String> bucket : Array1) {
                    counter++; // loop
                    bucket.clear();
                    counter++; // clear operation
                }
                

            } else {
                counter += 4; // subtraction, subtraction, modulus, comparison
                counter += 2; // assignment, comparison
                for (int i = 0; i < 10; i++) {
                    counter += 2; // compare + increment
                    
                    for (String num : Array2.get(i)) {
                        counter++; // nested loop
                        int digit = Character.getNumericValue(num.charAt(digitIndex));
                        Array1.get(digit).add(num);
                        counter += 5; // assignment, getNumericValue, charAt, get, add
                    }
                }
                for (ArrayList<String> bucket : Array2) {
                    counter++; // loop
                    bucket.clear();
                    counter++; // clear operation
                }
            }
        }

        System.out.println("3. Reorder");

        String[] finalArray = new String[count];
        counter++; // assignment

        int index = 0;
        counter++; // initialization

        counter += 3; // subtraction, modulus, comparison
        if ((maxLength - 1) % 2 == 0) {
            for (ArrayList<String> bucket : Array1) {
                counter++; // loop condition
                for (String num : bucket) {
                    counter++; // nested loop
                    finalArray[index++] = num;
                    counter += 2; //assignment, increment
                }
            }
        } else {
            for (ArrayList<String> bucket : Array2) {
                counter++; // loop condition
                for (String num : bucket) {
                    counter++; // nested loop
                    finalArray[index++] = num;
                    counter += 2; //assignment, increment
                }
            }
        }

        counter++; // return
        return finalArray;
    }
}

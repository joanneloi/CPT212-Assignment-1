import java.util.*;

public class sorting {
    // static int getMax(int[] arr) {
    //     int max = arr[0];
    //     for (int value : arr) {
    //         if (value > max) {
    //             max = value;
    //         }
    //     }
    //     return max;
    // }

    public static void main(String[] args){
        int[] arr = {275, 87, 426, 61, 409, 170, 677, 503};
        int n = arr.length;
        int counter = 3;          //the number of digits in the largest value
        ArrayList<Integer>[] Array1 = new ArrayList[10];
        ArrayList<Integer>[] Array2 = new ArrayList[10];
            
        // Initialize each bucket
        for (int i = 0; i < 10; i++) {
            Array1[i] = new ArrayList<>();
            Array2[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            int digit = arr[i] % 10;
            Array1[digit].add(arr[i]);
        }

        System.out.println("First Pass (by units digit):");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + ": ");
            for (int val : Array1[i]) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        for(int i = 0; i < 10; i++){
            for (int val : Array1[i]) {
                int tensDigit = (val / 10) % 10;
                Array2[tensDigit].add(val);
            }
        }

        System.out.println("Second Pass (by units digit):");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + ": ");
            for (int val : Array2[i]) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < 10; i++) {
            Array1[i].clear();
        }

        for(int i = 0; i < 10; i++){
            for (int val : Array2[i]) {
                int hundredsDigit = (val / 100) % 10;
                Array1[hundredsDigit].add(val);
            }
        }

        System.out.println("Third Pass (by units digit):");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + ": ");
            for (int val : Array1[i]) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
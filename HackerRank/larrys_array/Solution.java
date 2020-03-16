import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the larrysArray function below.
    static String larrysArray(int[] A) {
        //theory: if the number of swaps when doing insertion sort is odd, it's not possible. if it's even, it's possible.
        //I don't have a real proof, but the intuition was every array that *can't* be solved using rotations/3-swaps is because it
        //eventually ends up in a configuration which requires a 2-swap, (and a 2-swap can't be simulated with 3-swaps). A 3-swap
        //is simply equivalent to two 2-swaps. So if you can sort an array using an even number of 2-swaps, you can sort it with 
        //3-swaps. But if it requires an odd number of 2-swaps, it's not possible.
        return insertionSwaps(A) % 2 == 0 ? "YES" : "NO";
    }

    //problem restricts the input to a maximum of 1000 numbers, so the n^2 should be ok.
    static int insertionSwaps(int[] A){
        int retval = 0;
        while(true){
            int j = 0;
            for (; j < A.length-1; j++){
                if (A[j] > A[j+1]){
                    int tmp = A[j];
                    A[j] = A[j+1];
                    A[j+1] = tmp;
                    retval++;
                    break;
                }
            }
            if (j == A.length-1){
                return retval;
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] A = new int[n];

            String[] AItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int AItem = Integer.parseInt(AItems[i]);
                A[i] = AItem;
            }

            String result = larrysArray(A);

            System.out.println(result);
        }

        scanner.close();
    }
}


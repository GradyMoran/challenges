import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
        /*idea is to count the cycles present in the array. a cycle starts at a number in 
        an incorrect position. go to the index where it belongs, and that number will also
        be in an incorrect position. follow the cycle until the original number is reached. 
        each cycle of length k can be swapped back in k-1 moves. there may be many cycles. 
        will keep track of which cycles have been visited by marking the numbers as negative 
        as we go.
        */
        int swaps = 0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] < 1) continue;
            if (arr[i] != i+1){
                int cycleIndex = arr[i]-1;
                arr[i] *= -1;
                int cycleLength = 1;
                while (arr[cycleIndex] > 0){
                    arr[cycleIndex] *= -1;
                    cycleIndex = (arr[cycleIndex]*-1)-1;
                    cycleLength++;
                }
                swaps += (cycleLength -1);
            }
        }
        return swaps;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}


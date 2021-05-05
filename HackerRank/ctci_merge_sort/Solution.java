import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class PartialSortCount{
    long swaps;
    int[] arr;
    public PartialSortCount(long swaps, int[] arr){
        this.swaps = swaps;
        this.arr = arr;
    }
}

public class Solution {

    // Complete the countInversions function below.
    static long countInversions(int[] arr) {
        return countInversionsPSC(arr).swaps;
    }
    
    static PartialSortCount countInversionsPSC(int[] arr){
        //base case
        if (arr.length <= 1){
            PartialSortCount retval = new PartialSortCount(0, arr);
            return retval;
        }
        //else, sort subarrays, merge them, and add swap counts from subarrays and from merge process to swaps of retval, and return that
        int[] left = Arrays.copyOfRange(arr,0,arr.length/2);
        int[] right = Arrays.copyOfRange(arr,arr.length/2,arr.length);
        PartialSortCount leftPSC = countInversionsPSC(left);
        PartialSortCount rightPSC = countInversionsPSC(right);
        PartialSortCount retval = new PartialSortCount(leftPSC.swaps + rightPSC.swaps, new int[arr.length]);
        int left_counter = 0;
        int right_counter = 0;
        int retval_counter = 0;
        while (left_counter < leftPSC.arr.length &&
               right_counter < rightPSC.arr.length){
            //You do a series of "swaps" when taking an element from the right list and putting it on the sorted list part. The number of "swaps" completed when the element at the front of the right list is smaller is equal to the number of elements remaining on the left list.
            if (leftPSC.arr[left_counter] <= rightPSC.arr[right_counter]){
                retval.arr[retval_counter] = leftPSC.arr[left_counter];
                retval_counter++;
                left_counter++;
            } else {
                retval.arr[retval_counter] = rightPSC.arr[right_counter];
                retval.swaps += (leftPSC.arr.length - left_counter); //the element on the right list has to be "swapped" all the way past the elements on the left list
                retval_counter++;
                right_counter++;
            }
        }
        //copy remaining elements in the subarrays if necessary
        while (left_counter < leftPSC.arr.length) {
        	retval.arr[retval_counter] = leftPSC.arr[left_counter];
        	retval_counter++;
        	left_counter++;
        }
        while (right_counter < rightPSC.arr.length) {
        	retval.arr[retval_counter] = rightPSC.arr[right_counter];
        	retval_counter++;
        	right_counter++;
        }
        return retval;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            long result = countInversions(arr);

            //bufferedWriter.write(String.valueOf(result));
            //bufferedWriter.newLine();
            System.out.println(result);
        }

        //bufferedWriter.close();

        scanner.close();
    }
}

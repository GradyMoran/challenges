import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int maxSubsetSum(int[] arr) {
        //algorithm is to iterate through the input one by one, checking if it is better to add the element two behind the current or three behind in a running sum (or neither, in which case we continue holding the previous sum).
    	if (arr.length == 2) {
    		return max(new int[] {0, arr[0], arr[1]});
    	}
    	if (arr.length == 1) {
    		return arr[0] > 0 ? arr[0] : 0;
    	}
    	if (arr.length == 0) return 0;

    	int[] d = new int[arr.length];
        int lChoice;
    	int rChoice;
    	for (int i = 2; i < arr.length; i++) {
    		if (i-3 < 0) {//only with first round
    			lChoice = 0;
    		} else if (d[i-3] != 0) { //dynamic array initialized
    			lChoice = d[i-3];
    		} else { //still initializing dynamic array
    			lChoice = arr[i-3] > 0 ? arr[i-3] : 0;
    		}
    		if (d[i-2] != 0) {
    			rChoice = d[i-2];
    		} else {
    			rChoice = arr[i-2] > 0 ? arr[i-2] : 0;
    		}
    		int cur = arr[i] > 0 ? arr[i] : 0;
    		d[i] = max(new int[] {lChoice + cur, rChoice + cur});
    	}
    	return max(new int[] {d[d.length-1], d[d.length-2]});
    }

    //helper function just to allieviate the number of 3 way maxes I do above
    static int max(int[] arr) {
    	if (arr.length == 0) return 0;
    	int max = arr[0];
    	for (int i = 0; i < arr.length; i++) {
    		if (arr[i] > max) max = arr[i];
    	}
    	return max;
    }
        
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = maxSubsetSum(arr);

        System.out.println(res);

        scanner.close();
    }
}

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    /*
    This has a bug somewhere.
    */
    static long[] riddle(long[] arr) {
        Stack<Integer> s = new Stack<Integer>();
        int[] smallestIndexUntilToRight = new int[arr.length];
        int[] smallestIndexUntilToLeft = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
        	smallestIndexUntilToRight[i] = arr.length;
        	smallestIndexUntilToLeft[i] = 0;
        }
        for (int i = 0; i < arr.length; i++){
            if (s.empty()){
                s.push(i);
            } else if (arr[s.peek()] <= arr[i]){
                s.push(i);
            } else {
                while (!s.empty() && arr[s.peek()] > arr[i]){
                    smallestIndexUntilToRight[s.pop()] = i;
                }
                s.push(i);
            }
        }
        s.clear();
        for (int i = arr.length-1; i >= 0; i--) {
        	if (s.empty()) {
        		s.push(i);
        	} else if (arr[s.peek()] <= arr[i]){
                s.push(i);
            } else {
                while (!s.empty() && arr[s.peek()] > arr[i]){
                    smallestIndexUntilToLeft[s.pop()] = i+1;
                }
                s.push(i);
            }
        }
        HashMap<Long, Integer> numberToWindowSize = new HashMap<Long, Integer>();
        for (int i = 0; i < arr.length; i++) {
        	if (!numberToWindowSize.containsKey(arr[i])) {
        		numberToWindowSize.put(arr[i],  smallestIndexUntilToRight[i] - smallestIndexUntilToLeft[i]);
        	} else {
        		if (smallestIndexUntilToRight[i] - smallestIndexUntilToLeft[i] > 
        			numberToWindowSize.get(arr[i])) {
        			numberToWindowSize.put(arr[i],  smallestIndexUntilToRight[i] - smallestIndexUntilToLeft[i]);
        		}
        	}
        }
        HashMap<Integer, Long> windowSizeToNumber = new HashMap<Integer, Long>();
        for (long num : numberToWindowSize.keySet()) {
        	int wSize = numberToWindowSize.get(num);
        	if (!windowSizeToNumber.containsKey(wSize)) {
        		windowSizeToNumber.put(wSize, num);
        	} else {
        		if (windowSizeToNumber.get(wSize) < num) {
        			windowSizeToNumber.put(wSize, num);
        		}
        	}
        }
        
        long tmp = -1l;
        long[] retval = new long[arr.length];
        int counter = 0;
        for (int i = arr.length; i > 0; i--) {
        	if (windowSizeToNumber.containsKey(i)) tmp = windowSizeToNumber.get(i); 
        	retval[counter] = tmp;
        	counter++;
        }
        long[] reverse = new long[retval.length];
        for (int i = 0; i < retval.length; i++) {
        	reverse[i] = retval[retval.length-1-i];
        }
        
        return reverse;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long[] arr = new long[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long arrItem = Long.parseLong(arrItems[i]);
            arr[i] = arrItem;
        }

        long[] res = riddle(arr);

        for (int i = 0; i < res.length; i++) {
            //bufferedWriter.write(String.valueOf(res[i]));
            System.out.print("" + res[i]);

            if (i != res.length - 1) {
                //bufferedWriter.write(" ");
                System.out.print(" ");
            }
        }
        System.out.println();

        scanner.close();
    }
}


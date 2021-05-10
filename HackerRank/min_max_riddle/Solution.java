import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the riddle function below.
    static long[] riddle(long[] arr) {
        /* Algorithm is to create n priority queues, initially with each having one element.
           Add them all to a list, and among all the min elements in the priority queues, find the maximum, and put it in retval.
           For the next iteration, add one number to the right of each of the priority queues, and drop the rightmost one from the list. Then do the same min max finding, and add to retval again
           Terminate after there's only one queue left.
        */
        //Initialization 
/*        long[] retval = new long[arr.length];
        List<PriorityQueue<Long>> pqs = new ArrayList<PriorityQueue<Long>>();
        for (int i = 0; i < arr.length; i++){
            PriorityQueue<Long> tmp = new PriorityQueue<Long>();
            tmp.add(arr[i]);
            pqs.add(tmp);
        }

        //Main algorithm loop
        long tmp;
        long maxOfMins;
        for (int i = 0; i < arr.length && pqs.size() > 0; i++){
            maxOfMins = pqs.get(0).peek();
            for (int j = 0; j < pqs.size(); j++){
                tmp = pqs.get(j).peek();
                maxOfMins = tmp > maxOfMins ? tmp : maxOfMins;
            }
            retval[i] = maxOfMins;
            //recombine the priority queues to make the next largest window sizes and drop the last value
            for (int pqIndex = 0; pqIndex < pqs.size()-1; pqIndex++){
                pqs.get(pqIndex).add(arr[pqIndex+i+1]);
            }
            pqs.remove(pqs.size()-1);
        }
        return retval;*/
        /*
        Iterate through the array left to right. As long as value is increasing, add indices to a stack. Create an array leftToRight, which is to keep track of when each index value finally wasn't the minimum. After iterating through once leftToRight should contain indices that tell from each index the closest index to the right which is smaller than the current. So when you hit a decrease from the previous value, pop indices off the stack until you hit one that's smaller than the current, and for each of those indices in leftToRight put the current index.
        Maybe make another pass of leftToRight and fill with final index if initial value is still remaining or something.
        Do the same from rightToLeft.
        Then iterate through both lists at the same time, adding the sizes of the windows to the left side and to the right side of each number, and put in a map like number->window size.
        Flip the map so it goes from window size -> number, and if two numbers have the same window size, should take larger one
        */
        Stack<Integer> s = new Stack<Integer>();
        int[] smallestIndexUntilToRight = new int[arr.length];
        int[] smallestIndexUntilToLeft = new int[arr.length];
        for (int i = 0; i < arr.length; i++){
            if (s.empty()){
                s.push(i);
            } else if (arr[s.peek()] <= arr[i]){
                s.push(i);
            } else {
                while (arr[s.peek()] > arr[i]){
                    smallestIndexUntilToRight[s.pop()] = i;
                }
                s.push(i);
            }
        }
        return new long[]{};
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


import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int[] absolutePermutation(int n, int k) {
        //for each index, there are only two (at most) numbers available for it: i+k and i-k. To create the minimum
        //permutation, need to choose the smallest of those two while iterating over the solution from left to right.
        Set<Integer> nums = new HashSet<Integer>();
        for (int i = 1; i <= n; i++){
            nums.add(i);
        }
        int[] retval = new int[n];
        //take the smaller of the two possible numbers for each index, if it's available. if neither's available, it's not possible.
        for (int i = 1; i <= n; i++){
            int smaller_possible = i+k;
            int larger_possible = i-k;
            if (smaller_possible > larger_possible) {
                int tmp = smaller_possible;
                smaller_possible = larger_possible;
                larger_possible = tmp;
            }
            if (nums.contains(smaller_possible)){
                nums.remove(smaller_possible);
                retval[i-1] = smaller_possible;
            }
            else if (nums.contains(larger_possible)){
                nums.remove(larger_possible);
                retval[i-1] = larger_possible;
            }
            else {
                return (new int[]{-1});
            }
               
        }  
        return retval;          
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nk = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nk[0]);

            int k = Integer.parseInt(nk[1]);

            int[] result = absolutePermutation(n, k);

            for (int i = 0; i < result.length; i++) {
                //bufferedWriter.write(String.valueOf(result[i]));
                System.out.print(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    //bufferedWriter.write(" ");
                    System.out.print(" ");
                }
            }

            //bufferedWriter.newLine();
            System.out.println();
        }

        //bufferedWriter.close();

        scanner.close();
    }
}


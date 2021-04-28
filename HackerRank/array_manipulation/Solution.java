import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {
        //I don't know why they're called queries, they're modification operations.
        //Anyways, the answer will be something like change the format of the input queries so it's just a 1d array of 
        //[(total K increases from query starts at index X) - (total K decreases from query ends at index X)]
        //(except for space's sake we don't need to keep the array, since we're only walking it one time, can just
        //remember the max value)
        //k_prev = 0
        //max_k = 0
        //for i = 0 to i = n
        //  k_adds = get all segments opening at i, and add the k value of them together
        //  k_subs = get all segments ending at i, and add the k values together
        //  k_delta = k_adds - k_subs
        //  k_pcur = k_prev + k_delta
        //  if k_cur > max_k: max_k = k_cur
        //  k_prev = k_cur
        //return max_k

        //O(n + q)
        
        HashMap<Long, Long> k_increase_at_i = new HashMap<Long, Long>();
        HashMap<Long, Long> k_decrease_at_i = new HashMap<Long, Long>();
        
        for (int i = 0; i < queries.length; i++){
            long opening = queries[i][0];
            long closing = queries[i][1];
            long k = queries[i][2];
            if (k_increase_at_i.containsKey(opening)){
                k_increase_at_i.put(opening, k_increase_at_i.get(opening)+k);
            } else {
                k_increase_at_i.put(opening, k);
            }
            if (k_decrease_at_i.containsKey(closing)){
                k_decrease_at_i.put(closing, k_decrease_at_i.get(closing)+k);
            } else {
                k_decrease_at_i.put(closing, k);
            }
        }

        long k_prev = 0;
        long max_k = 0;
        for (long i = 0; i < n; i++){
            long k_adds = 0;
            long k_subs = 0;
            if (k_increase_at_i.containsKey(i+1)){
                k_adds = k_increase_at_i.get(i+1);
            }
            if (k_decrease_at_i.containsKey(i)){
                k_subs = k_decrease_at_i.get(i);
            }
            long k_delta = k_adds - k_subs;
            long k_cur = k_prev + k_delta;
            if (k_cur > max_k) max_k = k_cur;
            k_prev = k_cur;
        }
        return max_k; 
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] queries = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        long result = arrayManipulation(n, queries);

        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();
        System.out.println(result);

        //bufferedWriter.close();

        scanner.close();
    }
}


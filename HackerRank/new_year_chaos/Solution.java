import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
        Set<Integer> bribedNums = new HashSet<Integer>();
        Set<Integer> seen = new HashSet<Integer>();
        int bribes = 0;
        for (int i = 0; i < q.length; i++){
            seen.add(q[i]);
            int expected = i+1;
            if (bribedNums.contains(q[i])) bribedNums.remove(q[i]);
            for (int j = expected; j < q[i]; j++){ //add the skipped numbers
                if (!seen.contains(j)) bribedNums.add(j);
            }
            for (int tmp : bribedNums){
                if (q[i] > tmp) bribes++;
            }
            if(bribedNums.size() > 2){
                System.out.println("Too chaotic");
                return;
            }
        }
        System.out.println(bribes);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }
}


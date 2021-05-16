import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the triplets function below.
    static long triplets(int[] a, int[] b, int[] c) {
        /*
        Suspicious that this is a "search" problem but the most obvious solution doesn't use a search...
        Anyways, it's p in a, q in b, and r in c. count num of triplets where q >= p and q >= r.

        alg:
        sort and eliminate duplicates (within same array) for a, b, and c
        starting with q = largest element in b, find largest p and r <= to that q, and note index #s.
        add to retval the product of the (index+1)s of p and r (because there are that many combinations of p and r values for the chosen q)
        then decrease q to the next value and repeat
        */
        long retval = 0;

        //sort and eliminate duplicates
        Arrays.sort(a);
        Arrays.sort(b);
        Arrays.sort(c);

        List<Integer> newA = new ArrayList<Integer>();
        List<Integer> newB = new ArrayList<Integer>();
        List<Integer> newC = new ArrayList<Integer>();

        int prevElem = a[0]-1;
        for (int i = 0; i < a.length; i++){
            if (a[i] != prevElem){
                newA.add(a[i]);
                prevElem = a[i];
            }
        }
        prevElem = b[0]-1;
        for (int i = 0; i < b.length; i++){
            if (b[i] != prevElem){
                newB.add(b[i]);
                prevElem = b[i];
            }
        }
        prevElem = c[0]-1;
        for (int i = 0; i < c.length; i++){
            if (c[i] != prevElem){
                newC.add(c[i]);
                prevElem = c[i];
            }
        }

        //count the number of combinations of p and r values <= current q
        int a_i = newA.size()-1;
        int c_i = newC.size()-1;
        int q;
        for (int b_i = newB.size()-1; b_i >= 0; b_i--){
            q = newB.get(b_i);
            while(a_i >= 0 && newA.get(a_i) > q) a_i--;
            while(c_i >= 0 && newC.get(c_i) > q) c_i--;
            retval += ((long) a_i+1)*((long) c_i+1);
        }
        return retval;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] lenaLenbLenc = scanner.nextLine().split(" ");

        int lena = Integer.parseInt(lenaLenbLenc[0]);

        int lenb = Integer.parseInt(lenaLenbLenc[1]);

        int lenc = Integer.parseInt(lenaLenbLenc[2]);

        int[] arra = new int[lena];

        String[] arraItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lena; i++) {
            int arraItem = Integer.parseInt(arraItems[i]);
            arra[i] = arraItem;
        }

        int[] arrb = new int[lenb];

        String[] arrbItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenb; i++) {
            int arrbItem = Integer.parseInt(arrbItems[i]);
            arrb[i] = arrbItem;
        }

        int[] arrc = new int[lenc];

        String[] arrcItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenc; i++) {
            int arrcItem = Integer.parseInt(arrcItems[i]);
            arrc[i] = arrcItem;
        }

        long ans = triplets(arra, arrb, arrc);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}


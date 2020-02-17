import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the fibonacciModified function below.
    static String fibonacciModified(int t1, int t2, int n) {
        BigInteger t_cur = BigInteger.ZERO;
        BigInteger t1b = new BigInteger("" + t1);
        BigInteger t2b = new BigInteger("" + t2);
        for (int cur = 3; cur <= n; cur++){
            t_cur = t1b.add(t2b.pow(2));
            t1b = t2b;
            t2b = t_cur;
        }
        return t_cur.toString();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] t1T2n = scanner.nextLine().split(" ");

        int t1 = Integer.parseInt(t1T2n[0]);

        int t2 = Integer.parseInt(t1T2n[1]);

        int n = Integer.parseInt(t1T2n[2]);

        String result = fibonacciModified(t1, t2, n);

        bufferedWriter.write(result);
        
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}


import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'commonChild' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING s1
     *  2. STRING s2
     */

    public static int commonChild(String s1, String s2) {
        /*
        This is the longest subsequence problem.
        Use dynamic programming solution identified by https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
        */
        int[][] lcs = new int[s1.length()+1][s2.length()+1];
        for (int i = 0; i < s1.length()+1; i++){
            lcs[i][0] = 0;
        }
        for (int i = 0; i < s2.length()+1; i++){
            lcs[0][i] = 0;
        }
        for (int r = 1; r < s1.length()+1; r++){
            for (int c = 1; c < s2.length()+1; c++){
                if (s1.charAt(r-1) == s2.charAt(c-1)){
                    lcs[r][c] = lcs[r-1][c-1]+1;
                } else {
                    lcs[r][c] = lcs[r-1][c] > lcs[r][c-1] ? lcs[r-1][c] : lcs[r][c-1];
                }
            }
        }
        return lcs[s1.length()][s2.length()];
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String s1 = bufferedReader.readLine();

        String s2 = bufferedReader.readLine();

        int result = Result.commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}


import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the biggerIsGreater function below.

    //a string is maximal if the ascii values decrease monotonically

    //find rightmost letter sequence where the ith letter is less than the i+1th letter, replace it with the next largest letter in the word with index > i, and then sort the remaining letters in monotonically increasing order.
    static String biggerIsGreater(String w) {
        int i = w.length()-1;
        for (; i > 0; i--){
            if (w.charAt(i) > w.charAt(i-1)){
                break;
            }
        }
        if (i == 0) return "no answer";
        //i-1th letter needs to be replaced with the smallest letter greater than it in the part of the string to the right of it.
        int tmp = i;
        int nextGreatestCharIndex = i;
        for (; tmp < w.length(); tmp++){
            if (w.charAt(tmp) < w.charAt(nextGreatestCharIndex) && w.charAt(tmp) > w.charAt(i-1)){
                nextGreatestCharIndex = tmp;
            }
        }
        char[] rightPart = w.substring(i, w.length()).toCharArray();
        rightPart[nextGreatestCharIndex - i] = w.charAt(i-1); //sub out the one we swapped
        StringBuilder sb = new StringBuilder();
        sb.append(w.substring(0, i-1));
        sb.append("" + w.charAt(nextGreatestCharIndex));
        Arrays.sort(rightPart);
        sb.append(new String(rightPart));
        return sb.toString();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int T = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int TItr = 0; TItr < T; TItr++) {
            String w = scanner.nextLine();

            String result = biggerIsGreater(w);

            System.out.println(result);
        }

        scanner.close();
    }
}


import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the timeInWords function below.
    static String timeInWords(int h, int m) {
        //this problem is simple, can we do it in one line?
        return (m == 0 ? numToString(h) + " o' clock" : 
               (m == 1 ? "one minute past " + numToString(h) :
               (m == 15 ? "quarter past " + numToString(h) :
               (m == 30 ? "half past " + numToString(h) :
               (m == 45 ? "quarter to " + numToString((h%12)+1) :
               (m == 59 ? "one minute to " + numToString((h%12)+1) :
               (m < 30 ? numToString(m) + " minutes past " + numToString(h) :
               numToString(60-m) + " minutes to " + numToString((h%12)+1))))))));

    }

    //only for range 0 - 29
    static String numToString(int n) {
        if (n < 0) return "";
        String[] numbers = {"zero",
                            "one",
                            "two",
                            "three",
                            "four",
                            "five",
                            "six",
                            "seven",
                            "eight",
                            "nine",
                            "ten",
                            "eleven",
                            "twelve",
                            "thirteen",
                            "fourteen",
                            "fifteen",
                            "sixteen",
                            "seventeen",
                            "eighteen",
                            "nineteen",
                            "twenty"};
        if (n <= 20) return numbers[n];
        return "twenty " + numToString(n-20);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int h = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String result = timeInWords(h, m);

        System.out.println(result);

        scanner.close();
    }
}


import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the encryption function below.
    static String encryption(String s) {
        s = s.replaceAll(" ", "");
        int r = (int) Math.sqrt(s.length());
        int c = r;
        while (c*r < s.length()){
            if (c - r > 0){ //Biased towards fewer rows than columns
                r++;
            } else {
                c++;
            }
        }

        char[][] block = new char[r][c];
        for (int i = 0; i < s.length(); i++){
            block[i/c][i%c] = s.charAt(i);
        }
        String retval = "";
        for (int i = 0; i < c; i++){
            for (int j = 0; j < r; j++){
                retval = retval + block[j][i];
            }
            retval = retval + " ";
        }
        return retval.trim();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String s = scanner.nextLine();

        String result = encryption(s);

        System.out.println(result);

        scanner.close();
    }
}


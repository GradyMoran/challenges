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
     * Complete the 'isBalanced' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String isBalanced(String s) {
    // Write your code here
        Stack<Character> st = new Stack<Character>();
        for (int i = 0; i < s.length(); i++){
            Character c = s.charAt(i);
            if (c == '{' || c == '(' || c == '['){
                st.push(c);
            } else if (st.size() == 0) {
                return "NO";
            } else if (c == '}' && st.peek() == '{' ||
                       c == ')' && st.peek() == '(' ||
                       c == ']' && st.peek() == '['){
                st.pop();
            } else {
                return "NO";
            }
        }
        return st.size() == 0 ? "YES" : "NO";
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String s = bufferedReader.readLine();

                String result = Result.isBalanced(s);

                System.out.println(result);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}


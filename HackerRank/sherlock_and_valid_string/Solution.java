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
     * Complete the 'isValid' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String isValid(String s) {
        //generate frequency counts per letter
        HashMap<Character, Integer> freqCounts = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++){
            if (freqCounts.containsKey(s.charAt(i))){
                freqCounts.put(s.charAt(i), freqCounts.get(s.charAt(i))+1);
            } else {
                freqCounts.put(s.charAt(i), 1);
            }
        }

        //generate freq counts per freq count, meaning how many different letters have frequency 1, frequency 2, etc
        HashMap<Integer, Integer> freqCountCounts = new HashMap<Integer, Integer>();
        for (Integer freq: freqCounts.values()){
            if (freqCountCounts.containsKey(freq)){
                freqCountCounts.put(freq, freqCountCounts.get(freq)+1);
            } else {
                freqCountCounts.put(freq, 1);
            }
        }

        if (freqCountCounts.size() <= 1) return "YES";
        if (freqCountCounts.size() >= 3) return "NO";
        //if there are two different letter frequencies in the string, it can only be turned into a sherlock string if
        //a) one of the frequencies is '1', and the other frequency occurring is > 1
        //b) the letter frequency appearing only once is one higher than the frequency for all the other letters
        Integer[] freqKeys = freqCountCounts.keySet().toArray(new Integer[0]);
        int mainLetterFreq = freqKeys[0];
        int changeFreq = freqKeys[1];
        if (freqCountCounts.get(mainLetterFreq) < freqCountCounts.get(changeFreq)){
            int tmp = mainLetterFreq;
            mainLetterFreq = changeFreq;
            changeFreq = tmp;
        }
        if (freqCountCounts.get(changeFreq) > 1) return "NO";
        if (changeFreq == 1) return "YES";
        if (changeFreq == mainLetterFreq+1) return "YES";
        return "NO";
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String s = bufferedReader.readLine();

        String result = Result.isValid(s);

        System.out.println(result);

        bufferedReader.close();
    }
}


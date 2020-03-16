import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    /*
    There are n^2 substrings of a string that can be iterated through in a nested for loop pretty simply.
    For each of those substrings we can form a hashmap<char, freq> (frequency counts of all characters
        in that substring). 
    This inner step will make it n^3. That might be ok, since the problem definition says max string length
        is 100.
    You can make it n^2 again by instead of regenerating the whole freqCounts for every substring, you 
        adjust the freqCounts of the previous substring (amortized constant)

    Edit: n^3 works fast enough for these input sizes
    
    */

    // Complete the sherlockAndAnagrams function below.
    static BigInteger sherlockAndAnagrams(String s) {
        //algorithm is to count how many times a particular character frequency set appears among all substrings of s.
        //(two substrings are anagrams == two substrings have the same character frequencies)
        //how many times that particular character frequency occurs is the size of the "anagram group"
        HashMap<HashMap<Character, Integer>, Integer> allFreqCounts = new HashMap<HashMap<Character, Integer>, Integer>();
        BigInteger retval = BigInteger.ZERO;
        for (int i = 0; i < s.length(); i++){
            for (int j = i+1; j <= s.length(); j++){
                String tmpString = s.substring(i, j);
                HashMap<Character, Integer> tmp = freqCounts(tmpString);
                if (allFreqCounts.containsKey(tmp)){
                    allFreqCounts.put(tmp, allFreqCounts.get(tmp)+1);
                } else {
                    allFreqCounts.put(tmp, 1);
                }
            }
        }

        //the wording of the problem wants us to find how many anagram pairs there are. which is equal to
        //size of the anagram group (freq) choose 2.
        for (Integer freq : allFreqCounts.values()){
            retval = retval.add(nChooseK(freq, 2));
        }
        return retval;
    }

    public static HashMap<Character, Integer> freqCounts(String s){
        HashMap<Character, Integer> retval = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++){
            Character c = s.charAt(i);
            if (!retval.containsKey(c)){
                retval.put(c, 1);
            } else {
                retval.put(c, retval.get(c)+1);
            }
        }
        return retval;
    }

    public static BigInteger nChooseK(int n, int k){
        return fact(n).divide((fact(k).multiply(fact(n-k))));
    }

    public static BigInteger fact(int n){
        return BIfact(new BigInteger("" + n));
    }

    public static BigInteger BIfact(BigInteger n){
        if (n.compareTo(BigInteger.ONE) <= 0) return BigInteger.ONE;
        return n.multiply(BIfact(n.subtract(BigInteger.ONE)));
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            BigInteger result = sherlockAndAnagrams(s);

            //bufferedWriter.write(String.valueOf(result));
            //bufferedWriter.newLine();
            System.out.println(result);
        }

        //bufferedWriter.close();

        scanner.close();
    }
}


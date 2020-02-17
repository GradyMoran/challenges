import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'nonDivisibleSubset' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY s
     */

    public static int nonDivisibleSubset(int k, List<Integer> s) {
    // Write your code here
    /*
    So if k = 10, then you can include:
    only ONE of 1 or 9 (because when anything from the mod 1 group is added to anything from the mod 9 group, it creates something which is divisible by 10, which means the mod 1 group cannot be present in the end solution if the mod 9 group is., so just pick the bigger of the two?)
    only ONE of 2 or 8
    ...
    NOT 5 (or maybe you can include one out of them?)
    
    That seems like the correct solution. For all pairs x<k, y<k where x+y = k (there should be roughly k/2 such pairs. if k = 5, the mod groups are 0, 1, 2, 3, 4 and you can include 1 and 3, 1 and 2, 2 and 3, or 2 and 4, plus a single number from the group that is mod 0, and a single number from the group k/2, if k is even. of course need to check those groups are not zero in size)
    */

        if (k == 1) return 1; //special case

        //Preprocessing mod frequency counts
        Map<Integer,Integer> modFrequencies = new HashMap<Integer,Integer>();
        for (int i = 0; i < k; i++){
            modFrequencies.put(i, 0);
        }
        for (int i = 0; i < s.size(); i++){
            int oldFreq = modFrequencies.get(s.get(i) % k);
            modFrequencies.put(s.get(i) % k, oldFreq+1);
        }

        int answer = 0;
        for (int i = 1; i < k/2; i++){
            int lower = modFrequencies.get(i);
            int higher = modFrequencies.get(k-i);
            answer += lower > higher ? lower : higher;
        }

        //can include a single number from the group that is a direct multiple of k (because sums of 2 numbers that are a multiple of k which include one number that is a multiple of k can only have the sum itself be a multiple of k if the other number is a multiple of k too. so we are safe to include one number from this group, given such a number exists in the input)
        if (modFrequencies.get(0) > 1) answer++;
        //if k is even, we can only include one number from the group that is mod (k/2), because anything from that group will produce a multiple of k if added to anything else from that group. 
        if (k % 2 == 0 && modFrequencies.get(k/2) > 1){
            answer++;
        } else { //if k is odd, then we missed the final iteration on the input in the main answer+= loop above, so need to add max of the two middle frequencies. but if k = 1, this doesn't apply?
            int lower = k/2 > 0 ? modFrequencies.get(k/2) : 0;
            int higher = modFrequencies.containsKey(k/2 + 1) ? modFrequencies.get(k/2 + 1) : 0;
            answer += lower > higher ? lower : higher;
        }
        return answer;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        String[] sTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> s = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int sItem = Integer.parseInt(sTemp[i]);
            s.add(sItem);
        }

        int result = Result.nonDivisibleSubset(k, s);

        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();
        System.out.println("" + result);

        bufferedReader.close();
    }
}


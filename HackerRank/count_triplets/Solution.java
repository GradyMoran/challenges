import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        //Get a frequency count of the input
        //Then iterate through the input one by one
        //checking if arr[i]*r^1 and arr[i]*r^2 are both present (using the frequency count hash)
        //if they are present then add the product of their frequencies (to represent different combinatinos) to the retval

        //update: ok, need to do it differently to enforce that the triplet is in order. Will walk it once and do the input_freqs as planned, but refer to this freq count as input_freqs_right, and the main walk will be looking for the middle number in the triple. We will remove entries from input_freqs_right and add to input_freqs_left as we iterate through the input a second time. 
        HashMap<Long,Long> input_freqs = new HashMap<Long,Long>();
        long max = 0;
        for (int i = 0; i < arr.size(); i++){
            long cur_num = arr.get(i);
            if (input_freqs.containsKey(cur_num)){
                input_freqs.put(cur_num, input_freqs.get(cur_num)+1);
            } else {
                input_freqs.put(cur_num, ((long) 1));
            }
            if (cur_num > max) max = cur_num;
        }

        long retval = 0;
        //uh, special case if r = 1, then need to avoid picking itself 3 times.
        //so instead of multiplying the different counts of the number, will do n choose 3 for
        //each number that appears in the input.
        if (r == 1){
            for (long cur_num : input_freqs.keySet()){
                retval += nChooseK(input_freqs.get(cur_num), 3).longValue();
            }
            return retval;
        }
        //avoid double counting by only looking for powers higher than the current num
        //bug: numbers in triplet have to appear in the input in that order. 
        for (long cur_num : input_freqs.keySet()){
            long pow_1 = cur_num*((long) Math.pow(r,1));
            long pow_2 = cur_num*((long) Math.pow(r,2));
            if (input_freqs.containsKey(pow_1) && input_freqs.containsKey(pow_2)){
                retval += (input_freqs.get(cur_num) * input_freqs.get(pow_1) * input_freqs.get(pow_2));
            }
        }
        return retval;

    }

    public static BigInteger nChooseK(long n, long k){
        return fact(n).divide((fact(k).multiply(fact(n-k))));
    }

    public static BigInteger fact(long n){
        BigInteger retval = BigInteger.ONE;
        for (long i = 2; i <= n; i++){
            retval = retval.multiply(BigInteger.valueOf(i));
        }
        return retval;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
       //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        String[] arrItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Long> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            long arrItem = Long.parseLong(arrItems[i]);
            arr.add(arrItem);
        }

        long ans = countTriplets(arr, r);

        //bufferedWriter.write(String.valueOf(ans));
        //bufferedWriter.newLine();
        System.out.println(ans);

        bufferedReader.close();
        //bufferedWriter.close();
    }
}


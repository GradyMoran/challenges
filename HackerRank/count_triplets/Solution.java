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
        HashMap<Long,Long> input_freqs_right = new HashMap<Long,Long>();
        for (int i = 0; i < arr.size(); i++){
            long cur_num = arr.get(i);
            if (input_freqs_right.containsKey(cur_num)){
                input_freqs_right.put(cur_num, input_freqs_right.get(cur_num)+1);
            } else {
                input_freqs_right.put(cur_num, ((long) 1));
            }
        }

        long retval = 0;
        //uh, special case if r = 1, then need to avoid picking itself 3 times.
        //so instead of multiplying the different counts of the number, will do n choose 3 for
        //each number that appears in the input.
//        if (r == 1){
//            for (long cur_num : input_freqs.keySet()){
//                retval += nChooseK(input_freqs.get(cur_num), 3).longValue();
//            }
//            return retval;
//        }
        HashMap<Long,Long> input_freqs_left = new HashMap<Long,Long>();
        for (int i = 0; i < arr.size(); i++){
            //decrease count for this element in input_freqs_right by one
            long cur_num = arr.get(i);
            input_freqs_right.put(cur_num, input_freqs_right.get(cur_num)-1L);
            if (cur_num % r == 0) {
                if (input_freqs_left.containsKey(cur_num/r) && input_freqs_right.containsKey(cur_num*r)){
                    retval += (input_freqs_left.get(cur_num/r) * input_freqs_right.get(cur_num*r));
                }
            }
            if (input_freqs_left.containsKey(cur_num)) {
                input_freqs_left.put(cur_num, input_freqs_left.get(cur_num)+1L);
            } else {
                input_freqs_left.put(cur_num, 1L);
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


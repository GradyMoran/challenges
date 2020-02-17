import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class e50 { 
    public static Set<Integer> composites;
    public static void main(String[] args){
        composites = new HashSet<Integer>();
        composites.add(1);
        long MAX_CHECK = 1000000; //record primes up to this value
        
        //sieve
        for (int i = 2; i <= Math.sqrt(MAX_CHECK); i++){
            if (!composites.contains(i)){
                for (int j = i*2; j < MAX_CHECK; j+=i){
                    composites.add(j);
                }
            }
        }
        
        ArrayList<Integer> primes = new ArrayList<Integer>();
        for (int i = 2; i < 1000000; i++){
            if (!composites.contains(i)) primes.add(i);
        }
        
        //main alg loop
        /* algorithm: start at longest sequence of consecutive primes that add to less than 1M.
         * then look at smaller and smaller sequences:
         *     with each sequence, "shift up" (i.e. 2 3 5 7 would go to 3 5 7 11) until greater than 1M.
         *     the first time a prime is found by one of these sequences is the answer.
         */
        int sum = 0, start, length;
        outer:
        for (length = 547; length > 0; length--){ //547 is largest # of consecutive primes that add to < 1000000
            start = 0;//index of first prime to include in sum
            for (start = 0;; start++){
                //get sum of $length primes from $start to $start+$length
                sum = 0;
                for (int i = start; i < start+length; i++){
                    sum+=primes.get(i);
                }
                if (sum > 1000000) continue outer;
                if (!composites.contains(sum)){
                    System.out.println("winner: " + sum + " number of primes: " + length);
                    System.exit(0);
                }
            }
        }
        System.out.println("oopsie");
    }
}
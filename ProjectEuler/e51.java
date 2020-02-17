import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class e51 {
    public static void main(String[] args){
        Set<Integer> composites = new HashSet<Integer>();
        Set<Integer> primes = new HashSet<Integer>();
        composites.add(1);
        long MAX_CHECK = 1000000; //record primes up to this value
        
        //sieve
        for (int i = 2; i <= Math.sqrt(MAX_CHECK); i++){
            if (!composites.contains(i)){
                primes.add(i);
                for (int j = i*2; j < MAX_CHECK; j+=i){
                    composites.add(j);
                }
            }
        }
        
        /*Set<Integer> primes = new HashSet<Integer>();
        for (int i = 2; i < MAX_CHECK; i++){
            if (!composites.contains(i)) primes.add(i);
        }*/

        for (Integer p : primes){
            for (int n = 1; n < Math.log10(p)+1; n++){ //number of digits to replace with d
                for (int d = 0; d < 10; d++){ //digit to replace
                    if (numPrimes(p, n, d) == 8) System.out.println(p);
                }
            }
        }
    }
    
    public static int numPrimes(int p, int numReplace, int digit){
        String p_string = Integer.toString(p);
        int retval = 1;
        //?
        //possibly: don't check if you are trying to replace a digit that's lower than you're replacing it (which would indicate you already checked)
        return retval;
    }
}

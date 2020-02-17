import java.util.*;
public class e47 {
    public static Set<Long> composites;
    public static void main(String[] args){
        composites = new HashSet<Long>();
        long MAX_CHECK = 10000L; //record primes up to this value
        
        //sieve
        for (Long i = 2l; i <= Math.sqrt(MAX_CHECK); i++){
            if (!composites.contains(i)){
                for (Long j = i*2; j < MAX_CHECK; j+=i){
                    composites.add(j);
                }
            }
        }
        
        int seq = 0;
        for (long i = 2; i < 10000000; i++){
            if (num_pf(i) == 4) seq++;
            else seq = 0;
            if (seq == 4) {
                System.out.println((i-3) + " " + (i-2) + " " + (i-1) + " " + i);
                System.exit(0);
            }
        }
    }
    
    public static int num_pf(long n){
        int pow = 0, count = 0;
        long rem = n, p = 2; //p: current prime to check; pow: power of p; rem: remainder of n after being divided by previously checked p values
	//Algorithm is basically check whether n is divisible by each prime in increasing order. When n is divisible by that prime, increase power of that prime and divide by that prime again until no longer divisble. Move onto higher primes until finished.
	while (p <= rem){
		while (rem % p == 0) {
			rem = rem/p;
			pow++;
		}
		if (pow != 0) {
			count++;
		}
		pow = 0;
		//p = get_next_prime(p);
                p++;
                while (composites.contains(p)) p++; //get next prime
	}
        return count;
    }
}

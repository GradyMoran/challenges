import java.util.*;
public class e46 {
    //public static Set<Long> primes;
    public static Set<Long> composites;
    public static void main(String[] args){
        composites = new HashSet<Long>();
        long MAX_CHECK = 10000L;
        
        //sieve
        for (Long i = 2l; i <= Math.sqrt(MAX_CHECK); i++){
            if (!composites.contains(i)){
                for (Long j = i*2; j < MAX_CHECK; j+=i){
                    composites.add(j);
                }
            }
        }
        
        for (long x = 9; x < Integer.MAX_VALUE; x+=2){
            if (composites.contains(x)){
                if (!isSum(x)){
                    System.out.println(x + " is the winner!");
                    System.exit(0);
                }
            }
        }
    }
    
    //this returns false if the input disproves goldbach's other conjecture
    public static boolean isSum(long x){
        long p = nextSmallestPrime(x);
        while (p != 3){
            if (isSquare((x - p)/2)){
                //System.out.println(x + " = " + p + " + 2*" + ((x - p)/2));
                return true;
            }
            p = nextSmallestPrime(p);
            if (p == -1) System.exit(1);
        }
        return false;
    }
    
    public static boolean isSquare(long x){
        return Math.sqrt(x) % 1 == 0;
    }
    
    public static long nextSmallestPrime(long x){
        x--;
        while (x >= 2){
            if (!composites.contains(x)) return x;
            x--;
        }
        return -1; //error
    }
}

import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class e49 { 
    public static Set<Long> composites;
    public static void main(String[] args){
        composites = new HashSet<Long>();
        composites.add(1L);
        long MAX_CHECK = 10000L; //record primes up to this value
        
        //sieve
        for (Long i = 2l; i <= Math.sqrt(MAX_CHECK); i++){
            if (!composites.contains(i)){
                for (Long j = i*2; j < MAX_CHECK; j+=i){
                    composites.add(j);
                }
            }
        }
        
        //try increasing gaps for each prime above 1000. fast since hash table lookup
        for (int p = 1009; p < 10000; p+=2){
            if (composites.contains((long) p)) continue;
            for (int gap = 2; 2*gap + p < 10000; gap+=2) { //gap has to be even
                if (composites.contains((long) gap+p) || composites.contains((long) 2*gap+p)) continue;
                if (check_perm(p, p+gap) && check_perm(p, p+2*gap)) System.out.println("" + p + (p+gap) + (p+2*gap));
            }
        }
    }
    
    //pre: x and y have same # digits
    public static boolean check_perm(int x, int y){
        char[] x_chars = Integer.toString(x).toCharArray();
        char[] y_chars = Integer.toString(y).toCharArray();
        Arrays.sort(x_chars);
        Arrays.sort(y_chars);
        for (int i = 0; i < x_chars.length; i++){
            if (x_chars[i] != y_chars[i]) return false;
        }
        return true;
    }
}

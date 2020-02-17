import java.util.*;
public class forty_one {
    public static void main(String[] args){
        Set<Integer> primes = new TreeSet<Integer>();
        primes.add(2);
        int biggest = 2143;
        int tmp = 3;
        boolean doAdd = true;
        while (tmp <= 987654321){
            for (Integer i : primes){ //maybe slow because iterator in hashset does not return smallest first
                doAdd = true;
                if (tmp % i == 0) {
                    doAdd = false;
                    break;
                }
            }
            if (doAdd) {
                primes.add(tmp);
                if (tmp > biggest && isPandigital(tmp)) {
                    biggest = tmp;
                }
            }
            tmp++;
        }
        System.out.println(biggest);
    }
    
    public static boolean isPandigital(int x){
        if (x > 987654321 || x < 0) return false;
        Set<Integer> digits = new HashSet<Integer>();
        String input = Integer.toString(x);
        
        for (int i = 0; i < input.length(); i++){
            if (digits.contains(input.charAt(i)-48)) return false; //includes same num twice
            digits.add(input.charAt(i)-48);
        }
        
        for (int i = 1; i <= input.length(); i++){
            if (!digits.contains(i)) return false; //doesn't contain a number in 1..n, where n = digits in x
        }
        return true;
    }
    
    public static boolean isPandigital2(int x){
        if (x > 987654321 || x < 0) return false;
        int pow = 1;
        while (Math.pow(10, pow) < x){
            pow++;
        }
        pow--;
        int[] digits = new int[pow];
        for (int i = pow-1; i >= 0; i--){
            digits[i] = (int) (x / Math.pow(10, i));
        }
        
        //??
        return true;
    }
}

import java.util.*;
public class forty_one_two {
    public static void main(String[] args){
        boolean[] composites = new boolean[987654322];
        composites[0] = true;
        composites[1] = true;
        for (int i = 2; i <= (int) Math.sqrt(987654322) && !composites[i]; i++){
            for (int j = i*i; j < 987654322; j+=i){
                composites[j] = true;
            }
        }
        for (int p = 987654321; p >= 2143; p--){
            if (!composites[p] && isPandigital(p)){
                System.out.println(p);
                break;
            }
        }
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

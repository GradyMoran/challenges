import java.util.*;
import java.lang.Math;
public class twenty_three {
    public static void main(String[] args){
        /*
        note: not sure how the 28*** limit applies, exactly but I think the following will work.
        find all abundant numbers by brute force up to 28***
        make a boolean array sized 28***
        n^2 loop through all combinations of abundant numbers, mark that value in array
        as true
        sum digits of all false values in array
        */
        
        /*
        since we know every num > 28123 can be written as the sum of two abundants,
        we only need to find abundants up to 28123, since we know the sum of any
        abundant greater than 28123 and another abundant will be more than 28123, and we 
        thus don't have to check it.
        */
        
        long start = System.nanoTime();
        //get a list of all abundant numbers under 28123
        List<Integer> abundants = new ArrayList<Integer>();
        for (int i = 2; i < 28123; i++){
            if (sumOfDivs(i) > i){
                abundants.add(i);
            }
        }
        
        boolean[] isSumOfAbundants = new boolean[28123*2];
        //for (int i = 0; i < 28123*2; i++) isSumOfAbundants[i] = false;
        int tmp;
        for (int i = 0; i < abundants.size(); i++){
            for (int j = i; j < abundants.size(); j++){
                tmp = abundants.get(i) + abundants.get(j);
                if (tmp < 28123) isSumOfAbundants[tmp] = true;
            }
        }
        
        int sum = 0;
        for (int i = 0; i < 28123; i++){
            if (!isSumOfAbundants[i]){
                sum+=i;
            }
        }
        System.out.println(sum);
        System.out.println((System.nanoTime() - start)/1000000 + "ms");
    }
    
    public static int sumOfDivs(int n){
        int sum = 1;
        int incrAmt = n % 2 == 0 ? 1 : 2;
        for (int i = incrAmt+1; i <= Math.sqrt(n); i+=incrAmt){
            if (n % i == 0){
                sum += i;
                if (n/i != i) sum+=(n/i);
            }
        }
        return sum;
    }
}

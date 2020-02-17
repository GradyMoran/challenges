import java.util.*;
public class thirty_two {
    public static void main(String[] args){
        Set<Integer> pandigitals = new HashSet<Integer>(); //use set since problem asks for sum of unique pandigital products, this easily removes duplicates
        long sum = 0;
        for (int mcand = 1; mcand <= 100; mcand++){//max upper limit of 100 b/c 100*100 would result in a pandigital with more than 9 digits
            for (int mplier = mcand; mcand*mplier <= 10000; mplier++){ //start mplier at mcand to avoid dups
                if (isPandigital(mcand, mplier)) {
                    //System.out.println(mcand + " " + mplier + " " + mcand*mplier);
                    if (!pandigitals.contains(mcand*mplier)) sum+=mcand*mplier;
                    pandigitals.add(mcand*mplier);
                }
            }
        }
        System.out.println(sum);
    }
    
    public static boolean isPandigital(int mcand, int mplier){
        //probably should have used a set and checked !contains() instead of array stuff
        boolean[] seen = new boolean[10];
        seen[0] = true;
        for (char t : ("" + mcand + mplier + mcand*mplier).toCharArray()){
            if (seen[t-48]) return false; //see same # twice, not pandigital
            seen[t-48] = true;
        }
        for (boolean b : seen) if (!b) return false; //number doesn't occur, not pandigital
        return true;
    }
}

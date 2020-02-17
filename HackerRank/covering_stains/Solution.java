import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    /*
    So there's actually only four ways a solution can use a smaller silk square than the original. That's if the set of k removed stains contains at least one of the set of all the top row, bottom row, right column, or left column stains. We don't care how much smaller, we only care that the solution set contains at least one full edge row/column. So can we get better efficiency by not actually enumerating beyond the first row/column removed (instead you can just compute n choose k).
    Not sure iterating through all (N choose K) subsets is feasible, even if checking is o(k). N is capped at 1000, so the max number of subsets would be 2.7*10^299 if k = 500.
    What we could do though, is eliminate the full top row, and count the ways you can choose (k - # stains in top row) other stains under the silk blanket. Then do the same for right column, left column, bottom row, and all combinations of those. But you will still have to keep track of the actual combination of stains chosen, because you have to be careful to not accidentally double count a solution where e.g., you randomly choose all the stains on the right column after removing the stains from the top row, and then count the same solution again when you remove all the right column stains and randomly remove the top row ones.
    Maybe we can simply count the number of "core" stains, i.e., stains that are not on the edges of row or column. Then do brute force to find whether we can remove all the top row, all the right column, left column, and bottom row, and how many combinations of that we can do. Then with the remaining k-foo unused squirts, compute (Core count choose k-foo), and multiply that by the number of ways for the outer edge.
    So suppose there are 3 stains in the top row, and k = 5. We can choose zero stains from other edges, and then get (core choose 2) ways to have smaller silk by covering two of the core stains. Then count the number of ways to remove one more stain from around the edge, and multiply that by (core choose 1), and add to total. Then count the number of ways to remove two stains from around the edges, and multiply it by (core choose 0). Do the same with the other edges. 

    T
    B
    L
    R
    TR
    TL
    TB
    RL
    RB
    LB
    TRL
    TRB
    TLB
    LBR
    TRLB

    ^^^ those are all the combinations of four edges. Start from the bottom up, if you can cover that many stains. Do something where you compute (total_other_stains choose unused_squirts) and add to total. But for each, you have to subtract the counts from all the ones below it? Something like that. For T, subtract (T-TR) if (T-TR) > 0, and the same for all other combinations with T in them. You only subtract the difference because what we're interested in is how many of the T combinations (stain combinations where the whole top row is removed) were already counted in TRLB, for instance.

    So, for T, you have to subtract all the below categories that contain T, then store that number as T. Add them all up at the end, and that's the result. 

    Special case: if a stain is in the corner, you have to include it in both the edge row and the edge column. But doesn't that mess up the combining logic I described above? Edit: no

    */
    
    private static class Coord{
        final int x;
        final int y;
        public Coord(int x, int y){
            this.x = x;
            this.y = y;
        }
        public boolean equals(Object o){
            if (!(o instanceof Coord)) return false;
            Coord tmp = (Coord) o;
            return tmp.x == this.x && tmp.y == this.y;
        }
        public String toString(){
            return "(" + this.x + ", " + this.y + ")";
        }
    }

    public static BigInteger numWays(int n, int k, Set<Coord> stains){
        if (stains.size() == 0) return BigInteger.ONE;
        BigInteger count = new BigInteger("0");
        int max_x = 0;
        int max_y = 0;
        int min_x = Integer.MAX_VALUE; //The max_values might mess stuff up if the size is zero.
        int min_y = Integer.MAX_VALUE;
        for (Coord stain : stains){
            if (stain.x > max_x) max_x = stain.x;
            if (stain.x < min_x) min_x = stain.x;
            if (stain.y > max_y) max_y = stain.y;
            if (stain.y < min_y) min_y = stain.y;
        }
        //sets of nodes for the edges
        Set<Coord> T = new HashSet<Coord>();
        Set<Coord> B = new HashSet<Coord>();
        Set<Coord> R = new HashSet<Coord>();
        Set<Coord> L = new HashSet<Coord>();

        for (Coord c : stains){
            if (c.x == max_x) R.add(c);
            if (c.y == max_y) B.add(c);
            if (c.x == min_x) L.add(c);
            if (c.y == min_y) T.add(c);
        }

        Set<Coord> edges = new HashSet<Coord>();
        edges.addAll(T);
        edges.addAll(B);
        edges.addAll(R);
        edges.addAll(L);
        int edgeCount = edges.size();
        int coreCount = n - edgeCount;

        /*
        T
        B
        L
        R
        TR
        TL
        TB
        RL
        RB
        LB
        TRL
        TRB
        TLB
        LBR
        TRLB
        */

        //TRLB (shrink in all directions)
        Set<Coord> TRLB = new HashSet<Coord>();
        TRLB.addAll(T);
        TRLB.addAll(R);
        TRLB.addAll(L);
        TRLB.addAll(B);
        BigInteger TRLBcount = BigInteger.ZERO;
        if (TRLB.size() <= k){
            TRLBcount = nChooseK(n - TRLB.size(), k - TRLB.size());
            count = count.add(TRLBcount);
        }

        //LBR (shrink left, bottom, right, but not top)
        Set<Coord> LBR = new HashSet<Coord>();
        LBR.addAll(L);
        LBR.addAll(B);
        LBR.addAll(R);
        BigInteger LBRcount = BigInteger.ZERO;
        if (LBR.size() <= k){
            LBRcount = nChooseK(n - LBR.size(), k - LBR.size()).subtract(TRLBcount);
            count = count.add(LBRcount);
        }

        //TLB
        Set<Coord> TLB = new HashSet<Coord>();
        TLB.addAll(T);
        TLB.addAll(L);
        TLB.addAll(B);
        BigInteger TLBcount = BigInteger.ZERO;
        if (TLB.size() <= k){
            TLBcount = nChooseK(n - TLB.size(), k - TLB.size()).subtract(TRLBcount);
            count = count.add(TLBcount);
        }

        //TRB
        Set<Coord> TRB = new HashSet<Coord>();
        TRB.addAll(T);
        TRB.addAll(R);
        TRB.addAll(B);
        BigInteger TRBcount = BigInteger.ZERO;
        if (TRB.size() <= k){
            TRBcount = nChooseK(n - TRB.size(), k - TRB.size()).subtract(TRLBcount);
            count = count.add(TRBcount);
        }

        //TRL
        Set<Coord> TRL = new HashSet<Coord>();
        TRL.addAll(T);
        TRL.addAll(R);
        TRL.addAll(L);
        BigInteger TRLcount = BigInteger.ZERO;
        if (TRL.size() <= k){
            TRLcount = nChooseK(n - TRL.size(), k - TRL.size()).subtract(TRLBcount);
            count = count.add(TRLcount);
        }

        //LB
        Set<Coord> LB = new HashSet<Coord>();
        LB.addAll(L);
        LB.addAll(B);
        BigInteger LBcount = BigInteger.ZERO;
        if (LB.size() <= k){
            LBcount = nChooseK(n - LB.size(), k - LB.size()).subtract(TRLBcount).subtract(TLBcount).subtract(LBRcount);
            count = count.add(LBcount);
        }

        //RB
        Set<Coord> RB = new HashSet<Coord>();
        RB.addAll(R);
        RB.addAll(B);
        BigInteger RBcount = BigInteger.ZERO;
        if (RB.size() <= k){
            RBcount = nChooseK(n - RB.size(), k - RB.size()).subtract(TRLBcount).subtract(TRBcount).subtract(LBRcount);
            count = count.add(RBcount);
        }

        //RL
        Set<Coord> RL = new HashSet<Coord>();
        RL.addAll(R);
        RL.addAll(L);
        BigInteger RLcount = BigInteger.ZERO;
        if (RL.size() <= k){
            RLcount = nChooseK(n - RL.size(), k - RL.size()).subtract(TRLBcount).subtract(TRLcount).subtract(LBRcount);
            count = count.add(RLcount);
        }

        //TB
        Set<Coord> TB = new HashSet<Coord>();
        TB.addAll(T);
        TB.addAll(B);
        BigInteger TBcount = BigInteger.ZERO;
        if (TB.size() <= k){
            TBcount = nChooseK(n - TB.size(), k - TB.size()).subtract(TRLBcount).subtract(TRBcount).subtract(TLBcount);
            count = count.add(TBcount);
        }

        //TL
        Set<Coord> TL = new HashSet<Coord>();
        TL.addAll(T);
        TL.addAll(L);
        BigInteger TLcount = BigInteger.ZERO;
        if (TL.size() <= k){
            TLcount = nChooseK(n - TL.size(), k - TL.size()).subtract(TRLBcount).subtract(TRLcount).subtract(TLBcount);
            count = count.add(TLcount);
        }

        //TR
        Set<Coord> TR = new HashSet<Coord>();
        TR.addAll(T);
        TR.addAll(R);
        BigInteger TRcount = BigInteger.ZERO;
        if (TR.size() <= k){
            TRcount = nChooseK(n - TR.size(), k - TR.size()).subtract(TRLBcount).subtract(TRLcount).subtract(TRBcount);
            count = count.add(TRcount);
        }

        //T
        BigInteger Tcount = BigInteger.ZERO;
        if (T.size() <= k){
            Tcount = nChooseK(n - T.size(), k - T.size()).subtract(TRLBcount).subtract(TRLcount).subtract(TRBcount).subtract(TLBcount).subtract(TRcount).subtract(TLcount).subtract(TBcount);
            count = count.add(Tcount);
        }

        //B
        BigInteger Bcount = BigInteger.ZERO;
        if (B.size() <= k){
            Bcount = nChooseK(n - B.size(), k - B.size()).subtract(TRLBcount).subtract(LBRcount).subtract(TLBcount).subtract(TRBcount).subtract(LBcount).subtract(RBcount).subtract(TBcount);
            count = count.add(Bcount);
        }

        //L
        BigInteger Lcount = BigInteger.ZERO;
        if (L.size() <= k){
            Lcount = nChooseK(n - L.size(), k - L.size()).subtract(TRLBcount).subtract(TRLcount).subtract(TLBcount).subtract(LBRcount).subtract(TLcount).subtract(RLcount).subtract(LBcount);
            count = count.add(Lcount);
        }

        //R
        BigInteger Rcount = BigInteger.ZERO;
        if (R.size() <= k){
            Rcount = nChooseK(n - R.size(), k - R.size()).subtract(TRLBcount).subtract(TRLcount).subtract(TRBcount).subtract(LBRcount).subtract(TRcount).subtract(RLcount).subtract(RBcount);
            count = count.add(Rcount);
        }

        /*
        T
        B
        L
        R
        TR
        TL
        TB
        RL
        RB
        LB
        TRL
        TRB
        TLB
        LBR
        TRLB
        */

        return count.mod(new BigInteger("1000000007"));
    }

    public static BigInteger nChooseK(int n, int k){
        return fact(n).divide((fact(k).multiply(fact(n-k))));
    }

    public static BigInteger fact(int n){
        return BIfact(new BigInteger("" + n));
    }

    public static BigInteger BIfact(BigInteger n){
        if (n.compareTo(BigInteger.ONE) <= 0) return BigInteger.ONE;
        return n.multiply(BIfact(n.subtract(BigInteger.ONE)));
    }     

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner kb = new Scanner(System.in);
        String line = kb.nextLine();
        int n = Integer.parseInt(line.split(" ")[0]);
        int k = Integer.parseInt(line.split(" ")[1]);
        Set<Coord> stains = new HashSet<Coord>();
        for (int i = 0; i < n; i++){
            line = kb.nextLine();
            Coord stain = new Coord(Integer.parseInt(line.split(" ")[0]), Integer.parseInt(line.split(" ")[1]));
            stains.add(stain);
        }
        System.out.println(numWays(n, k, stains).toString());
    }
}

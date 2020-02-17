public class e44 {
    public static void main(String[] args){
        /*
         * notes:
         * for each j, after a certain k, no sums can be pentagonal. if Pj + Pk < P(k+1), need to increase j.
         * possibly, the first result found using the obvious smallest-first search alg will minimize D, but not sure
         */
        for (int j = 1;;j++){
            for (int k = j+1; nthPent(j) + nthPent(k) >= nthPent(k+1);k++){
                if (isPent(nthPent(j) + nthPent(k)) && isPent(nthPent(k) - nthPent(j))){
                    System.out.println(nthPent(j) + " " + nthPent(k));
                }
            }
        }
    }
    
    //algorithm from wikipedia page
    public static boolean isPent(int x){
        return (Math.sqrt(24*x + 1) + 1)/6 % 1 == 0;
    }
    
    //pre: n >= 1
    public static int nthPent(int n){
        return n*(3*n-1)/2;
    }
}

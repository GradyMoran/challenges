import java.math.BigInteger;
public class twenty_six {
    public static void main(String[] args){
	//https://en.wikipedia.org/wiki/Repeating_decimal
	//http://mathworld.wolfram.com/DecimalPeriod.html
	//if n r.p. to 10, It turns out that lambda(n) is the multiplicative order of 10 (mod n) (Glaisher 1878, Lehmer 1941). (i.e. least power of 10 s.t. 10^x mod n == 1)
        int maxNum = 3;
        int maxLen = 1;
        for (int i = 3; i < 1001; i++){
            if (rep_cycle_len(i) > maxLen){
                maxNum = i;
                maxLen = rep_cycle_len(i);
            }
        }
        System.out.println(maxNum);            
    }
    
    //precondition: x positive and relatively prime to 10 :(
    public static int rep_cycle_len(int x){
	BigInteger n = new BigInteger("" + x);
        for (int p = 1; p <= n.intValue(); p++){
            if (BigInteger.TEN.pow(p).mod(n).equals(BigInteger.ONE)) return p;
        }
        return -1;
    }
}

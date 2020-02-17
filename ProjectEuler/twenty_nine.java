import java.util.*;
import java.math.BigInteger;
public class twenty_nine {
    public static void main(String[] args){
        Set<BigInteger> s = new HashSet<BigInteger>();
        for (int a = 2; a <= 100; a++){
            for (int b = 2; b <= 100; b++){
                s.add(new BigInteger("" + a).pow(b));
            }
        }
        System.out.println(s.size());
    }
}

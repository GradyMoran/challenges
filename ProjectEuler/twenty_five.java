import java.math.BigInteger;
public class twenty_five {
    public static void main(String[] args){
        BigInteger prev2 = BigInteger.ONE;
        BigInteger prev1 = BigInteger.ONE;
        BigInteger next = BigInteger.ONE;
        long counter = 2;
        BigInteger kDigits = new BigInteger("10"); //first fib # greater than 10^999 is first one with 1000+ digits
        BigInteger tmp = kDigits;
        for (int i = 1; i < 999; i++) kDigits = kDigits.multiply(tmp);
        
        while (next.compareTo(kDigits) <= 0){
            prev2 = prev1;
            prev1 = next;
            next = prev1.add(prev2);
            counter++;
        }
        System.out.println(counter);
    }
}

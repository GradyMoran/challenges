import java.math.*;
public class sixteen {
    public static void main(String[] args){
        BigInteger n = new BigInteger("2");
        String digits = n.pow(1000).toString();
        long sum = 0;
        for (int i = 0; i < digits.length(); i++){
            sum += Integer.parseInt(digits.substring(i, i+1));
        }
        System.out.println(sum);
    }
}

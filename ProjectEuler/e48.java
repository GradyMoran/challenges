import java.math.BigInteger;
public class e48 {
    public static void main(String[] args){
        long last10 = 0;
        BigInteger tmp;
        for (int i = 1; i <= 1000; i++){
            tmp = new BigInteger(Integer.toString(i));
            String digits = tmp.pow(i).toString();
            last10 += Long.parseLong(digits.substring(digits.length() < 10 ? 0 : digits.length()-10, digits.length()));
            last10 = last10 % 10000000000l; //to ensure last10 always is at most 10 digits
        }
        System.out.println(last10);
    }
}

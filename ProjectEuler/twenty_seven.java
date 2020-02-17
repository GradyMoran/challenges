public class twenty_seven {
    public static void main(String[] args){
        //brute force
        int maxA = 0; 
        int maxB = 0;
        int maxNumPrimes = 0;
        int tmpNumPrimes = 0;
        for (int a = -999; a <= 999; a++){
            for (int b = -1000; b <= 1000; b++){
                for (int n = 0; n < Math.abs(b); n++){
                    if (isPrime((int) Math.pow(n, 2) + a*n + b)){
                        tmpNumPrimes++;
                    } else {
                        break;
                    }
                }
                if (tmpNumPrimes > maxNumPrimes){
                    maxA = a;
                    maxB = b;
                    maxNumPrimes = tmpNumPrimes;
                }
                tmpNumPrimes = 0;
            }
        }
        System.out.println("maxA: " + maxA + " maxB: " + maxB + " product: " + maxA*maxB + " maxNumPrimes: " + maxNumPrimes);
    }
    
    public static boolean isPrime(int x){
        if (x < 2) return false;
        for (int i = 2; i <= Math.sqrt(x); i++){
            if (x % i == 0) return false;
        }
        return true;
    }
}

public class thirty_seven {
     public static int get_next_prime(int p){
        if (p < 2) return 2;
	if (p == 2) return 3;
	while(p>0){
            p++;
            for (int i = 2; i <= p/2; i++){ //test if p is prime
		if (p % i == 0) break;
		if (i == (p/2)) return p; 
            }
	}
	return -1; //should not get here
    }
    
    public static boolean isPrime(int x){
        if (x < 2) return false;
        for (int i = 2; i <= Math.sqrt(x); i++){
            if (x % i == 0) return false;
        }
        return true;
    }
    
    public static boolean is_trunc_prime(int p){
        if (p < 10) return false;
        //left
        int pow = 0;
        while (Math.pow(10, pow) < p) pow++;
        int tmp = p;
        do{
            //make tmp equal to tmp modded by some power of ten, decrease power each iteration
            tmp = tmp % (int) Math.pow(10, pow);
            pow--;
            //if any tmp is not prime, return false
            if (!isPrime(tmp)) return false;
        } while (tmp > 10); //if tmp < 10, then we just checked last digit
        
        //right
        tmp = p;
        do{
            //divide by 10 each time
            tmp = tmp/10;
            if (!isPrime(tmp)) return false;
        } while (tmp > 10);
        return true;
    }
    
    public static void main(String[] args){
        int num_found = 0, sum = 0, p = 11;
        while (num_found < 11){
            p = get_next_prime(p);
            if (is_trunc_prime(p)){
                num_found++;
                System.out.println(p);
                sum+=p;
            }
        }
        System.out.println("sum: " + sum);
    }
}

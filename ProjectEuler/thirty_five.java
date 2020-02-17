public class thirty_five {
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
    
    public static void main(String[] args){
        int count = 0, tmp_count;
        for(int p = 2; p < 1000000; p = get_next_prime(p)){
            String p_string = Integer.toString(p);
            tmp_count = 0;
            for (int i = 0; i < p_string.length(); i++){
                if (isPrime(Integer.parseInt(p_string))){
                    tmp_count++;
                }
                else break;
                p_string = p_string.charAt(p_string.length()-1) + p_string.substring(0, p_string.length()-1);
            }
            if (tmp_count == p_string.length()){
                count++;
            }
        }
        System.out.println(count);
    }
}

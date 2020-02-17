public class thirty_four {
    public static int factorial(int n){
        if (n <= 1) return 1;
        return n*factorial(n-1);
    }
    
    public static void main(String[] args){
        long sum = 0;
        long dfs;
        String num;
        for (long n = 10; n < 10000000; n++){
            dfs = 0;
            num = Long.toString(n);
            for (int i = 0; i < num.length(); i++) dfs += factorial(num.charAt(i) - 48);
            if (n == dfs){
                System.out.println(dfs);
                sum+=n;
            }
        }
        System.out.println("sum:" + sum);
    }
}

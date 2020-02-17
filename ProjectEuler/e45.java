public class e45 {
    //solution thread pointed out that all hexagonal numbers are triangle numbers, which skips a lot of computations
    public static void main(String[] args){
        long t;
        for (long i = 286;;i++){
            t = i*(i+1)/2;
            if (isPent(t) && isHex(t)) {
                System.out.println("triangle #: " + i + " num: " + t);
                break;
            }
        }
    }
    
    public static boolean isPent(long x){
        return (Math.sqrt(24*x + 1) + 1)/6 % 1 == 0;
    }
    
    public static boolean isHex(long x){
        return (Math.sqrt(8*x + 1) + 1)/4 % 1 == 0;
    }
}

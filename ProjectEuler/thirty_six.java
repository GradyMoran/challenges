public class thirty_six {
    public static boolean is_b10_palindrome(int x){
        String s = Integer.toString(x);
        for (int i = (s.length()-1)/2; i >= 0; i--){
            if (s.charAt(i) != s.charAt(s.length()-1-i)) return false;
        }
        return true;
    }
    
    public static boolean is_b2_palindrome(int x){
        String s = Integer.toBinaryString(x);
        for (int i = (s.length()-1)/2; i >= 0; i--){
            if (s.charAt(i) != s.charAt(s.length()-1-i)) return false;
        }
        return true;
    }
    
    public static void main(String[] args){
        int sum = 0;
        for (int i = 0; i < 1000000; i++){
            if (is_b10_palindrome(i) && is_b2_palindrome(i)){
                sum+=i;
            }
        }
        System.out.println(sum);
    }
}

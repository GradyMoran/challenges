public class thirty_eight{
    public static void main(String[] args){
        int largest = 918273645; //ex from page, = 9 * (1,2,3,4,5)
        int i, n;
        for (i = 1; i <= 987654321; i++){
            String tmp = "";
            for (n = 1; n <= 9 && tmp.length() < 9; n++){
                tmp = tmp + Integer.toString(i*n);
            }
            if (n > 1 && tmp.length() == 9 && Integer.parseInt(tmp) > largest && isPandigital(Integer.parseInt(tmp)) ){
                largest = Integer.parseInt(tmp);
                System.out.println(tmp);
            }
        }
        System.out.println("" + largest);
    }
   
    public static boolean isPandigital(int n){
        //probably should have used a set and checked !contains() instead of array stuff
        boolean[] seen = new boolean[10];
        seen[0] = true;
        for (char t : ("" + n).toCharArray()){
            if (seen[t-48]) return false; //see same # twice, not pandigital
            seen[t-48] = true;
        }
        for (boolean b : seen) if (!b) return false; //number doesn't occur, not pandigital
        return true;
    }
}
public class thirty_nine {
    public static void main(String[] args){
        int p, a, b, c, count, maxcount = 0, maxnum = 0;
        for (p = 120; p <= 1000; p++){
            count = 0;
            for (a = 1; a < 1000; a++){
                for (b = a; b < 1000; b++){
                    c = p - (a+b);
                    if (c > b && a*a + b*b == c*c){ //c > b prevents double counting
                        count++;
                    }
                }
            }
            //System.out.println();
            if (count > maxcount) {
                maxnum = p;
                maxcount = count;
            }
        }
        System.out.println(maxnum);
    }
}
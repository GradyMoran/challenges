public class thirty_three {
    public static void main(String[] args){
        //form ab/cd (not product ab and cd, but digits a, b and c, d
        for (int a = 1; a < 10; a++){
            for (int b = 1; b < 10; b++){
                for (int c = a; c < 10; c++){ //note c starts at a, which skips a lot of num>denom cases
                    for (int d = 1; d < 10; d++){
                        if (10*a + b < 10*c + d) { //only check fractions less than 1
                            if (a == d){
                                double f = (double) (10*a + b)/(10*c + d);
                                double f2 = (double) b/c;
                                if (f == f2) {
                                    System.out.println((10*a+b) + "/" + (10*c+d));
                                }
                            }
                            else if (b == c){
                                double f = (double) (10*a + b)/(10*c + d);
                                double f2 = (double) a/d;
                                if (f == f2){
                                    System.out.println((10*a+b) + "/" + (10*c+d));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

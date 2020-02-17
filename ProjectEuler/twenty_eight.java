public class twenty_eight {
    public static void main(String[] args){
        long sum = 1;
        int gap = 2; //side length of current concentric square layer (-1, since don't count corners twice)
        int tmp = 1; //value at corner of square
        
        for (int i = 0; i < 500; i++){ //500 concentric squares layered over origin results in 1001x1001 matrix
            for (int j = 0; j < 4; j++){ //4 corners in a square...
                tmp += gap; //this corner is side length plus prev corner
                sum += tmp;
            }
            gap += 2; //side length of each square 2 larger than previous square
        }
        System.out.println(sum);
    }
}

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the surfaceArea function below.
    static int surfaceArea(int[][] A) {
        //row by row, add to a running sum the absolute value of all the height differences between blocks in the row.
        //the absolute value of the height difference = the exposed surface area on the side of the blocks
        //do the same for all the columns. 
        //sum all the rows and columns
        //add 2 * H * W (for top area and bottom area)

        int prev = 0;
        int running_sum = 0;

        //row-wise
        for (int r = 0; r < A.length; r++){
            prev = 0;
            for (int c = 0; c < A[0].length; c++){
                running_sum += Math.abs(A[r][c] - prev);
                prev = A[r][c];
            }
            running_sum += prev;
        }

        //column-wise
        for (int c = 0; c < A[0].length; c++){
            prev = 0;
            for (int r = 0; r < A.length; r++){
                running_sum += Math.abs(A[r][c] - prev);
                prev = A[r][c];
            }
            running_sum += prev;
        }
        return running_sum + ((2 * A.length) * A[0].length);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String[] HW = scanner.nextLine().split(" ");

        int H = Integer.parseInt(HW[0]);

        int W = Integer.parseInt(HW[1]);

        int[][] A = new int[H][W];

        for (int i = 0; i < H; i++) {
            String[] ARowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < W; j++) {
                int AItem = Integer.parseInt(ARowItems[j]);
                A[i][j] = AItem;
            }
        }

        int result = surfaceArea(A);

        System.out.println(String.valueOf(result));

        scanner.close();
    }
}


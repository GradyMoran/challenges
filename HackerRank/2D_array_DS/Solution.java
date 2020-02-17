import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
        int largestSum = -1000;
        //iterate through each hourglass
        //i,j = top left square of hourglass. k,l = row,column within that hourglass
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                int tmpSum = 0;
                for (int k = 0; k < 3; k++){
                    for (int l = 0; l < 3; l++){
                        //skip the spots not included in the hourglass
                        if (k == 1 && (l == 0 || l == 2)) continue;
                        tmpSum += arr[i+k][j+l];
                    }
                }
                if (tmpSum >= largestSum) largestSum = tmpSum;
            }
        }
        return largestSum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        int result = hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}


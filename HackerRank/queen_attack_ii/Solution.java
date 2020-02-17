import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the queensAttack function below.
    static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
        //unsorted obstacles is garbo, would rather have HashSet<ArrayList<Integer>>, where each ArrayList is an x, y coordinate pair. Then checking whether there is an obstacle at a given point is just a ~O(1) operation.
        //in retrospect, it would have been cleaner to have a HashSet<Coord>.
        HashSet<ArrayList<Integer>> obsts = new HashSet<ArrayList<Integer>>();
        for (int i = 0; i < obstacles.length; i++){
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            tmp.add(obstacles[i][0]);
            tmp.add(obstacles[i][1]);
            obsts.add(tmp);
        }
        int spotCount = 0;
        //straight up
        for (int i = 1; r_q - i > 0; i++){
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            tmp.add(r_q-i);
            tmp.add(c_q);
            if (obsts.contains(tmp)){
                break;
            }
            spotCount++;
        }
        //up right diag
        for (int i = 1; r_q - i > 0 && c_q + i <= n; i++){
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            tmp.add(r_q-i);
            tmp.add(c_q+i);
            if (obsts.contains(tmp)){
                break;
            }
            spotCount++;
        }
        //straight right
        for (int i = 1; c_q + i <= n; i++){
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            tmp.add(r_q);
            tmp.add(c_q+i);
            if (obsts.contains(tmp)){
                break;
            }
            spotCount++;
        }
        //down right diag
        for (int i = 1; r_q + i <= n && c_q + i <= n; i++){
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            tmp.add(r_q+i);
            tmp.add(c_q+i);
            if (obsts.contains(tmp)){
                break;
            }
            spotCount++;
        }
        //straight down
        for (int i = 1; r_q + i <= n; i++){
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            tmp.add(r_q+i);
            tmp.add(c_q);
            if (obsts.contains(tmp)){
                break;
            }
            spotCount++;
        }
        //down left diag
        for (int i = 1; r_q + i <= n && c_q - i > 0; i++){
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            tmp.add(r_q+i);
            tmp.add(c_q-i);
            if (obsts.contains(tmp)){
                break;
            }
            spotCount++;
        }
        //straight left
        for (int i = 1; c_q - i > 0; i++){
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            tmp.add(r_q);
            tmp.add(c_q-i);
            if (obsts.contains(tmp)){
                break;
            }
            spotCount++;
        }
        //up left diag
        for (int i = 1; c_q - i > 0 && r_q - i > 0; i++){
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            tmp.add(r_q-i);
            tmp.add(c_q-i);
            if (obsts.contains(tmp)){
                break;
            }
            spotCount++;
        }
        return spotCount;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String[] r_qC_q = scanner.nextLine().split(" ");

        int r_q = Integer.parseInt(r_qC_q[0]);

        int c_q = Integer.parseInt(r_qC_q[1]);

        int[][] obstacles = new int[k][2];

        for (int i = 0; i < k; i++) {
            String[] obstaclesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int obstaclesItem = Integer.parseInt(obstaclesRowItems[j]);
                obstacles[i][j] = obstaclesItem;
            }
        }

        int result = queensAttack(n, k, r_q, c_q, obstacles);

        System.out.println(String.valueOf(result));

        scanner.close();
    }
}


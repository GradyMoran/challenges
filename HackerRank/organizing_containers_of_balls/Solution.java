import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the organizingContainers function below.
    //you just have to check if there exists a unique container of the right size for each ball type
    static String organizingContainers(int[][] container) {
        int[] ballCounts = new int[container.length];
        int[] containerCapacities = new int[container.length];
        for (int i = 0; i < container.length; i++){
            for (int j = 0; j < container[i].length; j++){
                ballCounts[j] += container[i][j];
                containerCapacities[i] += container[i][j];
            }
        }
        for (int i = 0; i < ballCounts.length; i++){
            boolean found = false;
            for (int j = 0; j < containerCapacities.length; j++){
                if (ballCounts[i] == containerCapacities[j]){
                    containerCapacities[j] *= -1;
                    found = true;
                    break;
                }
            }
            if (!found) return "Impossible";
        }
        return "Possible";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[][] container = new int[n][n];

            for (int i = 0; i < n; i++) {
                String[] containerRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < n; j++) {
                    int containerItem = Integer.parseInt(containerRowItems[j]);
                    container[i][j] = containerItem;
                }
            }

            String result = organizingContainers(container);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}


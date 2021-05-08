import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'maxRegion' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY grid as parameter.
     */

    public static int maxRegion(List<List<Integer>> grid) {
        int rows = grid.size();
        int cols = grid.get(0).size();
        int maxSection = -1;
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                if (grid.get(r).get(c) != 1) continue;
                int sectionSize = dfsSize(grid, r, c);
                maxSection = sectionSize > maxSection ? sectionSize : maxSection;
            }
        } 
        return maxSection;
    }

    public static int dfsSize(List<List<Integer>> grid, int row, int col){
        if (row < 0 || row >= grid.size() || col < 0 || col >= grid.get(0).size()) return 0;
        if (grid.get(row).get(col) != 1) return 0;
        grid.get(row).set(col, -1);
        int retval = 1;
        //up
        retval+=dfsSize(grid, row-1, col); 
        //up diag right
        retval+=dfsSize(grid, row-1, col+1); 
        //right
        retval+=dfsSize(grid, row, col+1); 
        //down diag right
        retval+=dfsSize(grid, row+1, col+1); 
        //down
        retval+=dfsSize(grid, row+1, col); 
        //down diag left
        retval+=dfsSize(grid, row+1, col-1); 
        //left
        retval+=dfsSize(grid, row, col-1); 
        //up diag left
        retval+=dfsSize(grid, row-1, col-1); 
        return retval;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int m = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> grid = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                grid.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int res = Result.maxRegion(grid);

        System.out.println("" + res);

        bufferedReader.close();
    }
}


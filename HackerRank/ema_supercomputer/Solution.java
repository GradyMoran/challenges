import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the twoPluses function below.
    static int twoPluses(String[] grid) {
        //An array of strings is ungainly, transform into boolean[][] where false is bad and true is good.
        boolean[][] bGrid = new boolean[grid.length][grid[0].length()];
        for (int r = 0; r < grid.length; r++){
            for (int c = 0; c < grid[0].length(); c++){
                bGrid[r][c] = (grid[r].charAt(c) == 'G');
            }
        }
        
        //for each (good) square in the grid, calculate every possible cross from its location (extending outwards in all directions)
        //create a temp copy of the grid, mark all spots in the cross as bad, and find the area of the largest remaining cross.
        //multiply the areas of the crosses, and save if it's larger than the running max.
        //after iterating through each good square, return the max.
        int tmpMin = Math.min(bGrid.length, bGrid[0].length);
        int largestOdd = tmpMin % 2 == 1 ? tmpMin : tmpMin-1; //largest possible cross
        int largestProduct = 0;
        for (int r = 0; r < bGrid.length; r++){
            for (int c = 0; c < bGrid[0].length; c++){
                for (int length = 1; length <= largestOdd; length += 2){
                    if (!isCross(bGrid, length, r, c)) break;
                    //if it is a cross, create a temp grid with it removed.
                    boolean[][] tmpGrid = removePlus(bGrid, length, r, c); //returns a copy
                    int secondCrossArea = largestPlusArea(tmpGrid);
                    if (largestProduct < secondCrossArea * (length*2 - 1)){
                        largestProduct = secondCrossArea * (length*2 - 1);
                    }
                }
            }
        }
        return largestProduct;
    }

    //works by taking the largest possible vertical (largest odd number <= (min(n, m))), and searching for a sequence of good squares aligned vertically of that size. check if there is a horizontal line across the middle of the same size. decrease by 2 each time until a cross is found.
    static int largestPlusArea(boolean[][] grid) {
        int tmpMin = Math.min(grid.length, grid[0].length);
        int largestOdd = tmpMin % 2 == 1 ? tmpMin : tmpMin-1;
        for (int tmpLength = largestOdd; tmpLength > 0; tmpLength -= 2){
            for (int c = 0; c < grid[0].length; c++){
                for (int r = 0; r < grid.length; r++){
                    //can you draw a cross with length verticalSize and center at grid[r][c]?
                    if (isCross(grid, tmpLength, r, c)){
                        return tmpLength*2 - 1;
                    }
                }
            }
        }
        return 0;
    }

    //I'll say a cross's "location" is the center.
    static boolean isCross(boolean[][] grid, int length, int r, int c){
        //check if cross would run off the side of the grid
        if (r + length/2 >= grid.length || r - length/2 < 0) return false;
        if (c + length/2 >= grid[0].length || c - length/2 < 0) return false;
        //check all the squares in the cross are good.
        for (int i = r-length/2; i <= r+length/2; i++){
            if (grid[i][c] == false) return false;
        }
        for (int j = c-length/2; j <= c+length/2; j++){
            if (grid[r][j] == false) return false;
        }
        return true;
    }

    //assumes inputted plus is valid.
    static boolean[][] removePlus(boolean[][] grid, int length, int r, int c){
        boolean[][] newGrid = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++){
            newGrid[i] = Arrays.copyOf(grid[i], grid[i].length);
        }
        for (int i = r-length/2; i <= r+length/2; i++){
            newGrid[i][c] = false;
        }
        for (int j = c-length/2; j <= c+length/2; j++){
            newGrid[r][j] = false;
        }
        return newGrid;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        String[] grid = new String[n];

        for (int i = 0; i < n; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        int result = twoPluses(grid);

        System.out.println(String.valueOf(result));

        scanner.close();
    }
}

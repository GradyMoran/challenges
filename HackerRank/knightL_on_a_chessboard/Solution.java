import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    private static class Coord{
        int x;
        int y;
        public Coord(int x, int y){
            this.x = x;
            this.y = y;
        }
        public String toString(){
            return "(" + this.x + ", " + this.y + ")";
        }
    }

    /*
    To find the path length for a particular n,a,b triple, need to start by initializing an n x n board with 0 at the starting position
    and maxInt at each other position. Keep track of the knight's "current position" by placing possible move destinations in a queue.
    At each step of the algorithm, pop from the queue, and check if moving there decreases the cost (by adding 1 to the cost of the 
    current square). If the cost goes down, modify the n x n board to have the new lower cost for that square, and then add the new
    possible destinations to the queue. When you pop from the queue and moving to the destination would not be of a lower cost, do not
    add its squares to the queue.
    */

    //note the solution is always symmetrical about the main diagonal. I'll make that optimization if simply calculating everything 
    //turns out to be slow enough to fail.

    // Complete the knightlOnAChessboard function below.
    static int[][] knightlOnAChessboard(int n) {
        int[][] retval = new int[n-1][n-1];
        for (int a = 1; a < n; a++){
            for (int b = 1; b < n; b++){
                int[][] dynLengths = new int[n][n];
                //initialize dynLenghts
                for (int i = 0; i < n; i++){
                    for (int j = 0; j < n; j++){
                        dynLengths[i][j] = Integer.MAX_VALUE;
                    }
                }
                dynLengths[0][0] = 0;
                Queue<Coord> toCheck = new LinkedList<Coord>();
                toCheck.offer(new Coord(0, 0));

                //this goes until there are no more unvisited possible destinations.
                //But, I think you can quit early as soon as you reach the destination
                //square, since BFS will always find shortest path first.
                while(toCheck.size() > 0){
                    Coord source = toCheck.poll();
                    Set<Coord> dests = new HashSet<Coord>();
                    if (source.x + a < n && source.y + b < n){
                        dests.add(new Coord(source.x + a, source.y + b));
                    }
                    if (source.x + a < n && source.y - b >= 0){
                        dests.add(new Coord(source.x + a, source.y - b));
                    }
                    if (source.x - a >= 0 && source.y + b < n){
                        dests.add(new Coord(source.x - a, source.y + b));
                    }
                    if (source.x - a >= 0 && source.y - b >= 0){
                        dests.add(new Coord(source.x - a, source.y - b));
                    }
                    if (source.x + b < n && source.y + a < n){
                        dests.add(new Coord(source.x + b, source.y + a));
                    }
                    if (source.x + b < n && source.y - a >= 0){
                        dests.add(new Coord(source.x + b, source.y - a));
                    }
                    if (source.x - b >= 0 && source.y + a < n){
                        dests.add(new Coord(source.x - b, source.y + a));
                    }
                    if (source.x - b >= 0 && source.y - a >= 0){
                        dests.add(new Coord(source.x - b, source.y - a));
                    }
                    for (Coord d : dests){
                        if (dynLengths[source.x][source.y] + 1 < dynLengths[d.x][d.y]){
                            dynLengths[d.x][d.y] = dynLengths[source.x][source.y] + 1;
                            toCheck.offer(d);
                        }
                    }
                }
                retval[a-1][b-1] = dynLengths[n-1][n-1] != Integer.MAX_VALUE ? dynLengths[n-1][n-1] : -1;
            }
        }
        return retval;
    }

    private static String str(int[][] board){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                sb.append(String.valueOf(board[i][j]));
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] result = knightlOnAChessboard(n);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(String.valueOf(result[i][j]));

                if (j != result[i].length - 1) {
                    System.out.print(" ");
                }
            }

            if (i != result.length - 1) {
                System.out.println();
            }
        }

        System.out.println();

        scanner.close();
    }
}


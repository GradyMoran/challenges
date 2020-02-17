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

    // Complete the matrixRotation function below.
    static void matrixRotation(List<List<Integer>> matrix, int r) {
        //for each item in the array, step clockwise r steps (mod layer size), and print out the element there.
        for (int j = 0; j < matrix.size(); j++){
            for (int i = 0; i < matrix.get(0).size(); i++){
                Coord tmp = rotationDest(matrix.size(), matrix.get(0).size(), i, j, r);
                System.out.print(matrix.get(tmp.y).get(tmp.x));
                if (i < matrix.get(0).size() - 1) System.out.print(" ");
            }
            System.out.println();
        }
    }

    //m is number rows, or matrix.size()
    //n is number cols, or matrix.get(0).size()
    //i is "x coordinate", or how many spots to the right
    //j is "y coordinate", or how many spots down
    //r is rotations
    //note this actually does the rotation clockwise. This is to make it easier to find which index "becomes" the current after rotating ccw.
    static Coord rotationDest(int m, int n, int i, int j, int r){
        //do the ugly "step through the layer r steps and return where you are" because problem defintion
        //caps the matrix at 300 x 300, so this won't kill efficiency.
        int layer = arrayMin(new int[]{i, j, (n-1)-i, (m-1)-j});
        int layerSize = 2*((m-2*(layer)) + (n-2*(layer))) - 4;
        r = r % layerSize;

        int layerTop = layer; //row index for the top row of the rotation layer
        int layerLeft = layer;
        int layerBottom = m - 1 - layer;
        int layerRight = n - 1 - layer;
        Coord retval = new Coord(i, j);
        while (r > 0){
            //top row
            if (retval.y == layerTop && retval.x != layerRight){
                retval.x++;
            }
            //left column
            else if (retval.x == layerLeft && retval.y != layerTop){
                retval.y--;
            }
            //bottom row
            else if (retval.y == layerBottom && retval.x != layerLeft){
                retval.x--;
            }
            //right column
            else if (retval.x == layerRight && retval.y != layerBottom){
                retval.y++;
            }
            r--;
        }
        return retval;
    }

    //assumes list is not empty
    static int arrayMin(int[] a){
        int min = a[0];
        for (int i = 1; i < a.length; i++){
            min = Math.min(min, a[i]);
        }
        return min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] mnr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(mnr[0]);

        int n = Integer.parseInt(mnr[1]);

        int r = Integer.parseInt(mnr[2]);

        List<List<Integer>> matrix = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                matrix.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        matrixRotation(matrix, r);

        bufferedReader.close();
    }
}

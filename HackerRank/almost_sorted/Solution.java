import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    /*
    This problem sucks. Tons of opportunities for off-by-ones and a lot of corner cases. The only insight you can have is it's possible to determine the "out of place" numbers in O(n).
    */

    // Complete the almostSorted function below.
    static void almostSorted(int[] arr) {
        if (isSorted(arr)){
            System.out.println("yes");
            return;
        }

        //will make things simpler by putting -1 at the start and 1,000,001 at the end. (from problem limits)
        int [] arr2 = new int[arr.length+2];
        arr2[0] = -1;
        arr2[arr2.length-1] = 1000001;
        for (int i = 0; i < arr.length; i++){
            arr2[i+1] = arr[i];
        }

        int firstSwapIndex = -1;
        for (int i = 0; i < arr2.length-1; i++){
            if (arr2[i+1] < arr2[i]){
                firstSwapIndex = i;
                break;
            }
        }

        int secondSwapIndex = -1;
        for (int i = arr2.length-1; i > 0; i--){
            if (arr2[i] < arr2[i-1]){
                secondSwapIndex = i;
                break;
            }
        }


        if (firstSwapIndex == -1 || secondSwapIndex == -1){
            System.out.println("no");
        }

        //try swapping just the first and second swap indices
        int [] arr3 = Arrays.copyOf(arr2, arr2.length);
        int tmp = arr3[firstSwapIndex];
        arr3[firstSwapIndex] = arr3[secondSwapIndex];
        arr3[secondSwapIndex] = tmp;
        if (isSorted(arr3)){
            System.out.println("yes");
            System.out.println("swap " + firstSwapIndex + " " + secondSwapIndex);
            return;
        }

        //try reversing everything between firstSwap and secondSwap
        int [] arr4 = Arrays.copyOf(arr2, arr2.length);
        int j = 0;
        for (int i = firstSwapIndex; i <= secondSwapIndex; i++){
            arr4[i] = arr2[secondSwapIndex-j];
            j++;
        }
        if (isSorted(arr4)){
            System.out.println("yes");
            System.out.println("reverse " + firstSwapIndex + " " + secondSwapIndex);
            return;
        }
        System.out.println("no");
        
    }

    private static boolean isSorted(int[] arr){
        for (int i = 0; i < arr.length-1; i++){
            if (arr[i] > arr[i+1]) return false;
        }
        return true;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        almostSorted(arr);

        scanner.close();
    }
}


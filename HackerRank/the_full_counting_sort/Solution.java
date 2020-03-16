import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the countSort function below.
    static void countSort(List<List<String>> arr) {
        ArrayList<ArrayList<String>> retval = new ArrayList<ArrayList<String>>(); //will follow format of "sorted" in the input
        for (int i = 0; i < 100; i++){
            retval.add(new ArrayList<String>());
        }
        for (int j = 0; j < arr.size()/2; j++){
            int index = Integer.parseInt(arr.get(j).get(0));
            retval.get(index).add("-");
        }
        for (int j = arr.size()/2; j < arr.size(); j++){
            int index = Integer.parseInt(arr.get(j).get(0));
            retval.get(index).add(arr.get(j).get(1));
        }
        StringBuilder sb = new StringBuilder();
        for (ArrayList<String> thisIndexTmp : retval){
            for (String s : thisIndexTmp){
                sb.append(s);
                sb.append(" ");
            }
        }
        System.out.println(sb.substring(0, sb.length()-1));

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr.add(Arrays.asList(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")));
        }

        countSort(arr);

        bufferedReader.close();
    }
}


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
     * Complete the 'whatFlavors' function below.
     *
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY cost
     *  2. INTEGER money
     */

    public static void whatFlavors(List<Integer> cost, int money) {
        //strange inconsistency in problem description is that
        //a) there may be duplicate ice cream prices, and
        //b) there is one unique solution
        //so we are allowed to assume the duplicates do not appear in the solution? if they did, there would be two or more solutions... unless duplicates are only allowed when there are two duplicates and their value is equal to money/2
        //ignoring duplicates allows us to use a simpler cost->index data structure, so I will do that except for special case where the value seen is 1/2 of money.
        //also notice it expects lower index to be printed first
        HashMap<Integer,Integer> cost2Index = new HashMap<Integer,Integer>();
        int halfCostIndexOne = -1;
        int halfCostIndexTwo = -1;
        for (int i = 0; i < cost.size(); i++){
            cost2Index.put(cost.get(i), i);
            if (money % 2 == 0 && cost.get(i) == money/2){
                if (halfCostIndexOne == -1){
                    halfCostIndexOne = i;
                } else if (halfCostIndexTwo == -1){
                    System.out.println((halfCostIndexOne+1) + " " + (i+1));
                    return;
                }
            } else if (cost2Index.containsKey(money - cost.get(i))){
                System.out.println((cost2Index.get(money - cost.get(i))+1) + " " + (i+1));
                return;
            }
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int money = Integer.parseInt(bufferedReader.readLine().trim());

                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> cost = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                Result.whatFlavors(cost, money);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}

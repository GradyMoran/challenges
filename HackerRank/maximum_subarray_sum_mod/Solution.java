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
     * Complete the 'maximumSum' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. LONG_INTEGER_ARRAY a
     *  2. LONG_INTEGER m
     */

    public static long maximumSum(List<Long> a, long m) {
        /*Notes:
            (a+b)%m = (a%m)+(b%m)
            Naive algorithm is n^2, and n <= 10^5.
            What if we divide the array and return all possible amounts to add for each half? Nah, same efficiency as naive.
                Maybe not quite. When we do the recombine step, we can sort the possible sum values from each subarray. Then use some sort of modified binary search or smart iteration through the values on each side simultaneously to find the maximum possible mod sum in O(n)? (whereas trying to combine all possible subarray mod sums from both left and right subarrays is (n/2)^2)
            Let's just start by coding the naive implementation.
        */
        /*long max_modulo_sum = 0;
        long row_sum = 0;
        for (int i = 0; i < a.size(); i++){
            row_sum = 0;
            for (int j = i; j < a.size(); j++){
                row_sum += a.get(j);
                row_sum = row_sum % m;
                if (row_sum > max_modulo_sum) max_modulo_sum = row_sum;
            }
        }
        return max_modulo_sum;*/
        /*
        Nah, it's a hard problem, of course that didn't pass.
        Maybe recursive divide and conquer can be improved. When we do the recombine step, we can sort the possible sum values from each subarray. Then use some sort of modified binary search or smart iteration through the values on each side simultaneously to find the maximum possible mod sum in O(n)? (whereas trying to combine all possible subarray mod sums from both left and right subarrays is (n/2)^2)
        We're looking for the max of (sums mod m in each half) and (sums across the halves). we can find the max sum across the halves in O(n) by ???. The mod part of the problem is making it impossible to do a binary search. 
        For each array half, we can create a set of possible modulo sum values for subarrays that extend to the rightmost or leftmost edge of that half (i.e., candidates for subarrays that span across the halves). Then, we can sort the values in each of those sets. Start from largest value in both sets, and if it rolls over, are you allowed to assume it's not the max sum observed so far and move onto smaller numbers? If that assumption is true, you can do it in o(n) (after sorting, which would be nlogn)
        Need some identity that says x + y % m < x % m and y % m if x+y > m (in english, when you sum two numbers and they "roll over", then the "rolled over" value can never be higher than *both* the individual numbers). Is it true? If we're looking for the largest sum that's less than m, instead of the largest sum mod m, it's a different problem. I think it is true...
        The algorithm will be this:
        divide input array into two subarrays, and make recursive call (with same value for m)
        when combining the subarrays, find the sum mod values for the left side's subarrays that extend to the right (so that they can form contiguous subarrays with the right half, e.g., for 
        left: [1, 2, 3, 4]
        right: [5, 6, 7, 8]
        the subarray sums for left side would be [4, 7, 9, 10] and for the right would be [5, 11, 18, 26].
        then apply modulo for each of those, and sort the lists.
        iterate through both lists at the same time using some greedy algorithm to find largest sum of numbers in the two lists that is less than m. greedy algorithm is something like start at smallest value in one list and largest value in the other list, and if sum is too big, walk down with the larger value, and if too small, walk up with smaller value.
        */
        if (a.size() == 1){
            return a.get(0) % m;
        }
        List<Long> left = a.subList(0, a.size()/2);
        List<Long> right = a.subList(a.size()/2, a.size());
        long leftSum = maximumSum(left, m);
        long rightSum = maximumSum(right, m);
        long maxSum = leftSum > rightSum ? leftSum : rightSum;

        //get sorted lists of possible subarray sums spanning over middle (mod m) from left and right subarrays
        List<Long> leftSumValues = new ArrayList<Long>();
        List<Long> rightSumValues = new ArrayList<Long>();
        long prevSumValue = 0;
        for (int i = left.size()-1; i >= 0; i--){
            leftSumValues.add((prevSumValue + left.get(i)) % m);
            prevSumValue = (prevSumValue + left.get(i)) % m;
        }
        prevSumValue = 0;
        for (int i = 0; i < right.size(); i++){
            rightSumValues.add((prevSumValue + right.get(i)) % m);
            prevSumValue = (prevSumValue + right.get(i)) % m;
        }
        Collections.sort(leftSumValues);
        Collections.sort(rightSumValues);

        //then find largest sum of those two subarrays that's less than m using greedy algorithm
        int rightSumCounter = rightSumValues.size()-1;
        int leftSumCounter = 0;
        long maxLessThanM = 0;
        long tmp = 0;
        while (leftSumCounter < leftSumValues.size() && rightSumCounter >= 0){
            tmp = leftSumValues.get(leftSumCounter) + 
                     rightSumValues.get(rightSumCounter);
            if (tmp >= m){
                rightSumCounter--;
            } else {
                if (tmp > maxLessThanM) maxLessThanM = tmp;
                leftSumCounter++;
            }
        }
        return maxSum > maxLessThanM ? maxSum : maxLessThanM;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                long m = Long.parseLong(firstMultipleInput[1]);

                List<Long> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Long::parseLong)
                    .collect(toList());

                long result = Result.maximumSum(a, m);

                System.out.println(result);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}


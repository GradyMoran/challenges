import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    /*
    This problem could be solved in O(n) time by taking advantage of the fact the largest expenditure value is 200.
    That allows you to use a frequencies hash map to keep and maintain the median rather than the heaps used in my
    solution or sorted lists. This solution is O(nlogn). It is barely fast enough to pass all the tests (see commented
    out code in MedianSet.remove(), that little optimization helped pass the largest test), but does have the advantage
    that it does not require a lowish maximum expenditure value.
    */

    static class MedianSet{
        private PriorityQueue<Integer> lower;
        private PriorityQueue<Integer> higher;
        private double median;
        public MedianSet(){
            this.lower = new PriorityQueue<Integer>(Collections.reverseOrder());
            this.higher = new PriorityQueue<Integer>();
            this.median = 0.0;
        }

        public void add(int num){
            this.higher.add(num);
            this.rebalance();
        }

        public void remove(int num){
            /*if (this.higher.contains(num)){
                this.higher.remove(num);
            } else if (this.lower.contains(num)){
                this.lower.remove(num);
            }*/
            if (this.higher.size() > 0 && num >= this.higher.peek()){
                this.higher.remove(num);
            } else {
                this.lower.remove(num);
            }
            this.rebalance();
        }

        public int size(){
            return this.lower.size() + this.higher.size();
        }

        private void rebalance(){
            while (this.higher.size() > this.lower.size()){
                this.lower.add(this.higher.poll());
            }
            while (this.lower.size() > this.higher.size()){
                this.higher.add(this.lower.poll());
            }
            if (this.lower.size() > this.higher.size()){
                this.median = this.lower.peek();
            } else if (this.higher.size() > this.lower.size()){
                this.median = this.higher.peek();
            } else if (this.higher.size() > 0 && this.lower.size() > 0){
                this.median = ((double) this.higher.peek() + this.lower.peek()) / 2.0;
            }
        }

        public double getMedian(){
            return this.median;
        }

        public String toString(){
            return "Lower: " + lower + " higher: " + higher + " median: " + median;
        }
    }

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        int notifCount = 0;
        MedianSet m = new MedianSet();
        for (int i = 0; i < expenditure.length; i++){
            if (i >= d && ((int) (2.0 * m.getMedian())) <= expenditure[i]){
                notifCount++;
            }
            if (i >= d) m.remove(expenditure[i-d]);
            m.add(expenditure[i]);
        }
        return notifCount;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        System.out.println(String.valueOf(result));
        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();

        //bufferedWriter.close();

        scanner.close();
    }
}


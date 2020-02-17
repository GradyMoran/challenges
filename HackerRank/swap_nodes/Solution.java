import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

class Node {
    public int index;
    public Node left;
    public Node right;
    public Node(int i){
        this.index = i;
    }
}

public class Solution {

    /*
     * Complete the swapNodes function below.
     */
    static int[][] swapNodes(int[][] indexes, int[] queries) {
        /*
         * Write your code here.
         */
        Node root = makeTree(indexes);
        int[][] retval = new int[queries.length][indexes.length];
        for (int i = 0; i < queries.length; i++){
            swapRecurse(root, 1, queries[i]);
            List<Integer> tmp = inOrder(root);
            for (int j = 0; j < tmp.size(); j++){
                retval[i][j] = (int) tmp.get(j);
            }
        }
        return retval;
    }

    //if k is 1, then we swap the root's children. so curDepth needs to start at 1.
    static void swapRecurse(Node root, int curDepth, int k){
        if (root == null) return;
        if (curDepth % k == 0){
            Node tmp = root.left;
            root.left = root.right;
            root.right = tmp;
        }
        swapRecurse(root.left, curDepth + 1, k);
        swapRecurse(root.right, curDepth + 1, k);
    }

    static Node makeTree(int[][] indexes){
        //make a tmp hash to store all of our tree nodes, before going through and assigning children.
        Map<Integer,Node> nodeSet = new HashMap<Integer,Node>();
        for (int i = 1; i <= indexes.length; i++){
            nodeSet.put(i, new Node(i));
        }
        for (int i = 0; i < indexes.length; i++){
            Node tmp = nodeSet.get(i+1);
            if (indexes[i][0] != -1){
                tmp.left = nodeSet.get(indexes[i][0]);
            }
            if (indexes[i][1] != -1 ){
                tmp.right = nodeSet.get(indexes[i][1]);
            }
        }
        return nodeSet.get(1);
    }

    static List<Integer> inOrder(Node root){
        List<Integer> retval = new ArrayList<Integer>();
        if (root.left != null) retval.addAll(inOrder(root.left));
        retval.add(root.index);
        if (root.right != null) retval.addAll(inOrder(root.right));
        return retval;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] indexes = new int[n][2];

        for (int indexesRowItr = 0; indexesRowItr < n; indexesRowItr++) {
            String[] indexesRowItems = scanner.nextLine().split(" ");

            for (int indexesColumnItr = 0; indexesColumnItr < 2; indexesColumnItr++) {
                int indexesItem = Integer.parseInt(indexesRowItems[indexesColumnItr].trim());
                indexes[indexesRowItr][indexesColumnItr] = indexesItem;
            }
        }

        int queriesCount = Integer.parseInt(scanner.nextLine().trim());

        int[] queries = new int[queriesCount];

        for (int queriesItr = 0; queriesItr < queriesCount; queriesItr++) {
            int queriesItem = Integer.parseInt(scanner.nextLine().trim());
            queries[queriesItr] = queriesItem;
        }

        int[][] result = swapNodes(indexes, queries);

        for (int resultRowItr = 0; resultRowItr < result.length; resultRowItr++) {
            for (int resultColumnItr = 0; resultColumnItr < result[resultRowItr].length; resultColumnItr++) {
                System.out.print(String.valueOf(result[resultRowItr][resultColumnItr]));

                if (resultColumnItr != result[resultRowItr].length - 1) {
                    System.out.print(" ");
                }
            }

            if (resultRowItr != result.length - 1) {
                System.out.print("\n");
            }
        }
        System.out.println();
    }
}


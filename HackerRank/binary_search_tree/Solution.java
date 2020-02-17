/* Hidden stub code will pass a root argument to the function below. Complete the function to solve the challenge. Hint: you may want to write one or more helper functions.  

The Node class is defined as follows:
    class Node {
        int data;
        Node left;
        Node right;
     }
*/
    boolean checkBST(Node root) {
        return (root.left == null || checkBST(root.left)) &&
            (root.right == null || checkBST(root.right)) &&
            (root.left == null || maxData(root.left) < root.data) &&
            (root.right == null || minData(root.right) > root.data);
    }

    //assumes input is a BST
    int maxData(Node root){
        if (root.right == null) return root.data;
        return maxData(root.right);
    }

    int minData(Node root){
        if (root.left == null) return root.data;
        return minData(root.left);
    }

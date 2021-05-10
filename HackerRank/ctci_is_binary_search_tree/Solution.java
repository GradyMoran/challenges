/*
Unfortunately, a runtime error in a hidden code stub in the java version of this problem on hackerrank prevents this problem from being solved. Here is what I think would be the solution:
*/
    boolean checkBST(Node root) {
        return checkOrder(getInOrder(root));
    }

    boolean checkOrder(List<Integer> l){
        if (l.size() == 0) return true;
        int x = l.get(0);
        for (int i = 1; i < l.size(); i++){
            if (x >= l.get(i)){
                return false;
            }
            x = l.get(i);
        }
        return true;
    }

    List<Integer> getInOrder(Node root){
        if (root == null) return new ArrayList<Integer>();
        List<Integer> leftList = getInOrder(root.left);
        List<Integer> rightList = getInOrder(root.right);
        leftList.add(root.data);
        leftList.addAll(rightList);
        return leftList;
    }

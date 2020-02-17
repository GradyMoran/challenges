

/*  
	class Node
		public  int frequency; // the frequency of this tree
    	public  char data;
    	public  Node left, right;
    
*/ 

    void decode(String s, Node root) {
        String result = "";
        Node cur = root;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '0'){
                cur = cur.left;
            } else {
                cur = cur.right;
            }

            if (cur.left == null && cur.right == null){
                result = result + cur.data;
                cur = root;
            }
        }
        System.out.println(result);
    }



class HailstoneMax { 
    public static void main(String[] args) { 
        long a; 
        int j; 
        int max = 0; 
        int maxnr = 0; 
        for(int i = 1; i < 1000000; i++) { 
            a = i; 
            j = 1; 
            while( a != 1 ) { 
                a = ((a & 1) == 1) ? (3 * a + 1) : (a >> 1); 
                j++; 
            } if(j > max) { 
                max = j; maxnr = i; 
            } 
        } System.out.println(maxnr); 
    } 
}
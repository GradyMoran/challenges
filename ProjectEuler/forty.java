public class forty {
    public static void main(String[] args){
        StringBuilder s = new StringBuilder();
        for (int i = 1; i < 1000000; i++){
            s.append(Integer.toString(i));
        }
        String champ = s.toString();
        System.out.println(
                    (champ.charAt(0)-48) * 
                    (champ.charAt(9)-48) *
                    (champ.charAt(99)-48) *
                    (champ.charAt(999)-48) *
                    (champ.charAt(9999)-48) *
                    (champ.charAt(99999)-48) *
                    (champ.charAt(999999)-48));
    }
}// :(

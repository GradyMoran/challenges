import java.util.Scanner;
import java.io.File;
public class forty_two {
    public static void main(String[] args){
        Scanner f = new Scanner("");
        int count = 0;
        try{
            f = new Scanner(new File("/home/grady/workspace/stuff/ProjectEuler/non_java/words3.txt"));
        } catch (Exception e) {
            System.out.println("oh well");
            System.exit(1);
        }
        while (f.hasNextLine()){
            if (isTriangleWord(f.nextLine())) count++;
        }
        System.out.println(count);
    }
    
    public static boolean isTriangleWord(String in){
        int inVal = 0;
        for (int i = 0; i < in.length(); i++){
            inVal += in.toUpperCase().charAt(i) - 64;
        }
        for (int n = 1; 0.5 * n * (n+1) <= inVal; n++){
            if (0.5 * n * (n+1) == inVal) return true;
        }
        return false;
    }
}

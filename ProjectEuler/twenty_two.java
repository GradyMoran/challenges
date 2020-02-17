import java.io.*;
import java.util.*;
public class twenty_two {
    public static void main(String[] args){
        Scanner f;
        try{
            f = new Scanner(new File("e.txt"));
        } catch (Exception e) {
            System.out.println("oops");
            return;
        }
        
        int lineNo = 1;
        long sum = 0;
        while (f.hasNext()){
            sum += stringVal(f.next())*lineNo;
            lineNo++;
        }
        System.out.println(sum);
    }
    
    public static int stringVal(String input){
        int sum = 0;
        input = input.toUpperCase();
        for (int i = 0; i < input.length(); i++){
            sum += input.charAt(i) - 64;
        }
        return sum;
    }
}

import java.util.*;
public class fourteen {
    public static void main(String[] args){
        long nextN, tmp;
        ArrayList<Long> thisPath = new ArrayList<Long>();
        Map<Long, Integer> hasChecked = new HashMap<Long, Integer>(); //keys are nums, values are their path lengths
        hasChecked.put(new Long(1),0);

        OUTER:
        for (int n = 1; n < 1000000; n++){
            if (hasChecked.containsKey(n)) continue;
            thisPath.clear();
            //thisPath = new ArrayList<Integer>();
            thisPath.add(new Long(n));
            tmp = n;
            while (!hasChecked.containsKey(tmp)){
                nextN = tmp % 2 == 0 ? tmp/2 : (tmp*3)+1;
                if (hasChecked.containsKey(nextN)){ //if next number in our path has been visited and has known chain length
                    for (int i = 1; i <= thisPath.size(); i++){ //then add all numbers in this chain to hasChecked, and record their lengths
                        hasChecked.put(thisPath.get(thisPath.size()-i), hasChecked.get(nextN) + i);
                    }
                    thisPath.clear(); //??
                    continue OUTER;
                }
                else tmp = nextN;
                thisPath.add(tmp);
            }
        }
        
        //find largest path length, print out its value
        int thisNLength, maxKnownChainLength = 0, maxKnownChainStart = 0;
        for (int n = 1; n < 1000000; n++){
            thisNLength = hasChecked.get(new Long(n));
            if (thisNLength > maxKnownChainLength) {
                maxKnownChainLength = thisNLength;
                maxKnownChainStart = n;
            }
        }
        System.out.println("" + maxKnownChainStart);
    }
}

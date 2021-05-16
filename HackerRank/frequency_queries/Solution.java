import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        List<Integer> retval = new ArrayList<Integer>();
        HashMap<Integer, Integer> data = new HashMap<Integer, Integer>();
        HashMap<Integer, HashSet<Integer>> freqToData = new HashMap<Integer, HashSet<Integer>>();
        for (List q : queries){
            int num = (Integer) q.get(1);
            int op = (Integer) q.get(0);
            switch (op){
                case 1: 
                    if (data.containsKey(num)){
                        int oldFreq = data.get(num);
                        if (freqToData.containsKey(oldFreq+1)){
                            freqToData.get(oldFreq+1).add(num);
                        } else {
                            HashSet<Integer> tmpSet = new HashSet<Integer>();
                            tmpSet.add(num);
                            freqToData.put(oldFreq+1, tmpSet);
                        }
                        freqToData.get(oldFreq).remove(num);
                        data.put(num, oldFreq+1);
                    } else {
                        data.put(num, 1);
                        if (freqToData.containsKey(1)){
                            freqToData.get(1).add(num);
                        } else {
                            HashSet<Integer> tmpSet = new HashSet<Integer>();
                            tmpSet.add(num);
                            freqToData.put(1, tmpSet);
                        }
                    }
                    break;
                case 2:
                    if (data.containsKey(num)){
                        int oldFreq = data.get(num);
                        if (oldFreq == 1){
                            data.remove(num);
                            freqToData.get(1).remove(num);
                        } else {
                            data.put(num, data.get(num)-1);
                            //should always pass, since we always have to make a set for a certain frequency before moving to higher ones, so we never can "go down" to a lower frequency where a set doesn't already exist inside freqToData.
                            freqToData.get(oldFreq).remove(num);
                            freqToData.get(oldFreq-1).add(num);
                        }
                    }
                    break;
                case 3:
                    if (freqToData.containsKey(num) && freqToData.get(num).size() > 0){
                        retval.add(1);
                    } else {
                        retval.add(0);
                    }
                    break;
            }
        }
        return retval;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
            ans.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}


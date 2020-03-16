import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static class Astronaut implements Comparable{
        public int id;
        public TreeSet<Astronaut> comrades;
        public Astronaut(int id){
            this.id = id;
            comrades = new TreeSet<Astronaut>();
        }
        public String toString(){
            String retval = "id: " + id + " comrades: ";
            for (Astronaut comrade : comrades){
                retval = retval + comrade.id + " ";
            }
            return retval;
        }
        public int compareTo(Object o){
            Astronaut tmp = (Astronaut) o;
            if (tmp.id < this.id) return -1;
            if (tmp.id > this.id) return 1;
            return 0;
        }
    }

    // Complete the journeyToMoon function below.
    static long journeyToMoon(int n, int[][] astronaut) {
        //create the "graph" of astronauts like the problem is guiding us to.
        Map<Integer, Astronaut> tmpAstronauts = new HashMap<Integer, Astronaut>();
        for (int i = 0; i < n; i++){
            Astronaut tmp = new Astronaut(i);
            tmpAstronauts.put(i, tmp);
        }
        for (int p = 0; p < astronaut.length; p++){
            Astronaut a = tmpAstronauts.get(astronaut[p][0]);
            Astronaut b = tmpAstronauts.get(astronaut[p][1]);
            a.comrades.add(b);
            b.comrades.add(a);
        }

        //but to calculate the total number of pairs, it's simpler to me to create a list of sets
        //list (instead of set) will be so it is easier to iterate over
        //ArrayList<HashSet<Astronaut>> abc = new ArrayList<HashSet<Astronaut>>(); //abc = astronaut by country
        Set<Astronaut> tmpFound = new HashSet<Astronaut>();
        for (int i = 0; i < n; i++){
            if (!tmpFound.contains(tmpAstronauts.get(i))){
                TreeSet<Astronaut> tmpComrades = findAllComrades(tmpAstronauts.get(i));
                for (Astronaut a : tmpComrades){
                    a.comrades = tmpComrades;
                }
                tmpFound.addAll(tmpComrades);
            }
        }

        long retval = 0;
        for (int i = 0; i < n; i++){
            int foreignersCount = n-i;
            foreignersCount -= tmpAstronauts.get(i).comrades.headSet(tmpAstronauts.get(i)).size()+1;
            retval += foreignersCount;
        }
        return retval;
    }

    static TreeSet<Astronaut> findAllComrades(Astronaut yuri){
        return _findAllComrades(yuri, new HashSet<Astronaut>());
    }

    private static TreeSet<Astronaut> _findAllComrades(Astronaut yuri, Set<Astronaut> visited){
        visited.add(yuri);
        TreeSet<Astronaut> retval = new TreeSet<Astronaut>();
        retval.addAll(yuri.comrades);
        retval.add(yuri);
        for (Astronaut comrade : yuri.comrades){
            if (!visited.contains(comrade)){
                retval.addAll(_findAllComrades(comrade, visited));
            }
        }
        return retval;
    }

    static boolean containsAnywhere(ArrayList<HashSet<Astronaut>> abc, Astronaut yuri){
        for (Set<Astronaut> country : abc){
            if (country.contains(yuri)) return true;
        }
        return false;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] np = scanner.nextLine().split(" ");

        int n = Integer.parseInt(np[0]);

        int p = Integer.parseInt(np[1]);

        int[][] astronaut = new int[p][2];

        for (int i = 0; i < p; i++) {
            String[] astronautRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int astronautItem = Integer.parseInt(astronautRowItems[j]);
                astronaut[i][j] = astronautItem;
            }
        }

        long result = journeyToMoon(n, astronaut);

        System.out.println(result);

        scanner.close();
    }
}


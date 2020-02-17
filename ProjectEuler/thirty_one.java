public class thirty_one {
    public static void main(String[] args){
        //I ought to be shot for this
        long count = 8;
        for (int hundred = 0; hundred < 2; hundred++){
            for (int fifty = 0; fifty < 4; fifty++){
                for (int twenty = 0; twenty < 10; twenty++){
                    for (int ten = 0; ten < 20; ten++){
                        for (int five = 0; five < 40; five++){
                            for (int two = 0; two < 100; two++){
                                for (int one = 0; one < 200; one++){
                                    if (100*hundred + 50*fifty + 20*twenty + 10*ten + 5*five + 2*two + one == 200) count++;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }
}

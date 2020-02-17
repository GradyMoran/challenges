public class seventeen{
    public static void main(String[] args){
        //one two three four five six seven eight nine
        int[] ones = {0,3,3,5,4,4,3,5,5,4}; //zero (not counted)..nine(4 letters)
        //eleven twelve thirteen fourteen fifteen sixteen seventeen eighteen nineteen
        int[] teens = {0,6,6,8,8,7,7,9,8,8}; //eleven(6 letters)..nineteen(8 letters)
        //ten twenty thirty forty fifty sixty seventy eighty ninety
        int[] tens = {0,3,6,6,5,5,5,7,6,6}; //zero (not counted)..ninety
        String[] ones2 = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] teens2 = {"", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tens2 = {"", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        long sum = 0;
        StringBuilder t = new StringBuilder();
        for (int i = 1; i <= 999; i++){
            long prevSum = sum;
            sum += ones[i/100]; //letters for hundreds
            t.append(ones2[i/100]);
            if (i >= 100 && i < 1000 && (i % 100 != 0)) {
                t.append("hundredAnd");
                sum+=10;
            } else if (i % 100 == 0) {
                t.append("hundred");
                sum+=7;
            }
            if (i % 100 > 10 && i % 100 < 20){
                sum+=teens[i%100 - 10]; //eleven..nineteen
                t.append(teens2[i%100 - 10]);
            } else {
                sum+=tens[i%100/10]; //ex: forty
                sum+=ones[i%10]; //final ones
                t.append(tens2[i%100/10]);
                t.append(ones2[i%10]);
            }
            t.delete(0, t.length());
        }
        sum+="onethousand".length();
        System.err.println(sum);
    }
}

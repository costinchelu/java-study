package problems.coderbyte;

/*
 * Given an integer, return another integer that will be resulted from a look and say operation. Example:
 *
 * 1
 * will result in One of 1 . then 11
 *
 * 211554
 * will result in One of 2, Two of 1, Two of 5, One of 4 . then 12212514
 *
 * */
public class LookAndSaySequence {

    public static int LookSaySequence(int num) {

        char[] charArr = String.valueOf(num).toCharArray();

        StringBuilder sb = new StringBuilder();
        int counter = 0;

        for(int i = 0; i < charArr.length; i++) {
            if (i + 1 < charArr.length) {
                counter++;
                if (charArr[i] != charArr[i + 1]) {
                    sb.append(counter);
                    counter = 0;
                    sb.append(charArr[i]);
                }
            } else {
                counter ++;
                sb.append(counter);
                sb.append(charArr[i]);
            }

        }

        return Integer.parseInt(sb.toString());
    }


    public static void main(String[] args) {
        System.out.println(LookSaySequence(2466));
    }
}

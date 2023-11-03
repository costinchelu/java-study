package howto;

import java.io.*;
import java.util.Scanner;

public class UserInput {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

//        int someInt = sc.nextInt();
//        float someFloat = sc.nextFloat();
//        double someDouble = sc.nextDouble();
//        byte someByte = sc.nextByte();
//        String someString = sc.nextLine();
//        boolean someBoolean = sc.nextBoolean();

        sc.close();

        Scanner sc2;
        try {
            //sc2 = new Scanner(new FileInputStream("someFile.txt"), "UTF-8");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String line;

            while((line = br.readLine()) != null) {
                System.out.println(String.format("The input is: %s", line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

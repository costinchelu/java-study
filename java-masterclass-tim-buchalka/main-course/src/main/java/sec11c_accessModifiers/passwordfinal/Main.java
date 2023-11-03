package sec11c_accessModifiers.passwordfinal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int pw = enterPassword();
        Password password = new Password(pw);
        password.storePassword();


        //when trying to reveal the password:
//        Password stolenPassword = new ExtendedPassword(pw);
//        stolenPassword.storePassword();

        password.letMeIn(1);
        password.letMeIn(523266);
        password.letMeIn(9773);
        password.letMeIn(0);
        password.letMeIn(-1);
        password.letMeIn(674312);
    }


    private static int enterPassword() {
        System.out.println("Enter a new password");
        Scanner scan = new Scanner(System.in);

        int input;
        while(true) {
            try {
                input = scan.nextInt();
                if(input < 0 ) {
                    throw new InputMismatchException();
                }
                break;
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please reenter!");
                scan.nextLine();
            }
        }
        return input;
    }



}

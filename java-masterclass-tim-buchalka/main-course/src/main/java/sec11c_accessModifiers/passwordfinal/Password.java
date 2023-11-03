package sec11c_accessModifiers.passwordfinal;


public class Password {
    private static final int key = 748576362;   //used to encrypt password
    private final int encryptedPassword;


    public Password(int password) {
        this.encryptedPassword = encryptDecrypt(password);
    }

    private int encryptDecrypt(int password) {
        return password ^ key - key/5 + password % 100;
    }

    public final void storePassword() {
        System.out.println("Saving password as " + this.encryptedPassword + " in the encrypted form");
        //being final means it cannot be overwritten in an extended class
    }

    public boolean letMeIn(int password) {
        if(encryptDecrypt(password) == this.encryptedPassword) {
            System.out.println("Welcome");
            return true;
        } else {
            System.out.println("Nope, you cannot come in");
            return false;
        }
    }

}

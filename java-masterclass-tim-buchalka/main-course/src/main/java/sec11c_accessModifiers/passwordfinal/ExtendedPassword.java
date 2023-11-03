package sec11c_accessModifiers.passwordfinal;

public class ExtendedPassword extends Password {
    private int decryptedPassword;


    //we can try to override "StorePassword" method but being final an extended class with illicit purpose
    //cannot accomplish overriding

    public ExtendedPassword(int password) {
        super(password);
        this.decryptedPassword = password;
    }

//    @Override
//    public void storePassword() {
//        System.out.println("The password is " + this.decryptedPassword);
//    }

}

package sec11c_accessModifiers.accessmodifiers;

/*
1. public   = public everywhere
public int x
public class X {

2. package private = public in it's own package, private elsewhere
int x
class X {

3. protected = accessible from derived classes
protected int x

4. private = only accessible in the same class
private int x


interfaces can have only public static methods and public final variables
but interfaces can be public or package private so, it's own methods and vars can be
also public or package private

 */

public class Main {
    public static void main(String[] args) {
        Account timsAccount = new Account("Tim");
        timsAccount.deposit(1000);
        timsAccount.withdraw(500);
        timsAccount.withdraw(-200);
        timsAccount.deposit(-20);
        timsAccount.calculateBalance();

        System.out.println("Balance on account is " + timsAccount.getBalance());
    }
}

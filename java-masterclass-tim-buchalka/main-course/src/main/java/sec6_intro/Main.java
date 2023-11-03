package sec6_intro;

public class Main
{
    public static void main(String[] args)
    {
        // constructor implicit:
        Account bobsAccount = new Account(); // "12345", 0.00, "Bob Brown", "myemail@bob.com",
        // "(087) 123-4567");


        // constructor explicit:
        Account matsAccount = new Account("12345", 2100.55, "Mat",
                "mat@email.com", "0720 000 000");

        //constructor explicit cu 2 parametrii:
        Account johnsAccount = new Account("11002200", "John");







        Car porsche = new Car();
        // instanta tip Car declarata si initializata
        // () constructorul este cel implicit
        // atributele sunt null

        Car holden; // instanta tip Car declatata dar neinitializata (nu poate fi inca folosita)

        porsche.setModel("Carrera");

        System.out.println("Modelul obiectului porsche este " + porsche.getModel());
    }
}

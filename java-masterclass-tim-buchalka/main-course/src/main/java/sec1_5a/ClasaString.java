package sec1_5a;

public class ClasaString {

    public static void main (String[] args) {

        // tipul de data String (cu S mare) - nu e tip de data primitiv (e clasa)
        String myString = "This is a string";
        System.out.println(myString);
        // se pot face si operatii de concatenare
        myString += " and more...";
        System.out.println(myString);
        myString += "\u00A9 2019";
        System.out.println(myString);
        String lastString = "10";
    }
}

package sec1_5a;

/*

JRE (Java Runtime Environment)
		- necesar pentru a rula soft scris in Java (pentru utilizatori)

JDK (Java Development Kit)
		- instrumente pentru crearea de soft Java (pentru developeri)

folder SRC = source
New -> Java Class

fisierul cu extensia .class este codul compilat
fisierul cu extensia .java este codul sursa

*/

// tipuri primitive de date:

public class TipuriPrimitive {

    public static void main (String[] args) {
        // se poate folosi sout + tab (scurtatura)
        System.out.println("Hello World");

        // int stocheaza +- 2_147_483_647   (4 bytes)
        // numerele pot fi scrise cu _ pentru a le face mai vizibile. Ex: 10_000
        int myFirstNumber = (5 + 10) + (2 * 10);
        int mySecondNumber = 12;
        int myThirdNumber = myFirstNumber + 2;
        int myTotal = myFirstNumber + mySecondNumber + myThirdNumber;

        // byte stocheaza +- 127 (1 bytes)
        byte myByte = -128;

        // short stocheaza +- 32767 (2 bytes)
        short myShort = 32767;

        // long (8 bytes)
        long myLong = 100L;

        // typecasting (pentru ca intr-o expresie, myByte se transforma automat in int)
        // se face pentru conversii cu pierderi
        byte newMyByte = (byte) (myByte / 2);

        // float (6 zecimale) - 4 bytes,
        // double (16 zecimale) - 8 bytes
        float myFloatValue = 5.2F;
        double myDouble = 5.25D;

        // char (1 byte)
        // foloseste un caracter literal sau un unicode
        char myChar = 'A';
        char myChar2 = '\u00A9';

        // booleeni (1 byte) = true sau false
        boolean myBoolean = true;

        System.out.println(myChar);
        if (myBoolean == true) System.out.println(myChar2);
        System.out.println("Float & Double = " + myFloatValue + " & " + myDouble);
        System.out.println(myByte + newMyByte + myShort + myLong);
        System.out.println("myTotal = " + myTotal);
    }

}

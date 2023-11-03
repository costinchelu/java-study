package crypto;

import org.testng.annotations.Test;
import util.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingTest {

    public void hashText(String s) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        byte[] input = s.getBytes();
        byte[] digest = messageDigest.digest(input);
        System.out.println("Input: " + s);
        Utils.printByteArray("Digest", digest);
    }

    @Test
    public void hashingDemo() throws NoSuchAlgorithmException {
        System.out.println("Hash is a one way function:");
        hashText("Some text");

        System.out.println("Hash is deterministic (same input = same output):");
        hashText("Some text");

        System.out.println("Hash is pseudorandom (any small change in the input will completely change the output):");
        hashText("Some test");

        System.out.println("Hash is fixed in length:");
        hashText("This is a much longer text, but it will result in a same length hash.");
    }
}

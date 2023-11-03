package crypto;

import org.apache.commons.codec.binary.Hex;
import org.testng.annotations.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BitcoinMiningTest {

    @Test
    public void miningDemo() throws NoSuchAlgorithmException {
        int noOfIterations = 0;

        //setup block
        String blockData = " whole bunch of transactions ";

        MessageDigest digester = MessageDigest.getInstance("SHA-256");
        String blockWithRandomValue = blockData + noOfIterations;
        while (digester.digest(blockWithRandomValue.getBytes())[0] != 0) {
            System.out.println("block" + blockWithRandomValue + " has hash " + Hex.encodeHexString(digester.digest(blockWithRandomValue.getBytes())) + ", not good enough!");
            noOfIterations++;
            blockWithRandomValue = blockData + noOfIterations;
        }

        System.out.println("block " + blockWithRandomValue + " has hash " + Hex.encodeHexString(digester.digest(blockWithRandomValue.getBytes())));
        System.out.println("block took " + noOfIterations + " iterations to seal.");
    }
}

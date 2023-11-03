package crypto;

import org.testng.annotations.Test;
import util.Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class AsymmetricEncryptionRosaTest {

    @Test
    public void encryptShortTextWRsaTest()
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {

        KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
        kpGen.initialize(2048);
        KeyPair keyPair = kpGen.generateKeyPair();
        Utils.printByteArray("Private Key", keyPair.getPrivate().getEncoded());
        Utils.printByteArray("Public Key", keyPair.getPublic().getEncoded());

        byte[] inputText = "Fortune favors the brave.".getBytes();
        Utils.printText("Input text", inputText);

        Cipher cipher = Cipher.getInstance("RSA");

        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPrivate());
        byte[] encryptedText = cipher.doFinal(inputText);
        Utils.printByteArray("Cipher Text", encryptedText);

        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPublic());
        byte[] decodedText = cipher.doFinal(encryptedText);
        Utils.printText("Decoded Text", decodedText);
    }

    /** Don't use asymmetric encryption to encrypt large blocks of data (RSA in particular is slow)
     asymmetric enc. ca be used for: <p>
     - agreeing on symmetric keys <p>
     - encrypting / decrypting symmetric keys <p>
     - encrypting hashes or message digests (digital signature)
     */
    @Test(expectedExceptions = IllegalBlockSizeException.class)
    public void encryptLargeTextWithRsa()
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
            IllegalBlockSizeException, BadPaddingException, URISyntaxException {

        KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
        kpGen.initialize(2048);
        KeyPair keyPair = kpGen.generateKeyPair();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPrivate());

        byte[] inputText = this.getClass()
                        .getResourceAsStream("/largefile.txt")
                        .readAllBytes();


        byte[] encryptedText = cipher.doFinal(inputText);   // Data must not be longer than 245 bytes
    }
}

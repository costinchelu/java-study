package crypto;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;
import util.Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SymmetricEncryptionCBCTest {

    @Test
    public void testSymmetricEncryption()
            throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(192);    // we need 192 bits allowed for AES
        Key key = generator.generateKey();
        Utils.printByteArray("Symmetric key", key.getEncoded());

        // get IV
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] random = new byte[16];
        secureRandom.nextBytes(random);
        IvParameterSpec ivSpec = new IvParameterSpec(random);
        Utils.printByteArray("ivSpec", random);

        byte[] input = StringUtils.leftPad("Fortune favors the brave.", 256).getBytes();
        Utils.printText("Input text", input);

        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        byte[] encryptedOutput = cipher.doFinal(input);
        Utils.printByteArray("Cipher Text", encryptedOutput);


        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        byte[] decryptedOutput = cipher.doFinal(encryptedOutput);
        Utils.printText("Decoded input", decryptedOutput);
    }
}

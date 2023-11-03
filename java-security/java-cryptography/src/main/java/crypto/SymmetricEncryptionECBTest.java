package crypto;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;
import util.Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

// Electronic Codebook (ECB)
// not so good for binary data
public class SymmetricEncryptionECBTest {

    @Test
    public void testSymmetricEncryption()
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {

        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(192);    // we need 192 bits allowed for AES
        Key key = generator.generateKey();
        Utils.printByteArray("Symmetric key", key.getEncoded());

        byte[] input = StringUtils.leftPad("Fortune favors the brave.", 256).getBytes();
        Utils.printText("Input text", input);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedOutput = cipher.doFinal(input);
        Utils.printByteArray("Cipher Text", encryptedOutput);


        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedOutput = cipher.doFinal(encryptedOutput);
        Utils.printText("Decoded input", decryptedOutput);
    }
}

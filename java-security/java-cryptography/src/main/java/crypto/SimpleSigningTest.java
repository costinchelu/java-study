package crypto;


import org.testng.annotations.Test;
import util.Utils;

import java.security.*;

public class SimpleSigningTest {

    @Test
    public void testAsymmetricSigningWithSignatureClasses() throws GeneralSecurityException {

        KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
        kpGen.initialize(2048);
        KeyPair keyPair = kpGen.generateKeyPair();
        Utils.printByteArray("private key", keyPair.getPrivate().getEncoded());
        Utils.printByteArray("public key", keyPair.getPublic().getEncoded());

        String data = "Java is the best!!!";

        Signature signatureAlgorithm = Signature.getInstance("SHA256WithRSA");
        signatureAlgorithm.initSign(keyPair.getPrivate());
        signatureAlgorithm.update(data.getBytes());

        byte[] signature = signatureAlgorithm.sign();

        Utils.printByteArray("signature", signature);



        //verification on the other end
        String receivedData = "Java is the worst!!!";

        Signature verificationAlgorithm = Signature.getInstance("SHA256WithRSA");
        verificationAlgorithm.initVerify(keyPair.getPublic());
        verificationAlgorithm.update(receivedData.getBytes());

        boolean matches = verificationAlgorithm.verify(signature);
        System.out.println("signature matches: " + matches);
    }
}

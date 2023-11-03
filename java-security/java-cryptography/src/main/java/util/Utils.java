package util;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Key;
import java.security.Security;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {

    /**
     * Constructs a DOM document from a xml message in string format.
     *
     * @param xml the xml message.
     * @return the xml message as a DOM document.
     */
    public static Document getDocumentFromString(String xml) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(true);
        // Create the builder and parse the xml
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            return builder.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Document getDocument(String filename)
            throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(true);
        // Create the builder and parse the file
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(Utils.class.getResourceAsStream(filename));
    }

    public static void printText(String name, byte[] bytes) {
        System.out.println(name + ": "+ new String(bytes));
        System.out.println(name + "length: " + bytes.length + " bytes, " + bytes.length * 8 + " bits.");
        System.out.println("\r\n");
    }

    public static void printByteArray(String name, byte[] bytes) {
        System.out.println(name + ": "+ Hex.encodeHexString(bytes));
        System.out.println(name + " length: " + bytes.length + " bytes, " + bytes.length * 8 + " bits.");
        System.out.println("\r\n");
    }

    public static String byteArrayToHexString(Key key) {
        return byteArrayToHexString(key.getEncoded());
    }

    public static String byteArrayToHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (byte value : b) {
            int v = value & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }

    public static void loadProvider() {
        Security.addProvider(new BouncyCastleProvider());
    }
}
/*
    public static void printByteArray(String message, byte[] input) {
        StringBuilder sb = new StringBuilder();
        for (byte b : input) {
            sb.append(String.format("%02X", b));
        }
        System.out.println(message + ": " + sb + "\n" + message + " length: " + sb.length() * 4 + " bits" + "\n");
    }

    public static void printText(String message, byte[] input) {
        String s = new String(input, StandardCharsets.UTF_8).trim();
        System.out.println(message + ": " + s + "\n" + message + " length: " + input.length + " characters" + "\n");
    }
 */
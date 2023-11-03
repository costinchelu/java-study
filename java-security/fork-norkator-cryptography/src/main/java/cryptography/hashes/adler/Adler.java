package cryptography.hashes.adler;

import net.jacksum.JacksumAPI;
import net.jacksum.algorithms.AbstractChecksum;

import java.security.NoSuchAlgorithmException;

import static org.testng.AssertJUnit.assertEquals;

public class Adler {

	public static void main(String[] args) {
	}

	private static String jacksumAPI(String input, String instance) {
		String output = "";
		AbstractChecksum abstractChecksum = null;
		try {
			abstractChecksum = JacksumAPI.getChecksumInstance(instance.toLowerCase());
			abstractChecksum.update(input.getBytes());
			output = abstractChecksum.getValueFormatted();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return output;
	}

	public static String adler32(String input) {
		return jacksumAPI(input, "Adler32");
	}

}

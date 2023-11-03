package cryptography.hashes.has;

import java.security.NoSuchAlgorithmException;
import net.jacksum.JacksumAPI;
import net.jacksum.algorithms.AbstractChecksum;

public class HAS {

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

	public static String has160(String input) {
		return jacksumAPI(input, "HAS-160");
	}

}

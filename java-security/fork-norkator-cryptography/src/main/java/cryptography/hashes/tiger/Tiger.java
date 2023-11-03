package cryptography.hashes.tiger;

import java.security.NoSuchAlgorithmException;
import net.jacksum.JacksumAPI;
import net.jacksum.algorithms.AbstractChecksum;

public class Tiger {

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

	public static String tiger(String input) {
		return jacksumAPI(input, "Tiger");
	}

	public static String tiger2(String input) {
		return jacksumAPI(input, "Tiger2");
	}
	
	public static String tiger128(String input) {
		return jacksumAPI(input, "Tiger-128");
	}

	public static String tiger160(String input) {
		return jacksumAPI(input, "Tiger-160");
	}
}

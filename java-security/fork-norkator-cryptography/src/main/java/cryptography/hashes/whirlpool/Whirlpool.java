package cryptography.hashes.whirlpool;

import java.security.NoSuchAlgorithmException;
import net.jacksum.JacksumAPI;
import net.jacksum.algorithms.AbstractChecksum;

public class Whirlpool {

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

	public static String whirlpool0(String input) {
		return jacksumAPI(input, "Whirlpool-0");
	}

	public static String whirlpoolT(String input) {
		return jacksumAPI(input, "Whirlpool-T");
	}

	public static String whirlpool(String input) {
		return jacksumAPI(input, "Whirlpool");
	}

}

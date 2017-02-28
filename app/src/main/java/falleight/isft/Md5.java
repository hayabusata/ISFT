package falleight.isft;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {
	public Md5() {

	}

	public static String Md5_function(String plainText) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");

		md.update(plainText.getBytes());

		byte[] hashBytes = md.digest();

		int[] hashInts = new int[hashBytes.length];
		StringBuilder sb = new StringBuilder();
		for (int i=0; i < hashBytes.length; i++) {
			hashInts[i] = (int)hashBytes[i] & 0xff;
			if (hashInts[i] <= 15) {
				sb.append("0");
			}
			sb.append(Integer.toHexString(hashInts[i]));
		}

		String hashText = sb.toString();

		return hashText;
	}
}
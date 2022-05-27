package nttdata.javat1.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hashear contrasenyas para mayor seguridad
 * 
 * @author Adrián Cámara Muñoz
 *
 */
public class HashPasswd {
	
	private HashPasswd() {
		super();
	}

	/**
	 * get SecurePassword with SHA-512
	 * 
	 * @param passwordToHash password to hash
	 * @param salt           salt to hash
	 * @return password generated
	 */
	public static String hashIt(String passwordToHash, String salt) {
		String generatedPassword = null;

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes(StandardCharsets.UTF_8));
			byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			generatedPassword = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}
}
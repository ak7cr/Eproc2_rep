package ak.util;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PBKDF2Hasher {
	
	 private static final int ITERATIONS = 10000;
	    private static final int KEY_LENGTH = 256;

	    public static String hashPassword(String password, byte[] salt) throws Exception {
	        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
	        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	        byte[] hash = skf.generateSecret(spec).getEncoded();
	        return Base64.getEncoder().encodeToString(hash);
	    }
	    
	    public static byte[] getSalt() {
	        SecureRandom sr = new SecureRandom();
	        byte[] salt = new byte[16];
	        sr.nextBytes(salt);
	        return salt;
	    }

}

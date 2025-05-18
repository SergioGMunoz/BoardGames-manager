package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

	// Enlace investigación
	// https://www.baeldung.com/sha-256-hashing-java
	
	// Encripta la contraseña a sha-256
    public static String hashPassword(String original) {
        try {
        	MessageDigest digest = MessageDigest.getInstance("SHA-256");
        	byte [] hash = digest.digest(
        			original.getBytes(StandardCharsets.UTF_8));
     
        	StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al encriptar con hash SHA-256", e);
        }
    }
}

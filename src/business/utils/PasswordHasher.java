package business.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordHasher {
    private static final String ALGORITHM = "SHA-256";

    public static String hash(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
        byte[] encodedHash = digest.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(encodedHash);
    }
}

package service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * {@code EncryptionService} class
 *
 * @author Artyom
 */
public class EncryptionService {
    /**
     * Md5 Encryption of given {@code String}
     *
     * @param input parameter of type {@code String}
     * @return value which represents encrypted {@code String}
     * @throws NoSuchAlgorithmException when a cryptographic algorithm is requested
     *                                  but is not available in the environment
     */
    public static String getMd5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        StringBuilder hashText = new StringBuilder(no.toString(16));
        while (hashText.length() < 32) {
            hashText.insert(0, "0");
        }
        return hashText.toString();
    }
}
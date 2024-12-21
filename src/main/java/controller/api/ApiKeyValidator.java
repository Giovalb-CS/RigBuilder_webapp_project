package controller.api;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class ApiKeyValidator {

    private static final String STORED_API_KEY_HASH = "RF4miDMpEsb2ucKtt+V+13EJHMlXFzA7he/x0lr59OE=";

    public static boolean isApiKeyValid(String apiKey) {
        String hashedInputKey = hash(apiKey);
        return STORED_API_KEY_HASH.equals(hashedInputKey);
    }

    private static String hash(String key) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(key.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing error: SHA-256 algorithm not available.", e);
        }
    }

    public static void main(String[] args) {
        String apiKey = "1P9N112129";
        String hash = hash(apiKey);
        System.out.println("Hash della chiave API (da memorizzare): " + hash);
    }
}

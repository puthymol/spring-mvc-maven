package com.softvider.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class AESTest {

    private static final Logger log = LoggerFactory.getLogger(AESTest.class);

    @Test
    public void test() throws Exception {
        String code = sign("data to hash", "wingsecretkey");
        log.info(code);
        code = encrypt("data to encrypt", "wingsecretkey");
        log.info(code);
        code = decrypt("+t8DA1s/aUle8DTp5Y4kRQ==", "wingsecretkey");
        log.info(code);
    }

    public static String sign(String stringToHash, String key) throws InvalidKeyException, NoSuchAlgorithmException {
        Mac sha256 = Mac.getInstance("HmacSHA256");
        sha256.init(new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] hash = sha256.doFinal(stringToHash.getBytes(StandardCharsets.UTF_8));
        return byteToHex(hash);
    }

    public static String byteToHex(byte[] bytes) {
        StringBuilder buffer = new StringBuilder();
        for (byte aByte : bytes) {
            buffer.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return buffer.toString();
    }

    public static String encrypt(String strToEncrypt, String key) throws Exception {
        try {
            SecretKeySpec secretKey = generateKey(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            log.info("Error while encrypting: " + e.toString());
            throw new Exception(e.getMessage());
        }
    }

    public static String decrypt(String strToDecrypt, String key)  throws Exception {
        try {
            SecretKeySpec secretKey = generateKey(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            log.info("Error while decrypting: " + e.toString());
            throw new Exception(e.getMessage());
        }
    }

    public static SecretKeySpec generateKey(String key) throws Exception {
        try {
            byte[] keyByte;
            keyByte = key.getBytes(StandardCharsets.UTF_8);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            keyByte = sha.digest(keyByte);
            keyByte = Arrays.copyOf(keyByte, 16);
            return new SecretKeySpec(keyByte, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            throw new Exception(e.getMessage());
        }
    }
}

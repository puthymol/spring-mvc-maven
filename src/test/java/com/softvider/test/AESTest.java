package com.softvider.test;

import com.softvider.utils.AESHexUtils;
import com.softvider.utils.AESUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AESTest {
    private static final Logger log = LoggerFactory.getLogger(AESTest.class);

    @Test
    public void testAES() throws Exception {
        String plainText = "{\n" +
                "  \"name\":\"Puthy\",\n" +
                "  \"gender\":\"M\",\n" +
                "}";
        AESUtils aesUtils = new AESUtils();
        String encrypted = aesUtils.encrypt(plainText);
        log.info("encrypted <- {}", encrypted);
        String decrypted = aesUtils.decrypt(encrypted);
        log.info("decrypted <- {}", decrypted);
    }

    @Test
    public void testAESHex() throws Exception {
        String plainText = "{\n" +
                "  \"name\":\"Puthy\",\n" +
                "  \"gender\":\"M\",\n" +
                "}";
        AESHexUtils aesHexUtils = new AESHexUtils();
        String encrypted = aesHexUtils.encrypt(plainText);
        log.info("encrypted <- {}", encrypted);
        String decrypted = aesHexUtils.decrypt(encrypted);
        log.info("decrypted <- {}", decrypted);
    }
}

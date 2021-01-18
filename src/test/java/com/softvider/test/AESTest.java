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
                "  \"account_number\":\"0965270842\",\n" +
                "  \"sender_id\":\"3311223482309755\",\n" +
                "  \"lang\":\"en\",\n" +
                "  \"service_type\":\"CB_CHECK_BALANCE\",\n" +
                "  \"source\":\"facebook\",\n" +
                "  \"recipient_id\": \"101427694631095\"\n" +
                "}";
        AESUtils aesUtils = new AESUtils();
        String encrypted = aesUtils.encrypt(plainText);
        log.info("encrypted <- {}", encrypted);
        String decrypted = aesUtils.decrypt(encrypted);
        log.info("decrypted <- {}", decrypted);
        String javascript = "oEI27QOkvsnLlnTy0VpzpEm9Jz912qgDOilMoktm8DLJVcYM5Frc+czVMst+OwXww2cnVBQQj5CC4pcjml7gsFnWm2Sty867GKyeonT0Ie19wS/xCBFVgIVvx9zTEbUdJUnNnTV8zmn7OdDFW0AyHGhQh2tMYKQwsoo1+LDV5O9CCNewxdMHq7+ItylVrA5fepoQAWEEa/6nSP506rXIDZH9Xod9GqvnSa3AurxzmNqA1dMVHt5KzhQodq6FLdEt";
        decrypted = aesUtils.decrypt(javascript);
        log.info("decrypted from javascript <- {}", decrypted);
    }

    @Test
    public void testAESHex() throws Exception {
        String plainText = "{\n" +
                "  \"account_number\":\"0965270842\",\n" +
                "  \"sender_id\":\"3311223482309755\",\n" +
                "  \"lang\":\"en\",\n" +
                "  \"service_type\":\"CB_CHECK_BALANCE\",\n" +
                "  \"source\":\"facebook\",\n" +
                "  \"recipient_id\": \"101427694631095\"\n" +
                "}";
        AESHexUtils aesHexUtils = new AESHexUtils();
        String encrypted = aesHexUtils.encrypt(plainText);
        log.info("encrypted <- {}", encrypted);
        String decrypted = aesHexUtils.decrypt(encrypted);
        log.info("decrypted <- {}", decrypted);
        String javascript = "a2KZ5UZ2sTz99W5o7UbGhNIQumP8di2QfOw65FD6Ynxw3SMdLU/c9ti5YI3XOmLqvi7nkFgyDCgKlXFNWGpA6AXrfQPhaOfryteAEsnI1wvl0JxJyuCLwGcf3gUNkrlG89nGlCSeWQKbZzb83b84HqlPZlCT/ubTvLM7FP4CmgGEB7f4KWKGxBCYzXeTblhn9DOHLruu+9lxabr2uvlJ/r6y1vZ5YtW0CnjlH1BPbvLk0UBAdvYKz+PqOnKvg+Cj";
        decrypted = aesHexUtils.decrypt(javascript);
        log.info("decrypted from javascript <- {}", decrypted);
    }
}

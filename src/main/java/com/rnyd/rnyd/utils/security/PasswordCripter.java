package com.rnyd.rnyd.utils.security;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class PasswordCripter {

    @Value("${secret.key}")
    private String secretKeyRaw;

    private SecretKeySpec secretKey;

    @PostConstruct
    public void init() {
        byte[] key = secretKeyRaw.getBytes();
        secretKey = new SecretKeySpec(key, 0, 16, "AES");
    }

    public String hashPassword(String password) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error encriptando la contraseña", e);
        }
    }

    public String decryptPassword(String encryptedPassword) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
            return new String(decrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error desencriptando la contraseña", e);
        }
    }
}

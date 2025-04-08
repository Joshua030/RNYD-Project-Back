package com.rnyd.rnyd.utils.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/*public class PasswordCripter {
    public static String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // TODO No funciona correctamente
    public static boolean checkPassword(String password, String hashPassword){
        return BCrypt.checkpw(password, hashPassword);
    }
}*/


//@Component
public class PasswordCripter{

    private static final String ALGORITHM = "AES";

    // Clave maestra fija (debe tener exactamente 16 caracteres para AES-128)
    //@Value("${secret.key}")
    private static final String SECRET_KEY = "ClaveMastraRNYD!";

    private static SecretKeySpec getSecretKey(){
        return new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
    }

    public static String hashPassword(String password){
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
            byte[] encrypted = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error al encriptar la contraseña", e);
        }
    }

    public static String decryptPassword(String password){
        try{
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey());
            byte[] decoded = Base64.getDecoder().decode(password);
            byte[] decrypted = cipher.doFinal(decoded);
            return new String(decrypted);
        }catch (Exception e ){
            throw new RuntimeException("Error al desencriptar la contraseña", e);
        }
    }


}

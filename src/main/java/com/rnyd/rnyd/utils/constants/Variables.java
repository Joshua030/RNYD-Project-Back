package com.rnyd.rnyd.utils.constants;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

public class Variables {
     public final static SecretKey SECRET_KEY_GEN = Keys.secretKeyFor(SignatureAlgorithm.HS256);
     public final static long EXPIRATION_TIME = 86400000; // 1 dia en milisegundos
     public final  static String
     USER_EMAIL_ALREADY_EXISTS = "Este correo ya está en uso.";
     public final static String USER_EMAIL_DOES_NOT_EXISTS = "Este correo no existe.";
     public final static String CURRENCY = "eur";
     // TODO ESTO NO FUNCIONA NI SIRVE, ELIMINAR Y HASHEAR
     public final static String API_KEY = "1234";




}
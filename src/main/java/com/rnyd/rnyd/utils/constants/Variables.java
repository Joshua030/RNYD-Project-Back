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
     public final static String DIET_CREATED = "Diet was created successfuly.";
     public final static String DIET_NOT_CREATED = "Error while creating Diet.";
     public final static String DIET_UPDATED = "Diet was updated successfuly.";
     public final static String DIET_NOT_UPDATED = "Error while updating Diet.";
     public final static String DIET_DELETED = "Diet was deleted successfuly.";
     public final static String DIET_NOT_DELETED = "Error while deleting Diet.";
     public final static String DIET_ASSIGNED = "Diet was assigned successfuly.";
     public final static String DIET_NOT_ASSIGNED = "Error while assgning Diet.";




}
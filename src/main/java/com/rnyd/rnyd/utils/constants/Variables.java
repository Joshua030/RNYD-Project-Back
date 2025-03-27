package com.rnyd.rnyd.utils.constants;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

public class Variables {
     public final static SecretKey SECRET_KEY_GEN = Keys.secretKeyFor(SignatureAlgorithm.HS256);
     public final static long EXPIRATION_TIME = 86400000; // 1 dia en milisegundos
}
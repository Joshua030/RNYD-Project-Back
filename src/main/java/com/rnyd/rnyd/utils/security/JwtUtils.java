package com.rnyd.rnyd.utils.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.function.Function;

import static com.rnyd.rnyd.utils.constants.Variables.EXPIRATION_TIME;
import static com.rnyd.rnyd.utils.constants.Variables.SECRET_KEY_GEN;

@Component
public class JwtUtils {

    public String generateJWT(String user, String role) {
        return Jwts.builder() // Vamos a crear este objeto String con el patron Builder
                .setSubject(user) // Seteamos la variable Subject (usuario) con nuestro usuario (email en este caso), debe ser String
                .claim("role", role) // Creamos y seteamos el valor de la variable 'role' que sera propia del token
                .setIssuedAt(new Date()) // Seteamos la fecha de creacion
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Seteamos el tiempo de expiracion
                .signWith(SECRET_KEY_GEN, SignatureAlgorithm.HS256) // Firmamos con la llave generada y usamos el algoritmo HS256
                .compact(); // Es igual que si pusieremos .build() en patron Builder
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY_GEN)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token, String email) {
        return extractEmail(token).equals(email) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}

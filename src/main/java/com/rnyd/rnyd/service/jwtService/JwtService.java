package com.rnyd.rnyd.service.jwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

import static com.rnyd.rnyd.utils.constants.Variables.EXPIRATION_TIME;
import static com.rnyd.rnyd.utils.constants.Variables.SECRET_KEY_GEN;

@Service
public class JwtService {


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY_GEN)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String generateToken(String user, String role) {
        return Jwts.builder() // Vamos a crear este objeto String con el patron Builder
                .setSubject(user) // Seteamos la variable Subject (usuario) con nuestro usuario (email en este caso), debe ser String
                .claim("role", role) // Creamos y seteamos el valor de la variable 'role' que sera propia del token
                .setIssuedAt(new Date()) // Seteamos la fecha de creacion
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Seteamos el tiempo de expiracion
                .signWith(SECRET_KEY_GEN, SignatureAlgorithm.HS256) // Firmamos con la llave generada y usamos el algoritmo HS256
                .compact(); // Es igual que si pusieremos .build() en patron Builder
    }
}

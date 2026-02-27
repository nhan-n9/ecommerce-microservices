package com.example.gateway.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class JwtService {
    @Value("${application.config.jwt.secret}")
    private String jwtSecret;

    public void validateToken(final String token) {
        Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token);
    }

    // generate signing key ()
    private SecretKey getKey() {
        // convert the key to byte array
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

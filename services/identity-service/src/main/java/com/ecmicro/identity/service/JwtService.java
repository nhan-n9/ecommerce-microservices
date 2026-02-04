package com.ecmicro.identity.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {
    @Value("${application.config.jwt.secret}")
    private String jwtSecret;

//    public JWTService() throws NoSuchAlgorithmException {
//        // choose algorithm
//        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
//        // generate the secret key, type: SecretKey
//        SecretKey sk = keyGen.generateKey();
//        // encode to Base64 string
//        secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
//    }

    public String generateToken(String username) {
        // store (header's payload & signature) as key-value pairs
        Map<String, Object> claims = new HashMap<>();

        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts
                .builder()
                .claims()
                .add(claims)    // add the claims Map in
                .subject(userName)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 30 * 1000))
                .and()
                .signWith(getKey())     // generate a key to sign
                .compact();
    }

    // generate signing key ()
    private SecretKey getKey() {
        // convert the key to byte array
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public void validateToken(String token) {
        Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token);
    }

    public Boolean validateToken(String token, String username) {
        Claims claims = Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        String tokenUsername = claims.getSubject();

        return (tokenUsername.equals(username) && isTokenExpired(token));
    }

    // extract all claims from token
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getKey())   // get key to decode the token -> verify
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // extract specific claim from token
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        // apply the function to get the specific claim
        return claimResolver.apply(claims);
    }

    // extract username from token
    public String extractUsernameFromToken(String token) {
        // extract the 'subject' claim (which is the username)
        return extractClaim(token, Claims::getSubject);
    }

    // extract expiration date from token
    private Date extractExpirationFromToken(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpirationFromToken(token).before(new Date());
    }
}

package com.jwtworking.Util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Jwts.SIG;

import javax.crypto.SecretKey;

@Component
public class JwtHelper {
    private final String SECRET_KEY = "rajkumarspringboottokensecretkey1234hellomynameisrajksharma";

    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;  // 10hours

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // generate Token
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();

    }
    public String getUsernameFromToken(String token) {
        return Jwts.parser().verifyWith((SecretKey) key).build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    //  Token valid hai ya nahi
    public boolean isTokenValid(String token, String username) {
        String extractedUsername = getUsernameFromToken(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    //  Check token expire to nahi hua
    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().verifyWith((SecretKey) key).build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        return expiration.before(new Date());
    }
}

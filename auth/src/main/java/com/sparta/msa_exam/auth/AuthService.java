package com.sparta.msa_exam.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Service
public class AuthService {


    private final String issuer;
    private final Long accessExpiration;
    private final SecretKey secretKey;

    public AuthService(
            @Value("${spring.application.name}") String issuer,
            @Value("${service.jwt.access-expiration}") Long accessExpiration,
            @Value("${service.jwt.secret-key}") String secretKey
    ) {
        this.issuer = issuer;
        this.accessExpiration = accessExpiration;
        this.secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey));
    }

    public String createToken(String user_id, String role) {
        long now = System.currentTimeMillis();

        return Jwts.builder()
                .claim("user_id", user_id)
                .claim("role", role)
                .issuer(issuer)
                .issuedAt(new Date(now))
                .expiration(new Date(now + accessExpiration))
                .signWith(secretKey)
                .compact();
    }
}

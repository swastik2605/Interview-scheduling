package com.interview.Interview_Service.auth.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.security.KeyRep.Type.SECRET;

@Service
public class JwtService {

    private final String SecretKey = "myverysecuresecretkeyforjwtauthentication12345";
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60)
                )
                .signWith(SignatureAlgorithm.HS256, SecretKey)
                .compact();
    }

    public String extractUsername(String token) {

        return Jwts.parser()
                .setSigningKey(SecretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}

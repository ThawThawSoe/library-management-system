package com.example.library.core.util;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


public class JwtUtil {
	private static final String SECRET_KEY_STRING  = "r9KbQf8Wm+2vM9fTz2xq2Sm8I6dHtk7VvFbGcQzQ2iU="; 
	private static final SecretKey SECRET_KEY =
	        Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY_STRING));
	
	public static String generateToken(String username) {
        long expirationMillis = 1000 * 60 * 60; // 1 hour
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY_STRING ));
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
	
	public static boolean validateToken(String token) {
        try {
        	Jwts.parser()
            .verifyWith(SECRET_KEY)
            .build()
            .parseSignedClaims(token);        	
        	
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
        
}

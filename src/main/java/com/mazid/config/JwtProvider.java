package com.mazid.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;

// Class to handle JWT token generation and validation
public class JwtProvider {

    // Secret key for signing JWT tokens, derived from the constant
    private static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    // Generates a JWT token for authenticated users
    public static String generateToken(Authentication auth){

        String jwt = Jwts.builder()
                .setIssuer("mazid26")// Sets the issuer of the token
                .setIssuedAt(new Date())// Sets the issue date
                .setExpiration(new Date(new Date().getTime()+86400000))// Sets token expiration (24 hours)
                .claim("email",auth.getName())// Adds email claim
                .signWith(key)// Signs the token with the secret key
                .compact();

        return jwt;
    }
    // Extracts email information from a JWT token
    public static String getEmailFromJwtToken(String jwt){
        // Removes "Bearer " prefix
        jwt = jwt.substring(7);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key).build().parseClaimsJws(jwt).getBody();// Parses the token

        String email = String.valueOf(claims.get("email"));// Retrieves the email claim



        return email;
    }
}

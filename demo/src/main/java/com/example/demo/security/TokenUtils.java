package com.example.demo.security;

import com.example.demo.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;

@Component
public class TokenUtils implements Serializable {
    private static final long serialVersionUID = -3L;

    //Token expiration time
    private static final Long EXPIRATION = 604800L;

    /* Use an assistant method to create a random key
        Need to be replaced when deploying.
        HS256 is used here since RS256 must have both private and public key
     */
    Key aRandomKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    /** Generate a token. Must setAudience and setExpiration and set role
     * @param user
     * @return token or null
     */
    public String createToken(User user) {
        try {
            // Set expiration time of TOKEN
            Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
            // Generate Token
            String token = Jwts.builder()
                    // set token receiver
                    .setAudience(user.getUsername())
                    // set expiration time
                    .setExpiration(expirationDate)
                    // set token generation time
                    .setIssuedAt(new Date())
                    // Set value in "claim" part
                    // Add priority information in Claim
                    .claim("priority", user.getPriority())
                    // set private key and algorithm

                    /*
                        //Can't use right now for the RSA.getPrivateKey() problem
                          Need to be fixed in the future

                        .signWith(RSA.getPrivateKey(), SignatureAlgorithm.RS256)
                    */
                    .signWith(aRandomKey)
                    .compact();
            //System.out.println("Generated Token:"+token);
            return String.format(token);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /** Verify token and get priority and user information
     * @param token your Token
     * @return user
     */
    public User validationToken(String token) {
        try {
            // Decode Token，get "Claims" part
            Claims claims = Jwts.parserBuilder()
                    /*  Doesn't work for the RSA.getPublicKey() problem
                        Need to be fixed in the future

                        .setSigningKey(RSA.getPublicKey())
                    */

                    // Use our random key to decode
                    .setSigningKey(aRandomKey)
                    .build().parseClaimsJws(token).getBody();
            System.out.println("Token: parsed Claims part: "+claims);
            //System.out.println("Priority:"+claims.get("priority"));
            assert claims != null;
            // Verify expiration time
            Date expiration = claims.getExpiration();
            if (!expiration.after(new Date())) {
                System.out.println("Token has expired!");
                return null;
            }
            User user = new User();
            user.setUsername(claims.getAudience());
            // Casting priority string to integer
            Integer priority = Integer.valueOf(claims.get("priority").toString());
            user.setPriority(priority);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Token validation failed!");
            return null;
        }
    }
}


package com.example.demo.security;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


// All this part can't work properly
// Need to be fixed later
public class RSA {

    public static PrivateKey getPrivateKey() {
        PrivateKey privateKey = null;
    try {
        // Customized your private KEY here
        String privateKeyStr = "private key";
        // PKCS 8 KEY generation

        // Original keySpec generator, can't work
        //PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyStr));
        // My own keySpec generator, doesn't work either
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getEncoder().encode(privateKeyStr.getBytes()));

        System.out.println(Base64.getEncoder().encodeToString(privateKeyStr.getBytes()));
        System.out.println(keySpec.toString());
        // RSA 算法
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
        e.printStackTrace();
    }
    return privateKey;
}

    public static PublicKey getPublicKey() {
        PublicKey publicKey = null;
        try {
            String publicKeyStr = "public key";
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyStr));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.averagejoedev.utils;

import com.averagejoedev.models.constant.ConstGlobal;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONObject;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by voncount on 7/15/16.
 */
public class EncryptionUtils {

    /**
     * Encrypt json data to JWT format
     * @param data json string data
     * @return JWT token with body is encrypted data
     */
    public static String encrypt(String data) {
        return Jwts
                .builder()
                .setSubject(data)
                .signWith(SignatureAlgorithm.HS512, ConstGlobal.ENCRYPTION_KEY.getBytes())
                .compact();
    }

    /**
     * Decrypt JWT data to text
     * @param data encrypted JWT token
     * @return unencrypted body of the data token
     */
    public static String decrypt(String data) {
        return Jwts
                .parser()
                .setSigningKey(ConstGlobal.ENCRYPTION_KEY.getBytes())
                .parseClaimsJws(data)
                .getBody()
                .getSubject();
    }

    /**
     * Generate user token after activate or login
     * Token expires after 30 days
     * @param authId
     * @param authUsername
     * @return
     */
    public static String generateToken(int authId, String authUsername) {

        JSONObject jwt = new JSONObject();
        jwt.put("id",       authId);
        jwt.put("username", authUsername);
        jwt.put("iat",      DateTimeUtils.now());
        jwt.put("exp",      DateTimeUtils.addTime(30, Calendar.DAY_OF_YEAR));

        return EncryptionUtils.encrypt(jwt.toString());
    }

    /**
     * Hash input
     * @param data raw data
     * @return base64 format of hashed data
     */
    public static String hash(String data) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        sha.update(data.getBytes());
        return Base64.getEncoder().encodeToString(sha.digest());
    }

    /**
     * Hash password
     * @param username raw username
     * @param password raw password
     * @return base 64 format of hashed data
     */
    public static String hashPassword(String username, String password) {
        try {
            return EncryptionUtils.hash(username + password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Cannot hash password");
        }
    }

    /**
     * Generate public/private keypair
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static Map<String, String> generateKey() throws NoSuchAlgorithmException {

        // init algorithms
        KeyPairGenerator generator  = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);

        // generate key pair
        KeyPair pair = generator.genKeyPair();

        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        Map<String, String> result = new HashMap<>(2);
        result.put("privateKey", Base64.getEncoder().encodeToString(privateKey.getEncoded()));
        result.put("publicKey", Base64.getEncoder().encodeToString(publicKey.getEncoded()));

        return result;
    }

    /**
     * Encrypt data using public key
     * @param publicKey
     * @param message
     * @return
     */
    public static String encryptUsingKey(String publicKey, byte[] message) {
        try {
            PublicKey key = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey)));

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            return Base64.getEncoder().encodeToString(cipher.doFinal(message));
        } catch (Exception e) {
            throw new RuntimeException("Cannot encrypt, caused by " + e.getMessage());
        }
    }

    /**
     * Decrypt data using private key
     * @param privateKey
     * @param message
     * @return
     */
    public static String decryptUsingKey(String privateKey, byte[] message) {
        try {
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
            PrivateKey key = KeyFactory.getInstance("RSA").generatePrivate(spec);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);

            return new String(cipher.doFinal(message));
        } catch (Exception e) {
            throw new RuntimeException("Cannot decrypt, caused by " + e.getMessage());
        }
    }

}

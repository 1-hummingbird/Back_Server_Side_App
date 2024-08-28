package com.hummingbird.kr.starbuckslike.auth.application;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Cryptor {
    private static final String Algo = "AES";

    public static String encrypt(String data, SecretKey key) throws Exception{
        Cipher cipher = Cipher.getInstance(Algo);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }
    public static SecretKey generateKey() throws Exception{
        KeyGenerator keyGen = KeyGenerator.getInstance(Algo);
        keyGen.init(256); // AES-256 암호화 호출할 거에요
        return keyGen.generateKey();
    }

    public static String decrypt(String encryptedData, SecretKey key) throws Exception{
        Cipher cipher = Cipher.getInstance(Algo);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decoded = Base64.getDecoder().decode(encryptedData);
        byte[] decrypteData = cipher.doFinal(decoded);
        return new String(decrypteData);
    }
}
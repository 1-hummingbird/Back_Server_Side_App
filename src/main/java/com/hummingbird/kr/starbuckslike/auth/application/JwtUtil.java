package com.hummingbird.kr.starbuckslike.auth.application;

import com.hummingbird.kr.starbuckslike.auth.config.JwtSecrets;

import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.security.*;
import java.security.interfaces.*;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jose.jwk.*;

@Component
public class JwtUtil {

    RSAPrivateKey privateKey = JwtSecrets.getPrivateKey();
    RSAPublicKey publicKey = JwtSecrets.getPublicKey();

    // nimbusds's jwt claim default set's data
    String jwtissuer = "Team Hummingbird in Spharos Academy 5th";
    List<String> audience = Arrays.asList("kr.starbuckslike.front","kr.starbuckslike.api");

     JWEHeader header = new JWEHeader(
             JWEAlgorithm.RSA_OAEP_256,
             EncryptionMethod.A128GCM
     );

     public String issueJwt(String userUID, String Priv){

         Date now = new Date();

         JWTClaimsSet jwtClaims = new JWTClaimsSet.Builder()
                 .issuer(jwtissuer)
                 .subject(userUID)
                 .audience(audience)
                 .notBeforeTime(now)
                 .issueTime(now)
                 .jwtID(UUID.randomUUID().toString())
                 .build();
     }

}
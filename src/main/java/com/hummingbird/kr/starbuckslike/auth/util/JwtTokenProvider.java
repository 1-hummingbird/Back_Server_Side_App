package com.hummingbird.kr.starbuckslike.auth.util;

import com.hummingbird.kr.starbuckslike.auth.application.TokenService;
import com.hummingbird.kr.starbuckslike.auth.config.JwtSecrets;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

// Spring Security oauth2-authorization-server project's JWT provider utility class
// maybe will expend someday, our server acting Auth server in MSA architecture or something
@Component
public class JwtTokenProvider {

    RSAPrivateKey privateKey = JwtSecrets.getPrivateKey();
    RSAPublicKey publicKey = JwtSecrets.getPublicKey();

    private final TokenService tokenService;

    // nimbusds's jwt claim default set's data
    String jwtissuer = "Team Hummingbird in Spharos Academy 5th";
    List<String> jwtaudience = Arrays.asList("kr.starbuckslike.front", "kr.starbuckslike.api");

    //Hint of JWT token Encryption type
    // RSA-EAEP-256 + ASE/GCM 128
    JWEHeader header = new JWEHeader(
            JWEAlgorithm.RSA_OAEP_256,
            EncryptionMethod.A128GCM
    );

    public JwtTokenProvider(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    // todo: refresh token system
    public String issueRefresh(int requestedExpiration, String authJWT) {

        Date now = new Date();
        String userUID = getClaimOfToken(authJWT, "subject").toString();

        //Create JWT claims
        JWTClaimsSet jwtClaims = new JWTClaimsSet.Builder()
                .issuer(jwtissuer)
                .subject(userUID)
                .audience(jwtaudience)
                .notBeforeTime(now)
                .issueTime(now)
                .expirationTime(new Date(now.getTime() +
                        1000 * 86400 * requestedExpiration))
                //Token is usable for user request days
                .jwtID(UUID.randomUUID().toString())
                .build();

        EncryptedJWT jwt = new EncryptedJWT(header, jwtClaims);

        RSAEncrypter encrypter = new RSAEncrypter(publicKey);

        try {
            jwt.encrypt(encrypter);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        //return serialized jwt Token
        return jwt.serialize();
    }

    public String issueRefresh(String authJWT) {

        Date now = new Date();
        String userUID = getClaimOfToken(authJWT, "subject").toString();

        //Create JWT claims
        JWTClaimsSet jwtClaims = new JWTClaimsSet.Builder()
                .issuer(jwtissuer)
                .subject(userUID)
                .audience(jwtaudience)
                .notBeforeTime(now)
                .issueTime(now)
                .expirationTime(new Date(now.getTime() +
                        1000 * 86400))
                //Token is usable for user default refresh token life is 1 day
                .jwtID(UUID.randomUUID().toString())
                .build();

        EncryptedJWT jwt = new EncryptedJWT(header, jwtClaims);

        RSAEncrypter encrypter = new RSAEncrypter(publicKey);

        try {
            jwt.encrypt(encrypter);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        //return serialized jwt Token
        return jwt.serialize();
    }

    public String refreshByToken(String refreshToken) {
        try {
            String userUID = getClaimOfToken(refreshToken, "subject").toString();
            return issueJwt(userUID, false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String issueJwt(String userUID, Boolean is2ndAuthed) {

        Date now = new Date();

        //Create JWT claims
        JWTClaimsSet jwtClaims = new JWTClaimsSet.Builder()
                .issuer(jwtissuer)
                .subject(userUID)
                .audience(jwtaudience)
                .notBeforeTime(now)
                .issueTime(now)
                .expirationTime(new Date(now.getTime() + 1000 * 1800))
                //Token is usable for 30 minutes
                .jwtID(UUID.randomUUID().toString())
                .claim("2nd-authed", is2ndAuthed)
                .build();

        EncryptedJWT jwt = new EncryptedJWT(header, jwtClaims);

        RSAEncrypter encrypter = new RSAEncrypter(publicKey);

        try {
            jwt.encrypt(encrypter);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }

        //return serialized jwt Token
        return jwt.serialize();
    }

    // parse serialized token value to token object
    private EncryptedJWT parseToken(String serializedJWT) {
        EncryptedJWT candidateToken = null;
        try {
            candidateToken = EncryptedJWT.parse(serializedJWT);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return candidateToken;
    }

    // decrypt token
    private EncryptedJWT decryptToken(EncryptedJWT token) {

        RSADecrypter decrypter = new RSADecrypter(privateKey);

        try {
            token.decrypt(decrypter);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        return token;
    }

    private boolean validateToken(JWTClaimsSet claims) {
        boolean vaildation = false;
        Date a = tokenService.findTokenExp(claims.getSubject());
        if (a == null) {
            String issuer = claims.getIssuer();
            Date expire = claims.getExpirationTime();
            Date now = new Date();
            List<String> audience = claims.getAudience();
            if (issuer == jwtissuer && audience.equals(jwtaudience) && expire.before(now)) {
                vaildation = true;
            }
            return vaildation;
        } else {
            return vaildation;
        }
    }

    //get values of token
    public Object getClaimOfToken(String recievedToken, String typeOfClaim) {
        try {
            EncryptedJWT targetToken = decryptToken(parseToken(recievedToken));
            JWTClaimsSet claimsSet = targetToken.getJWTClaimsSet();
            if (validateToken(claimsSet)) {
                return claimsSet.getClaim(typeOfClaim);
            } else {
                throw new RuntimeException("token expired");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 4. 액세스 토큰 생성
     *
     * @param authentication 사용자 정보
     * @return 클레임 정보와 사용자 정보를 기반으로 jwt 토큰 생성
     * 클레임 정보, 사용자 ID, 생성 시간, 만료 시간 등을 설정하고, 서명 알고리즘과 서명 키를 사용하여 토큰을 생성합니다.
     * Access Token 역활
     */
    public String generateAccessToken(Authentication authentication) {
        Date now = new Date();

        //Create JWT claims
        JWTClaimsSet jwtClaims = new JWTClaimsSet.Builder()
                .issuer(jwtissuer)
                .subject(authentication.getName())
                .audience(jwtaudience)
                .notBeforeTime(now)
                .issueTime(now)
                .expirationTime(new Date(now.getTime() + 1000 * 1800))
                //Token is usable for 30 minutes
                .jwtID(UUID.randomUUID().toString())
                .claim("2nd-authed", false)
                .build();

        EncryptedJWT jwt = new EncryptedJWT(header, jwtClaims);

        RSAEncrypter encrypter = new RSAEncrypter(publicKey);

        try {
            jwt.encrypt(encrypter);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }

        //return serialized jwt Token
        return jwt.serialize();
    }
}
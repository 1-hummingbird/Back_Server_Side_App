package com.hummingbird.kr.starbuckslike.auth.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
@Service
public class JwtTokenProvider {

    private final Environment env;

    /**
     * TokenProvider
     * 1. 토큰에서 uuid 가져오기
     * 2. Claims에서 원하는 claim 값 추출 ( - JWT version 업데이트로 사용하지 않음)
     * 3. 토큰에서 모든 claims 추출 ( - JWT version 업데이트로 사용하지 않음)
     * 4. 액세스 토큰 생성
     * 5. refresh 토큰 생성
     */

    /**
     * 1. 토큰에서 uuid 가져오기
     *
     * @param token
     * @return jwt토큰에서 추출한 사용자 UUID 반환
     * 토큰에서 추출한 클레임에서 사용자 UUID를 추출하여 반환합니다.
     */
    public String validateAndGetUserUuid(String token) throws BaseException {
        try {
            log.info(extractClaim(token, Claims::getSubject));
            return null;
        } catch (NullPointerException e) {
            log.info("토큰에 담긴 유저 정보가 없습니다");
            throw new BaseException(BaseResponseStatus.WRONG_JWT_TOKEN);
        }
    }

    /**
     * 2. Claims에서 원하는 claim 값 추출
     *
     * @param token
     * @param claimsResolver jwt토큰에서 추출한 정보를 어떻게 처리할지 결정하는 함수
     * @return jwt토큰에서 모든 클레임(클레임은 토큰에 담긴 정보 의미 ) 추출한 다음 claimsResolver함수를 처리해 결과 반환
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        log.info("claims={}", claims);
        return claimsResolver.apply(claims);
    }

    /**
     * 3. 토큰에서 모든 claims 추출
     *
     * @param token 주어진 JWT 토큰에서 모든 클레임을 추출하여 반환합니다.
     *              토큰의 서명을 확인하기 위해 사용할 서명 키(getSigningKey())를 설정하고 토큰을 파싱하여 클레임들을 추출합니다.
     */
    private Claims extractAllClaims(String token) {
        log.info("extractAllClaims token={}", token);
        try {
            return Jwts
                    .parser()
                    .verifyWith((SecretKey) getSignKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            // 이 단계에서 token의 유효성 검사 및 만료일 검사를 실시한다!
        }
        // 파싱 예외 처리
        catch (ExpiredJwtException e) {
            log.error("만료된 토큰입니다");
            throw new BaseException(BaseResponseStatus.WRONG_JWT_TOKEN);
        } catch (UnsupportedJwtException e) {
            log.error("지원되지 않는 유형의 토큰입니다");
            throw new BaseException(BaseResponseStatus.WRONG_JWT_TOKEN);
        } catch (MalformedJwtException | IllegalArgumentException e) {
            log.error("잘못된 토큰입니다");
            throw new BaseException(BaseResponseStatus.WRONG_JWT_TOKEN);
        } catch (io.jsonwebtoken.security.SignatureException e) {
            log.error("SecretKey가 일치하지 않습니다");
            throw new BaseException(BaseResponseStatus.WRONG_JWT_TOKEN);
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
        Claims claims = Jwts.claims().subject(authentication.getName()).build();
        Date now = new Date();
        Date expiration = new Date(now.getTime() + env.getProperty("JWT.token.access-expire-time", Long.class).longValue());

        return Jwts.builder()
                .signWith(getSignKey())
                .claim("uuid", claims.getSubject())
                .issuedAt(expiration)
                .compact();
    }

    public Key getSignKey() {
        return Keys.hmacShaKeyFor( env.getProperty("JWT.secret-key").getBytes() );
    }

}
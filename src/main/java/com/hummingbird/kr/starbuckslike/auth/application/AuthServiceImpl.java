package com.hummingbird.kr.starbuckslike.auth.application;

import com.hummingbird.kr.starbuckslike.auth.domain.AuthUserDetail;
import com.hummingbird.kr.starbuckslike.auth.dto.in.*;
import com.hummingbird.kr.starbuckslike.auth.dto.out.*;
import com.hummingbird.kr.starbuckslike.auth.infrastructure.AuthRepository;
import com.hummingbird.kr.starbuckslike.auth.util.JwtTokenProvider;
import com.hummingbird.kr.starbuckslike.common.Exception.BaseException;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponseStatus;

import com.hummingbird.kr.starbuckslike.member.domain.Member;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.core.env.Environment;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService{

    private final AuthRepository authRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate<String, Date> redisTemplate;
    private Environment env;


    @Autowired
    public AuthServiceImpl(AuthRepository authRepository, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, RedisTemplate<String, Date> redisTemplate) {
        this.authRepository = authRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() {
        log.info("AuthserviceImpl init");
    }

    /**
     * AuthServiceImpl
     * 1. 회원가입
     * 2. 로그인
     * 3. 로그아웃
     */

    @Override
    @Transactional
    public void register(RegisterRequestDTO registerRequestDTO) {

        log.info("registerRequestDTO : {}", registerRequestDTO);

        try {
            authRepository.save(registerRequestDTO.toEntity(passwordEncoder));
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_USER);
        }

    }

    @Override
    @Transactional
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        log.info("signInRequestDto : {}", loginRequestDTO);
        log.info(loginRequestDTO.getLoginID());
        Member member;
        member = authRepository.findByLoginID(loginRequestDTO.getLoginID()).orElseThrow(
                () -> new BaseException(BaseResponseStatus.FAILED_TO_LOGIN)
        );

        try
        {
            Authentication authentication = authenticate(member, loginRequestDTO.getPassword());
            String token = createToken(authentication);
            return new LoginResponseDTO(token,member.getName(),member.getMemberUID());

        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    @Transactional
    public void logout(LogoutRequestDTO logoutRequestDTO) {
        log.info("logoutRequestDTO : {}", logoutRequestDTO);
        Long accessExpireTime = Optional.ofNullable(env.getProperty("JWT.token.access-expire-time", Long.class)).orElse(0L);
        Date expires = new Date(accessExpireTime + System.currentTimeMillis());
        saveToken(logoutRequestDTO.getAccessToken(), expires);
        saveToken(logoutRequestDTO.getRefreshToken(), expires);
    }

    @Override
    @Transactional
    public void resetPW(ResetPWRequestDTO resetPWRequestDTO) {
        log.info("resetPWRequestDTO : {}", resetPWRequestDTO);
        try {
            authRepository.updatePasswordByLoginID(resetPWRequestDTO.getLoginID(), passwordEncoder.encode(resetPWRequestDTO.getPassword()));
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.NO_EXIST_USER);
        }
    }

    @Override
    @Transactional
    public FindIDResponseDTO findID(FindIDRequestDTO findIDRequestDTO) {
        log.info("findIDRequestDTO : {}", findIDRequestDTO);
        try {
            return new FindIDResponseDTO(authRepository.findByEmail(findIDRequestDTO.getEmail()).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER)).getLoginID());
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.NO_EXIST_USER);
        }
    }

    @Override
    public void sendPhoneVerificationCode(PhoneVerificationRequestDTO phoneVerificationRequestDTO) {

    }

    @Override
    public void checkPhoneVerificationCode(PhoneVerificationCheckRequestDTO phoneVerificationCheckRequestDTO) {

    }

    @Override
    public void sendEmailVerificationCode(EmailVerificationRequestDTO emailVerificationRequestDTO) {

    }

    @Override
    public void checkEmailVerificationCode(EmailVerificationCheckRequestDTO emailVerificationCheckRequestDTO) {

    }

    @Override
    public void oauthRegister(OauthRegisterRequestDTO oauthRegisterRequestDTO) {

    }

    @Override
    public LoginResponseDTO oauthLogin(OauthLoginRequestDTO oauthLoginRequestDTO) {
        return null;
    }

    @Override
    public CheckEmailResponseDTO checkEmail(CheckEmailRequestDTO checkEmailRequestDTO) {
        return null;
    }

    @Override
    public CheckPhoneResponseDTO checkPhone(CheckPhoneRequestDTO checkPhoneRequestDTO) {
        return null;
    }

    @Override
    public CheckLoginIDResponseDTO checkLoginID(CheckLoginIDRequestDTO checkLoginIDRequestDTO) {
        return null;
    }

    @Override
    public void withdraw(WithdrawRequestDTO withdrawRequestDTO) {
        
    }

    private String createToken(Authentication authentication) {
        return jwtTokenProvider.generateAccessToken(authentication);
    }

    private Authentication authenticate(Member member, String inputPassword) {
        AuthUserDetail authUserDetail = new AuthUserDetail(member);
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authUserDetail.getUsername(),
                        inputPassword
                )
        );
    }


    //for logout Request, this is black-list of Refresh, so TTL is same as expires
    public void saveToken(String tokenUID, Date expires) {
        long ttl = (expires.getTime() - System.currentTimeMillis()) / 1000;
        redisTemplate.opsForValue().set(tokenUID, expires, ttl, TimeUnit.SECONDS);
    }

    public Date findTokenExp(String tokenUID) {
        return redisTemplate.opsForValue().get(tokenUID);
    }
}
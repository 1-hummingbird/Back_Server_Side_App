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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService{

    private final AuthRepository authRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(AuthRepository authRepository, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
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
}
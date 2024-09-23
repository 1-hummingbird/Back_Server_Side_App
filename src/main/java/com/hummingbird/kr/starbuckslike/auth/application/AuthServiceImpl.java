package com.hummingbird.kr.starbuckslike.auth.application;

import com.hummingbird.kr.starbuckslike.auth.domain.*;
import com.hummingbird.kr.starbuckslike.auth.dto.in.*;
import com.hummingbird.kr.starbuckslike.auth.dto.out.*;
import com.hummingbird.kr.starbuckslike.auth.infrastructure.AuthRepository;
import com.hummingbird.kr.starbuckslike.auth.infrastructure.OauthInfoRepository;
import com.hummingbird.kr.starbuckslike.auth.util.JwtTokenProvider;
import com.hummingbird.kr.starbuckslike.common.Exception.BaseException;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponseStatus;

import com.hummingbird.kr.starbuckslike.member.domain.Member;
import com.hummingbird.kr.starbuckslike.redis.service.RedisService;
import com.hummingbird.kr.starbuckslike.mail.application.MailService;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.core.env.Environment;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService{

    private final AuthRepository authRepository;
    private final OauthInfoRepository oauthInfoRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;
    private final Environment env;
    private final MailService mailService;


    private final long AUTH_CHALLENGE_EXPIRE_TIME = 600000; // 10 minutes


    @Autowired
    public AuthServiceImpl(AuthRepository authRepository, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, RedisService redisService,
                             Environment env, OauthInfoRepository oauthInfoRepository, MailService mailService) {
        this.authRepository = authRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.redisService = redisService;
        this.mailService = mailService;
        this.env = env;
        this.oauthInfoRepository = oauthInfoRepository;
    }

    @PostConstruct
    public void init() {
        log.info("AuthserviceImpl init");
    }

    @Override
    @Transactional
    public void register(RegisterRequestDTO registerRequestDTO) {

        log.info("registerRequestDTO : {}", registerRequestDTO);
        String phone = registerRequestDTO.getPhone();
        String email = registerRequestDTO.getEmail();
        //if (redisService.getAuthChallenge(phone).equals("Success") &&
        //        redisService.getAuthChallenge(email).equals("Success")){
        if (redisService.getAuthChallenge(email).equals("Success")){
            try {
                authRepository.save(registerRequestDTO.toEntity(passwordEncoder));
           } catch (Exception e) {
               throw new BaseException(BaseResponseStatus.DUPLICATED_USER);
            }
        }
        else {throw new BaseException(BaseResponseStatus.DISALLOWED_ACTION);}

    }

    @Override
    @Transactional
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
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
        try{
            long accessExpireTime = Optional.ofNullable(env.getProperty("JWT.token.access-expire-time", Long.class)).orElse(3600L)*1000;
            Date expires = new Date(accessExpireTime + System.currentTimeMillis());
            redisService.recordToken(logoutRequestDTO.getAccessToken(), expires);}
        catch (Exception e) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    @Transactional
    public void resetPW(ResetPWRequestDTO resetPWRequestDTO) {
        log.info("resetPWRequestDTO : {}", resetPWRequestDTO);
        if (redisService.getAuthChallenge(resetPWRequestDTO.getEmail()).equals("Success")){
        try {
            authRepository.updatePasswordByLoginID(resetPWRequestDTO.getLoginID(), passwordEncoder.encode(resetPWRequestDTO.getPassword()));
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.NO_EXIST_USER);
        }}
        else {throw new BaseException(BaseResponseStatus.DISALLOWED_ACTION);}
    }

    @Override
    @Transactional
    public FindIDResponseDTO findID(FindIDRequestDTO findIDRequestDTO) {
        log.info("findIDRequestDTO : {}", findIDRequestDTO);
        if (redisService.getAuthChallenge(findIDRequestDTO.getEmail()).equals("Success")){
        try {
            return new FindIDResponseDTO(authRepository.findByEmail(findIDRequestDTO.getEmail()).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER)).getLoginID());
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.NO_EXIST_USER);
        }}else {throw new BaseException(BaseResponseStatus.DISALLOWED_ACTION);}
    }

    @Override
    public void sendPhoneVerificationCode(PhoneVerificationRequestDTO phoneVerificationRequestDTO) {
        String code = String.format("%06d", new Random().nextInt(1000000));
    }

    @Override
    public void checkPhoneVerificationCode(PhoneVerificationCheckRequestDTO phoneVerificationCheckRequestDTO) {

    }

    @Override
    public void sendEmailVerificationCode(EmailVerificationRequestDTO emailVerificationRequestDTO) {
        String email = emailVerificationRequestDTO.getEmail();
        Date expires = new Date(AUTH_CHALLENGE_EXPIRE_TIME + System.currentTimeMillis());
        String code = String.format("%06d", new Random().nextInt(1000000));
        try{mailService.sendMail(email, "Email Verification Code", "Your verification code is " + code);
            redisService.recordAuthChallenge(email, code, expires);}
        catch (Exception e) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void checkEmailVerificationCode(EmailVerificationCheckRequestDTO emailVerificationCheckRequestDTO) {
        String email = emailVerificationCheckRequestDTO.getEmail();
        String code = emailVerificationCheckRequestDTO.getVerificationCode();
        Date expires = new Date(AUTH_CHALLENGE_EXPIRE_TIME + System.currentTimeMillis());
        if (redisService.getAuthChallenge(email).equals(code)){
            redisService.recordAuthChallengeSuccess(email, expires);
        }
        else{throw new BaseException(BaseResponseStatus.DISALLOWED_ACTION);}
    }

    // todo: oauth register and login need to check kakao token vaildation and register need to prevent duplicated user
    @Override
    public void oauthRegister(OauthRegisterRequestDTO oauthRegisterRequestDTO) {
        log.info("oauthRegisterRequestDTO : {}", oauthRegisterRequestDTO);
        if (OauthTokenCheck(oauthRegisterRequestDTO.getOauthToken())){
            try {
                OauthInfo oauthInfo = oauthRegisterRequestDTO.toEntity();
                oauthInfoRepository.save(oauthInfo);
            } catch (Exception e) {
                throw new BaseException(BaseResponseStatus.DUPLICATED_USER);
            }
        }
        else{throw new BaseException(BaseResponseStatus.TOKEN_NOT_VALID);}

    }

    @Override
    public LoginResponseDTO oauthLogin(OauthLoginRequestDTO oauthLoginRequestDTO) {
        OauthInfo oauthInfo = oauthInfoRepository.findByOauthIDAndOauthType(oauthLoginRequestDTO.getOauthID(), oauthLoginRequestDTO.getOauthType()).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_USER)
        );
        Member member = authRepository.findByMemberUID(oauthInfo.getMemberUID()).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_USER)
        );
        if (OauthTokenCheck(oauthLoginRequestDTO.getOauthToken())){
            String token = createToken(oAuthAuthenticate(member.getMemberUID()));
            return new LoginResponseDTO(token,member.getName(),member.getMemberUID());}
        else{throw new BaseException(BaseResponseStatus.TOKEN_NOT_VALID);}
    }

    @Override
    public CheckEmailResponseDTO checkEmail(CheckEmailRequestDTO checkEmailRequestDTO) {
        Optional<Member> member = authRepository.findByEmail(checkEmailRequestDTO.getEmail());
        if (member.isPresent()) {
            return new CheckEmailResponseDTO(false);
        } else {
            return new CheckEmailResponseDTO(true);
        }
    }

    @Override
    public CheckPhoneResponseDTO checkPhone(CheckPhoneRequestDTO checkPhoneRequestDTO) {
        Optional<Member> member = authRepository.findByPhone(checkPhoneRequestDTO.getPhone());
        if (member.isPresent()) {
            return new CheckPhoneResponseDTO(false);
        } else {
            return new CheckPhoneResponseDTO(true);
        }
    }

    @Override
    public CheckLoginIDResponseDTO checkLoginID(CheckLoginIDRequestDTO checkLoginIDRequestDTO) {
        Optional<Member> member = authRepository.findByLoginID(checkLoginIDRequestDTO.getLoginID());
        if (member.isPresent()) {
            return new CheckLoginIDResponseDTO(false);
        } else {
            return new CheckLoginIDResponseDTO(true);
        }
    }

    @Override
    public void withdraw(WithdrawRequestDTO withdrawRequestDTO) {
        log.info("withdrawRequestDTO : {}", withdrawRequestDTO);
        try {
            authRepository.disableMember(withdrawRequestDTO.getMemberUID());
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void updatePW(UpdatePWRequestDTO updatePWRequestDTO) {
        log.info("updatePWRequestDTO : {}", updatePWRequestDTO);
        Member userinfo = authRepository.findByMemberUID(updatePWRequestDTO.getMemberUID()).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));
        if (!passwordEncoder.matches(updatePWRequestDTO.getOldPassword(), userinfo.getPassword())) {
            throw new BaseException(BaseResponseStatus.FAILED_TO_LOGIN);
        }
        authRepository.updatePasswordByUuid(updatePWRequestDTO.getMemberUID(), passwordEncoder.encode(updatePWRequestDTO.getNewPassword()));
    }

    @Override
    public OauthInfoResponseDTO getOauthInfo(String memberUID) {
        return new OauthInfoResponseDTO(oauthInfoRepository.findByMemberUID(memberUID));
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

    private Authentication oAuthAuthenticate(String memberUID) {
        log.info("memberUID : {}", memberUID);
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        memberUID,
                        null
                )
        );
    }

    private boolean OauthTokenCheck(String token){

        return true;
    }

}

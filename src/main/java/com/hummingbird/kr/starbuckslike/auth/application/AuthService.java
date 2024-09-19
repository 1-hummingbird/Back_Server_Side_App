package com.hummingbird.kr.starbuckslike.auth.application;

import com.hummingbird.kr.starbuckslike.auth.dto.in.*;
import com.hummingbird.kr.starbuckslike.auth.dto.out.*;


public interface AuthService {

    /**
     * 1. register
     * Save user
     * @param registerRequestDTO
     * return void
     */
    void register(RegisterRequestDTO registerRequestDTO);

    /**
     * 2. login
     * Authenticate user
     * @param loginRequestDTO
     * return SignInResponseDto
     */
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);

    /**
     * 3. logout
     * @param logoutRequestDTO
     * @return void
     */
    void logout(LogoutRequestDTO logoutRequestDTO);

    /**
     * 4. resetPW
     * @param resetPWRequestDTO
     * @return void
     */
    void resetPW(ResetPWRequestDTO resetPWRequestDTO);

    /**
     * 5. findID
     * @param findIDRequestDTO
     * @return findIDResponseDTO
     */
    FindIDResponseDTO findID(FindIDRequestDTO findIDRequestDTO);

    /**
     * 6. sendPhoneVerificationCode
     * @param phoneVerificationRequestDTO
     * @return void
     */
    void sendPhoneVerificationCode(PhoneVerificationRequestDTO phoneVerificationRequestDTO);

    /**
     * 7. checkPhoneVerificationCode
     * @param phoneVerificationCheckRequestDTO
     * @return void
     */
    void checkPhoneVerificationCode(PhoneVerificationCheckRequestDTO phoneVerificationCheckRequestDTO);


    /**
     * 8. sendEmailVerificationCode
     * @param emailVerificationRequestDTO
     * @return void
     */
    void sendEmailVerificationCode(EmailVerificationRequestDTO emailVerificationRequestDTO);
    
    
    /**
     * 9. checkEmailVerificationCode
     * @param emailVerificationCheckRequestDTO
     * @return void
     */
    void checkEmailVerificationCode(EmailVerificationCheckRequestDTO emailVerificationCheckRequestDTO);

    /**
     * 10. oauthRegister
     * @param oauthRegisterRequestDTO
     * @return void
     */
    void oauthRegister(OauthRegisterRequestDTO oauthRegisterRequestDTO);

    /**
     * 11. oauthLogin
     * @param oauthLoginRequestDTO
     * @return LoginResponseDTO
     */
    LoginResponseDTO oauthLogin(OauthLoginRequestDTO oauthLoginRequestDTO);

    /**
     * 12. checkEmail
     * @param checkEmailRequestDTO
     * @return CheckEmailResponseDTO
     */
    CheckEmailResponseDTO checkEmail(CheckEmailRequestDTO checkEmailRequestDTO);

    /**
     * 13. checkPhone
     * @param checkPhoneRequestDTO
     * @return CheckPhoneResponseDTO
     */
    CheckPhoneResponseDTO checkPhone(CheckPhoneRequestDTO checkPhoneRequestDTO);

    /**
     * 14. checkLoginID
     * @param checkLoginIDRequestDTO
     * @return CheckLoginIDResponseDTO
     */
    CheckLoginIDResponseDTO checkLoginID(CheckLoginIDRequestDTO checkLoginIDRequestDTO);

    /*
     * 15. withdraw
     * @param withdrawRequestDTO
     * @return void
     */
    void withdraw(WithdrawRequestDTO withdrawRequestDTO);
}
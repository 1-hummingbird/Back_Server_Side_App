package com.hummingbird.kr.starbuckslike.auth.application;

import com.hummingbird.kr.starbuckslike.auth.dto.in.*;
import com.hummingbird.kr.starbuckslike.auth.dto.out.*;


public interface AuthService {

    /**
     * AuthUserDetail service interface
     * 1. register
     * 2. login
     * 3. logout
     */

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
}
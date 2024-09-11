package com.hummingbird.kr.starbuckslike.auth.application;

import com.hummingbird.kr.starbuckslike.auth.dto.in.*;
import com.hummingbird.kr.starbuckslike.auth.dto.out.LoginResponseDTO;


public interface AuthService {

    /**
     * AuthUserDetail service interface
     * 1. register
     * 2. login
     * 3. logout
     */

    /**
     * 1. Sign up
     * Save user
     * @param registerRequestDTO
     * return void
     */
    void register(RegisterRequestDTO registerRequestDTO);

    /**
     * 2. Sign in
     * Authenticate user
     * @param loginRequestDTO
     * return SignInResponseDto
     */
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);

}
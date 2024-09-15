package com.hummingbird.kr.starbuckslike.auth.presentation;

import com.hummingbird.kr.starbuckslike.auth.application.AuthService;
import com.hummingbird.kr.starbuckslike.auth.dto.in.*;
import com.hummingbird.kr.starbuckslike.auth.dto.out.*;
import com.hummingbird.kr.starbuckslike.auth.vo.in.*;
import com.hummingbird.kr.starbuckslike.auth.vo.out.*;
import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseEntity;
import io.swagger.v3.oas.annotations.Operation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@ComponentScan(basePackages = {"com.hummingbird.kr.starbuckslike.auth"})
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController( AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Member Login API", description = "Member Login API", tags = {"Auth"})
    @PostMapping("/login")
    public CommonResponseEntity<LoginResponseVO> login(@RequestBody LoginRequestVO loginRequestVO) {
        try {
            LoginResponseDTO loginResponseDTO = authService.login((loginRequestVO.toDTO()));
            return new CommonResponseEntity<LoginResponseVO>(HttpStatus.OK, "login success", loginResponseDTO.toVO());
        } catch (Exception e) {
            return new CommonResponseEntity<>(HttpStatus.BAD_REQUEST, "login failure", null);}
    }
    @Operation(summary = "Member Register API", description = "Member Register API", tags = {"Auth"})
    @PostMapping("/register")
    public CommonResponseEntity<Void> register(@RequestBody RegisterRequestVO registerRequestVO) {
        authService.register(registerRequestVO.toDTO());
        return new CommonResponseEntity<>(HttpStatus.OK, "register complete", null);
    }
    @Operation(summary = "PW update API", description = "PW update API", tags = {"Auth"})
    @PostMapping("/resetPW")
    public CommonResponseEntity<ResetPWResponseDTO> resetPW(@RequestBody ResetPWRequestVO resetPWRequestVO) {

        return new CommonResponseEntity<ResetPWResponseDTO>(HttpStatus.OK, "PW update complete", null);
    }

}

package com.hummingbird.kr.starbuckslike.auth.presentation;

import com.hummingbird.kr.starbuckslike.auth.application.AuthService;
import com.hummingbird.kr.starbuckslike.auth.dto.out.*;
import com.hummingbird.kr.starbuckslike.auth.vo.in.*;
import com.hummingbird.kr.starbuckslike.auth.vo.out.*;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponse;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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
    public BaseResponse<LoginResponseVO> login(@RequestBody LoginRequestVO loginRequestVO) {
        try {
            LoginResponseDTO loginResponseDTO = authService.login((loginRequestVO.toDTO()));
            return new BaseResponse<>(loginResponseDTO.toVO());
        } catch (Exception e) {
            return new BaseResponse<>(BaseResponseStatus.FAILED_TO_LOGIN);}
    }
    @Operation(summary = "Member Register API", description = "Member Register API", tags = {"Auth"})
    @PostMapping("/register")
    public BaseResponse<Void> register(@RequestBody RegisterRequestVO registerRequestVO) {
        authService.register(registerRequestVO.toDTO());
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
    @Operation(summary = "PW update API", description = "PW update API", tags = {"Auth"})
    @PostMapping("/resetPW")
    public BaseResponse<Void> resetPW(@RequestBody ResetPWRequestVO resetPWRequestVO) {
        authService.resetPW(resetPWRequestVO.toDTO());
        return new BaseResponse<Void>(BaseResponseStatus.SUCCESS);
    }
    @Operation(summary = "Member Logout API", description = "Member Logout API", tags = {"Auth"})
    @PostMapping("/logout")
    public BaseResponse<Void> logout(@RequestBody LogoutRequestVO logoutRequestVO) {
        authService.logout(logoutRequestVO.toDTO());
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
    @Operation(summary = "Member Find ID API", description = "Member Find ID API", tags = {"Auth"})
    @PostMapping("/findID")
    public BaseResponse<FindIDResponseVO> findID(@RequestBody FindIDRequestVO findIDRequestVO) {
        FindIDResponseDTO responseDTO = authService.findID(findIDRequestVO.toDTO());
        return new BaseResponse<>(responseDTO.toVO());
    }
}

package com.hummingbird.kr.starbuckslike.auth.presentation;

import com.hummingbird.kr.starbuckslike.auth.application.AuthService;

import com.hummingbird.kr.starbuckslike.auth.vo.LoginRequestVO;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Member Login API", description = "Member Login API", tags = {"Auth"})
    @PostMapping("/login")
    public void login(@RequestBody LoginRequestVO loginreqVO) {
        authService.login((loginreqVO.toDTO()));
        HttpStatus responseStatus = HttpStatus.OK;
        //return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), null);
    }

    //@Operation(summary = "Member Register API", description = "Member Register API", tags = {"Auth"})
    //@PostMapping("/register")
    //public void register(@RequestBody SignUpRequestVo signUpRequestVo) {

        //return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), null);
    //}

}

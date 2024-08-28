package com.hummingbird.kr.starbuckslike.auth.presentation;

import com.hummingbird.kr.starbuckslike.auth.application.AuthService;

import com.hummingbird.kr.starbuckslike.auth.vo.LoginRequestVO;
import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseEntity;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Member Login API", description = "Member Login API", tags = {"Auth"})
    @PostMapping("/login")
    public CommonResponseEntity<Void> login(@RequestBody LoginRequestVO loginreqVO) {
        try {
            authService.login((loginreqVO.toDTO()));
            return new CommonResponseEntity<>(HttpStatus.OK, "login success", null);
        } catch (Exception e) {return new CommonResponseEntity<>(HttpStatus.BAD_REQUEST, "login fail", null);}
    }
    @GetMapping("/secret")
    public String getSecretKey() {
        try {return authService.getSecret();}
        catch (Exception e) {throw new RuntimeException(e);}
    }

    //@Operation(summary = "Member Register API", description = "Member Register API", tags = {"Auth"})
    //@PostMapping("/register")
    //public void register(@RequestBody SignUpRequestVo signUpRequestVo) {

        //return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), null);
    //}

}

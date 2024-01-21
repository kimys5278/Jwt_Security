package com.springboot.jwt_securityprac.controller;

import com.springboot.jwt_securityprac.data.dto.SignDto.SigninResultDto;
import com.springboot.jwt_securityprac.data.dto.SignDto.SignupResultDto;
import com.springboot.jwt_securityprac.service.SignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sign-api")
public class SignController {

    private final Logger logger = LoggerFactory.getLogger(SignController.class);

    private final SignService signService;

    @Autowired
    public SignController (SignService signService){
        this.signService = signService;
    }

    @PostMapping(value = "/sign-in")
    public SigninResultDto signIn( @RequestParam String email,  String password)throws  RuntimeException{
        logger.info("[signin] 로그인을 시도하고 있습니다. email : {}, password : *****", email);
        SigninResultDto signinResultDto = signService.signin(email,password);

        if(signinResultDto.getCode() == 0){
            logger.info("[signin] 정상적으로 로그인이 되었습니다. email: {}, token : {}",email,signinResultDto.getToken());
            signinResultDto.getToken();
        }
        return signinResultDto;
    }
    @PostMapping(value = "/sign-up")
    public SignupResultDto signUp(@RequestParam String email, String password, String name, String role){
        logger.info("[signUp] 회원가입을 수행합니다. email : {}, password : ****, name : {}, role : {}", email,
                name, role);
        SignupResultDto signupResultDto = signService.signup(email,password,name,role);

        logger.info("[signUp] 회원가입을 완료했습니다. email : {}", email);
        return signupResultDto;
    }

    @GetMapping(value = "/exception")
    public void exceptionTest()throws  RuntimeException{
        throw new RuntimeException("접근이 금지 되었습니다.");
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(RuntimeException e) {
        HttpHeaders responseHeaders = new HttpHeaders();
        //responseHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        logger.error("ExceptionHandler 호출, {}, {}", e.getCause(), e.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "에러 발생");

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }

}

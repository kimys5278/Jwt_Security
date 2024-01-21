package com.springboot.jwt_securityprac.service.Impl;

import com.springboot.jwt_securityprac.data.dto.CommonResponse;
import com.springboot.jwt_securityprac.data.dto.SignDto.SigninResultDto;
import com.springboot.jwt_securityprac.data.dto.SignDto.SignupResultDto;
import com.springboot.jwt_securityprac.data.entity.User;
import com.springboot.jwt_securityprac.jwt.JwtTokenProvider;
import com.springboot.jwt_securityprac.repository.UserRepository;
import com.springboot.jwt_securityprac.service.SignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SignServiceImpl implements SignService {
    private Logger logger = LoggerFactory.getLogger(SignServiceImpl.class);

    public UserRepository userRepository;
    public JwtTokenProvider jwtTokenProvider;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public SignServiceImpl(UserRepository userRepository , JwtTokenProvider jwtTokenProvider,
                           PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public SignupResultDto signup (String email, String password, String name , String role){
        logger.info("[getSignUpResult] 회원 가입 정보 전달");
        User user;
        if(role.equalsIgnoreCase("admin")){
            user = User.builder()
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .name(name)
                    .roles(Collections.singletonList("ROLE_ADMIN"))
                    .build();
        }else {
            user = User.builder()
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .name(name)
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();
        }

        User savedUser = userRepository.save(user);
        SignupResultDto signupResultDto = new SignupResultDto();
        logger.info("[getSignResultDto] User 정보 들어왔는지 확인 후 결과값 주입");
        if(!savedUser.getName().isEmpty()){
            setSuccessResult(signupResultDto);
        }else{
            setFailResult(signupResultDto);
        }
        return  signupResultDto;

    }
    @Override
    public SigninResultDto signin (String email, String password)throws RuntimeException{
        User user = userRepository.getByEmail(email);
        logger.info("[getSignInResult] 패스워드 비교 수행");
        if(!passwordEncoder.matches(password, user.getPassword())){
           throw new RuntimeException();
        }
        logger.info("[getSignInResult] 패스워드 일치");

        logger.info("[getSignInResult] SignInResultDto 객체 생성");
        SigninResultDto signInResultDto = SigninResultDto.builder()
                .token(jwtTokenProvider.createToken(String.valueOf(user.getEmail()),
                        user.getRoles()))
                .build();

        logger.info("[getSignInResult] SignInResultDto 객체에 값 주입");
        setSuccessResult(signInResultDto);

        return signInResultDto;
      }


    // 결과 모델에 api 요청 성공 데이터를 세팅해주는 메소드
    private void setSuccessResult(SignupResultDto result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    // 결과 모델에 api 요청 실패 데이터를 세팅해주는 메소드
    private void setFailResult(SignupResultDto result) {
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
    }
}



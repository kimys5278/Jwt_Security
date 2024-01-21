package com.springboot.jwt_securityprac.service;

import com.springboot.jwt_securityprac.data.dto.SignDto.SigninResultDto;
import com.springboot.jwt_securityprac.data.dto.SignDto.SignupResultDto;

public interface SignService {
    SignupResultDto signup (String id, String password, String name , String role);
    SigninResultDto signin (String id, String password)throws RuntimeException;

}

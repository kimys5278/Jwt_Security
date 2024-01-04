package com.springboot.jwt_securityprac.jwt;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter {
    private UserDetailsService userDetailsService;

    private final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${springboot.jwt.secret}")
    private String secretKey = "secretKey";

    private final long tokenValidMillisecond = 1000L*60*60;    //1시간 토큰 유효

    @PostConstruct
    protected void init(){
        System.out.println(secretKey);

        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));

    }

}

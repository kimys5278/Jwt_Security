package com.springboot.jwt_securityprac.service;

import com.springboot.jwt_securityprac.repository.UserRepository;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;


    private final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException{logger.info("[loadUserByUsername] loadUserByUsername 수횅 . username: {}",username);
        return userRepository.getByUid(username);
    }

}

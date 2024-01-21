package com.springboot.jwt_securityprac.repository;

import com.springboot.jwt_securityprac.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User getByEmail(String email);
}

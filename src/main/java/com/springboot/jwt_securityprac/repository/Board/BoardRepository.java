package com.springboot.jwt_securityprac.repository.Board;

import com.springboot.jwt_securityprac.data.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long>,BoardRepositoryCustom{

}

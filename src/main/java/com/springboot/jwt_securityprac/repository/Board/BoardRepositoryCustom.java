package com.springboot.jwt_securityprac.repository.Board;

import com.springboot.jwt_securityprac.data.entity.Board;

import java.util.List;

public interface BoardRepositoryCustom {
    //해당 유저가 작성한 게시물 반환
    List<Board> findBoardsByUsername(String name);
}

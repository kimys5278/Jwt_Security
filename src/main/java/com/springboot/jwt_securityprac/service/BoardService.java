package com.springboot.jwt_securityprac.service;

import com.springboot.jwt_securityprac.data.dto.BoardDto.BoardRequestDto;
import com.springboot.jwt_securityprac.data.dto.BoardDto.BoardResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardService {
    BoardResponseDto getContent(Long id);
    BoardResponseDto saveBoard(BoardRequestDto boardRequestDto );
    BoardResponseDto changeBoard(Long id, String title, String content);
    void deleteBoard(Long id);

    List<BoardResponseDto> findBoardsByUsername(String name);

}

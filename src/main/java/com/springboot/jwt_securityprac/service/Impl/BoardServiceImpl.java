package com.springboot.jwt_securityprac.service.Impl;

import com.springboot.jwt_securityprac.data.entity.User;
import com.springboot.jwt_securityprac.service.BoardService;
import com.springboot.jwt_securityprac.data.dto.BoardDto.BoardRequestDto;
import com.springboot.jwt_securityprac.data.dto.BoardDto.BoardResponseDto;
import com.springboot.jwt_securityprac.data.entity.Board;
import com.springboot.jwt_securityprac.jwt.JwtTokenProvider;
import com.springboot.jwt_securityprac.repository.BoardRepository;
import com.springboot.jwt_securityprac.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service

public class BoardServiceImpl implements BoardService {
    private Logger logger = LoggerFactory.getLogger(BoardService.class);

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    private JwtTokenProvider jwtTokenProvider;

    public BoardServiceImpl(BoardRepository boardRepository,JwtTokenProvider jwtTokenProvider
            ,UserRepository userRepository){
        this.boardRepository = boardRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @Override
    public BoardResponseDto getContent(Long id){
        logger.info("[getContent] : 게시글 반환");
        Board board = boardRepository.findById(id).get();
        BoardResponseDto boardResponseDto  = new BoardResponseDto();
        boardResponseDto.setId(board.getId());
        boardResponseDto.setContent(board.getContent());
        boardResponseDto.setTitle(board.getTitle());
        boardResponseDto.setCreateAt(board.getCreateDate());
        boardResponseDto.setUpdateAt(board.getUpdateDate());
        boardResponseDto.setUser(board.getUser());


        return boardResponseDto;

    }
    @Override
    public BoardResponseDto saveBoard(BoardRequestDto boardRequestDto) {
        logger.info("[saveBoard] 게시글 저장 : {}",boardRequestDto.toString());


            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            User user = userRepository.getByEmail(email);

            Board board = new Board();
            board.setTitle(boardRequestDto.getTitle());
            board.setContent(boardRequestDto.getContent());
            board.setCreateDate(LocalDateTime.now());
            board.setUpdateDate(LocalDateTime.now());
            board.setUser(user);




        Board savedBoard = boardRepository.save(board);

            BoardResponseDto boardResponseDto = new BoardResponseDto();
            boardResponseDto.setId(savedBoard.getId());
            boardResponseDto.setTitle(savedBoard.getTitle());
            boardResponseDto.setContent(savedBoard.getContent());
            boardResponseDto.setCreateAt(LocalDateTime.now());
            boardResponseDto.setUpdateAt(LocalDateTime.now());
            boardResponseDto.setUser(savedBoard.getUser());

            return boardResponseDto;

    }

    @Override
    public BoardResponseDto changeBoard(Long id, String title, String content){
        logger.info("[changeBoard] : 게시글 수정");
        Board findBoard = boardRepository.getById(id);
        findBoard.setTitle(title);
        findBoard.setContent(content);
        findBoard.setUpdateDate(LocalDateTime.now());
        Board board = boardRepository.save(findBoard);

        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setId(board.getId());
        boardResponseDto.setTitle(board.getTitle());
        boardResponseDto.setContent(board.getContent());
        boardResponseDto.setUpdateAt(LocalDateTime.now());

        return  boardResponseDto;

    }
    @Override
    public void deleteBoard(Long id){
        logger.info("[deleteBoard] : 게시글 삭제");
       boardRepository.deleteById(id);
    }
}

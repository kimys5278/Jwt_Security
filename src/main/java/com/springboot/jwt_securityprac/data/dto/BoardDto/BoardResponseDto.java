package com.springboot.jwt_securityprac.data.dto.BoardDto;

import com.springboot.jwt_securityprac.data.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private User user;

    @Builder
    public BoardResponseDto(Long id, String title, String content
            , LocalDateTime createAt , LocalDateTime updateAt,User user){
    this.id = id;
    this.title =title;
    this.content = content;
    this.user = user;
    this.createAt = createAt;
    this.updateAt = updateAt;
    }




}

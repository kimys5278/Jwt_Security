package com.springboot.jwt_securityprac.data.dto.BoardDto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequestDto {
    private String title;
    private String content;

}

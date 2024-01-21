package com.springboot.jwt_securityprac.data.dto.SignDto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SigninResultDto extends  SignupResultDto{
    private String token;

    @Builder
    public SigninResultDto(boolean success, int code, String msg, String token) {
        super(success, code, msg);
        this.token = token;
    }
}

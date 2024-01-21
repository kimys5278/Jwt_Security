package com.springboot.jwt_securityprac.data.dto.SignDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignupResultDto {
    private boolean success;

    private int code;

    private String msg;

}

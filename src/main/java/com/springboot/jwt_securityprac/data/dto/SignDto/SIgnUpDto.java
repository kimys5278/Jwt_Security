package com.springboot.jwt_securityprac.data.dto.SignDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SIgnUpDto {
    private String email;
    private String password;
    private String name;
}

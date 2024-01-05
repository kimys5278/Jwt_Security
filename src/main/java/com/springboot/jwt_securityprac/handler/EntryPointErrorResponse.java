package com.springboot.jwt_securityprac.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EntryPointErrorResponse {
    private String msg;
}

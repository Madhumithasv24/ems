package com.expeditors.ems.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginResponse {
    private String isAuth;
    private String token;
    private Integer userId;
    private String role;

    public LoginResponse(String isAuth, String token,String role, Integer userId) {
        this.isAuth = isAuth;
        this.token = token;
        this.role=role;
        this.userId = userId;
    }
}

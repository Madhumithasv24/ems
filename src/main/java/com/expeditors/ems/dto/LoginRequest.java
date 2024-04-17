package com.expeditors.ems.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String userName;
    private String loginPassword;
    private String role;

}

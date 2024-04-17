package com.expeditors.ems.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserCreateRequest {

    private String name;
    private String email;
    private Integer role;
    private LocalDateTime createdAt;
    private String password;
}

package com.expeditors.ems.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Integer Id;
    private String name;
    private String email;
    private String roleName;
}

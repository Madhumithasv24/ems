package com.expeditors.ems.dto;

import com.expeditors.ems.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class TaskCreateUser {
    private String taskName;
    private String taskDesc;

    private Integer createdBy;
}

package com.expeditors.ems.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateCandidate {
    private Integer id;
    private String name;
    private String email;
    private Integer interviewerId;
    private Integer status;
    private LocalDateTime scheduledAt;
}

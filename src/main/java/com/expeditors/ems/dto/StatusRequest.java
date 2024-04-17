package com.expeditors.ems.dto;

import lombok.Data;

@Data
public class StatusRequest {
    private Integer developerId;
    private Integer taskId;
    private String taskStatus;
    private String taskName;
    private String taskDesc;



}

package com.expeditors.ems.dto;

import lombok.Data;

@Data
public class UpdateTask {
    private Integer developerId;
    private Integer taskId;
     private String taskName;
    private String taskDesc;
    private String taskStatus;
}

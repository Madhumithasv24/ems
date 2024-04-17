package com.expeditors.ems.dto;

import lombok.Data;

@Data
public class TaskStatusResponse {
    private Integer task_id;
    private String status;
}

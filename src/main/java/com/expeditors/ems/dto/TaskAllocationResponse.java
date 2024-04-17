package com.expeditors.ems.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TaskAllocationResponse {
    private Integer taskId;
    private Integer developerId;
    private String developerName;
    private Integer assignedBy;
    private String status;
}

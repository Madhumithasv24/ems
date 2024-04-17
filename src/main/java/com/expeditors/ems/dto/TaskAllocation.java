package com.expeditors.ems.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskAllocation {
    private Integer taskId;
    private Integer developerId;
    private Integer assignedBy;
    private String status;
}

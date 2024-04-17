package com.expeditors.ems.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListOfStatus {
    List<TaskStatusResponse>CompletedList;
    List<TaskStatusResponse>IncompletedList;
}

package com.expeditors.ems.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class AccountRequest {
    private Integer expenseId;
    private Integer userId;
    private Integer amount;
    private LocalDate date;
    private String description;
    private Integer statusId;
    private Integer accountedBy;
}

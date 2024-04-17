package com.expeditors.ems.dto;

import lombok.Data;

@Data
public class UpdateAccount {
    private Integer expenseId;
    private Integer statusId;
    private Integer accountedBy;
}

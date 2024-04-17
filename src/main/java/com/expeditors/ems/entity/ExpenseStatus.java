package com.expeditors.ems.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "expense_status")
public class ExpenseStatus {
    @Id
    @Column(name = "id")
    private Integer id;


    @Column (name = "status")
    private String status;
}

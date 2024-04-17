package com.expeditors.ems.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "expense_type")
public class ExpenseType {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private String expenseType;


}

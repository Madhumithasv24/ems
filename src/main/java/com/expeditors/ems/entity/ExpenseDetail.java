package com.expeditors.ems.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "expense_detail")
public class ExpenseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User userId;

    @ManyToOne
    @JoinColumn(name="status_id",referencedColumnName = "id")
    private ExpenseStatus statusId;

    @ManyToOne
    @JoinColumn(name = "type_id",referencedColumnName = "id")
    private ExpenseType typeId;

    @ManyToOne
    @JoinColumn(name = "accounted_by",referencedColumnName = "id")
    private User accountedBy;

    @Column(name = "spent_at")
    private LocalDate spentAt;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "accounted_at")
    private LocalDateTime accountedAt;


}

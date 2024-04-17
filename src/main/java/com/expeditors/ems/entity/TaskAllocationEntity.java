package com.expeditors.ems.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="task_allocation")
public class TaskAllocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private  Long id;

    @ManyToOne
    @JoinColumn(name="task_id",referencedColumnName="id")
    private TaskEntity taskId;

    @ManyToOne
    @JoinColumn(name="developer_id",referencedColumnName = "id")
    private User developerId;

    @ManyToOne
    @JoinColumn(name="assigned_by",referencedColumnName = "id")
    private User assignedBy;

    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;//time

    @Column(name = "status")
    private String status;

}

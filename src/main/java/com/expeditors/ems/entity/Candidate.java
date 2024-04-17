package com.expeditors.ems.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "candidate")
public class Candidate {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name="email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "interviewer_id",referencedColumnName = "id")
    private User interviewerId;

    @ManyToOne
    @JoinColumn(name = "status_id",referencedColumnName = "id")
    private HiringStatus statusId;

    @Column(name = "scheduled_at")
    private LocalDateTime scheduledAt;
}

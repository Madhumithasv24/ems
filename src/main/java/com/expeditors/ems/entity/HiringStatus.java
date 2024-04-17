package com.expeditors.ems.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "hiring_status")
public class HiringStatus {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "status")
    private String status;
}

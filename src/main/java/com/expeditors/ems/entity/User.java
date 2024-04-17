package com.expeditors.ems.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="user_detail")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name="email")
    private String email;

    @ManyToOne
    @JoinColumn(name="role_id",referencedColumnName="id")
    private Role role;//the datatype is Role as we are using as foreign key ,so we are using this is an object

    @Column(name = "created_at")
    private LocalDateTime createdat;

    @Column(name = "password")
    private String password;
}

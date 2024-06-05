package com.example.score.model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "tea")
public class Tea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "sex")
    private String sex;
    @Column(name = "phone")
    private String phone;
    @Column(name = "sub")
    private String sub;
    @Column(name = "salary")
    private String salary;
}

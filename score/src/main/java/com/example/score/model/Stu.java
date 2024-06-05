package com.example.score.model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "Stu")
public class Stu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "stuid")
    private String stuid;
    @Column(name = "type")
    private String type;
    @Column(name = "grade")
    private String grade;
    @Column(name = "phone")
    private String phone;
}

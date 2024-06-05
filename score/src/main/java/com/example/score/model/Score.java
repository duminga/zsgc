package com.example.score.model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "Score")
public class Score {

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
    @Column(name = "sc")
    private String sc;
}

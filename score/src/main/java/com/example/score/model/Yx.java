package com.example.score.model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "yx")
public class Yx {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "num")
    private String num;
    @Column(name = "zn")
    private String zn;
    @Column(name = "addr")
    private String addr;
    @Column(name = "people")
    private String people;
}
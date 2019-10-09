package com.unosquare.cvgenerator.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "hello")
@Data
public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "GREETING")
    private String greeting;

    public Greeting() { }

    public Greeting(int id, String greeting) {
        this.id = id;
        this.greeting = greeting;
    }

}

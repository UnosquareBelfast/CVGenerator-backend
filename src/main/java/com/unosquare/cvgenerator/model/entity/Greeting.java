package com.unosquare.cvgenerator.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "hello")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "GREETING")
    private String greeting;

}

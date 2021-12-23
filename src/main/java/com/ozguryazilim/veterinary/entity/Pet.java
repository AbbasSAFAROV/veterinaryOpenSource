package com.ozguryazilim.veterinary.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Integer age;
    private String type;
    private String genus;

    @ManyToOne(fetch = FetchType.LAZY)
    private Owner owner;

}

package com.ozguryazilim.veterinary.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String age;
    private String type;
    private String genus;

    @ManyToOne(fetch = FetchType.LAZY)
    private Owner owner;

    public Pet(String name, String description, String age, String type, String genus,Owner owner) {
        this.name = name;
        this.description = description;
        this.age = age;
        this.type = type;
        this.genus = genus;
        this.owner = owner;
    }
}

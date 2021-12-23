package com.ozguryazilim.veterinary.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // long 0,1,2,3 ; id = 0; Long id=null;
    private String nameSurname;
    private String contact;
    private String phoneNumber;
    private String email;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Pet> pets;
}

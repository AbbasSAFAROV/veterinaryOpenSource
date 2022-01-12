package com.ozguryazilim.veterinary.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // long 0,1,2,3 ; id = 0; Long id=null;
    private String nameSurname;
    private String contact;
    private String phoneNumber;
    @Builder.Default
    private UserRole userRole=UserRole.USER_ROLE;
    private String email;
    private String username;
    private String password;

    @OneToMany(mappedBy = "owner" , cascade = CascadeType.ALL)
    private List<Pet> pets;

    public Owner(String nameSurname, String contact, String phoneNumber, String email, String username, String password) {
        this.nameSurname = nameSurname;
        this.contact = contact;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.username = username;
        this.password = password;
    }

}


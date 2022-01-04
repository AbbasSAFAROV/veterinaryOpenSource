package com.ozguryazilim.veterinary.model;

import com.ozguryazilim.veterinary.entity.Pet;
import com.ozguryazilim.veterinary.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto {

    private Long id;
    private String nameSurname;
    private String contact;
    private String phoneNumber;
    private UserRole userRole;
    private String username;
    private String email;
    private String password;
    private List<Pet> pets;
}

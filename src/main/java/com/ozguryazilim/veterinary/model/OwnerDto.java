package com.ozguryazilim.veterinary.model;

import com.ozguryazilim.veterinary.entity.Pet;
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
    private String email;
    private String password;
    private List<Pet> pets;
}

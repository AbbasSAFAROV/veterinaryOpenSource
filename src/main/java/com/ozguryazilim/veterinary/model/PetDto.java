package com.ozguryazilim.veterinary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDto {

    private Long id;
    private String name;
    private String description;
    private Integer age;
    private String type;
    private String genus;

}

package com.ozguryazilim.veterinary.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetCreateRequest {

    private String name;
    private String description;
    private Integer age;
    private String type;
    private String genus;

}

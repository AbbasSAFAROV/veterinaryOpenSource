package com.ozguryazilim.veterinary.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerCreateRequest {

    private String nameSurname;
    private String contact;
    private String phoneNumber;
    private String email;
    private String password;

}

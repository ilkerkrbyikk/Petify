package com.Ilker.Petify.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterCorporateCustomerDto {

    private String email;
    private String password;
    private String taxNumber;
    private String address;


}
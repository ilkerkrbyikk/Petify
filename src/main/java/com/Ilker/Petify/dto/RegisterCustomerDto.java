package com.Ilker.Petify.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterCustomerDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String tckNo;
    private String phoneNumber;
    private int age;


}

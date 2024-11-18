package com.Ilker.Petify.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterPetSitterDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private String tckNo;
    private String phoneNumber;
    private boolean available;


    private Long cityId;


}


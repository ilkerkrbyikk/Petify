package com.Ilker.Petify.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

    @Email(message = "Please enter a valid email address.")
    private String email;
    @NotBlank(message = "Password is required.")
    private String password;
}

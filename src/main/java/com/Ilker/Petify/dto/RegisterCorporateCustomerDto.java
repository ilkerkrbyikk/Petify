package com.Ilker.Petify.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import javax.crypto.Mac;

@Getter
@Setter
public class RegisterCorporateCustomerDto {

    //TODO: STRING OLMAYAN NOTBLANKLERIN HEPSİNİ NOTNULL VE NOTEMPTY YAP.


    @Email(message = "Please enter a valid e-mail.")
    @NotBlank(message = "Email is required.")
    private String email;

    @NotBlank(message = "Password is required.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "The password must be at least 8 characters long " +
                    "and contain at least one uppercase letter, one lowercase letter," +
                    " one digit, and one special character.")
    private String password;

    @NotBlank(message = "Tax number is required.")
    @Size(min = 10,max = 10,message = "Tax number must be exactly 10 digits.")
    private String taxNumber;

    @NotBlank(message = "Address is required.")
    @Size(max = 65, message = "Address must be maximum 65 characters.")
    private String address;


}
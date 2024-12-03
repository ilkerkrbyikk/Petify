package com.Ilker.Petify.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterPetSitterDto {

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

    @NotBlank(message = "Name field can not be null.")
    @Size(min = 2, max = 15, message = "Name field can be 3-15 characters.")
    private String firstName;

    @NotBlank(message = "Lastname field can not be null.")
    @Size(min = 2, max = 15, message = "Lastname field can be 3-15 characters.")
    private String lastName;

    @NotNull(message = "Age field can not be null.")
    @Min(value = 18, message = "Under age people can not be pet sitter.")
    private int age;

    @NotBlank(message = "TCK no can not be null")
    @Size(min = 11, max = 11, message = "National number must be exactly 11 digits.")
    private String tckNo;


    @NotBlank(message = "Phone number can not be null and it must start with +905...")
    @Size(min = 12, max = 12, message = "Phone number must start with (+905..) and it must be exactly 12 digits.")
    private String phoneNumber;

    @NotNull(message = "Availability can not be null.")
    private boolean available;


    private Long cityId;


}


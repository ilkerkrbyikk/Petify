package com.Ilker.Petify.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterAdminDto {
    //TODO: STRING OLMAYAN NOTBLANKLERIN HEPSİNİ NOTNULL VE NOTEMPTY YAP.


    @Email(message = "Please enter a valid email address.")
    private String email;
    @NotBlank(message = "Password is required.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "The password must be at least 8 characters long " +
                    "and contain at least one uppercase letter, one lowercase letter," +
                    " one digit, and one special character.")
    private String password;

    @NotBlank(message = "Name is required.")
    private String name;

}


package com.Ilker.Petify.request.gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddGenderRequest {

    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 20, message = "Name must be 2-20 characters.")
    private String name;
}


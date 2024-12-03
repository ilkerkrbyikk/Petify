package com.Ilker.Petify.request.breed;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBreedRequest {

    @NotBlank(message = "Name is required.")
    @Size(min = 2, max = 20, message = "Name must be 2-20 characters.")
    private String name;
}


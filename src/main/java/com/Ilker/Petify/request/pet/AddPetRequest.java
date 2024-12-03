package com.Ilker.Petify.request.pet;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPetRequest {


    @NotNull(message = "Name can not be null.")
    @Size(min = 2, max = 20, message = "Name must be 2-15 characters.")
    private String name;

    @NotNull(message = "Weight can not be null.")
    private int weight;

    @NotNull(message = "Age can not be null.")
    private int age;

    @NotNull(message = "You must describe your pet.")
    @Size(max = 500, message = "Description can be maximum 500 characters.")
    private String description;


    @NotNull(message = "Please set the pet's gender.")
    private Long genderId;

    @NotNull(message = "Please set the pet's breed.")
    private Long breedId;

    @NotNull(message = "Please set the pet's location.")
    private Long cityId;
}


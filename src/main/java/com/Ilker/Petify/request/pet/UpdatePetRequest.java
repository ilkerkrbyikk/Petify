package com.Ilker.Petify.request.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePetRequest {

    private String name;
    private int weight;
    private int age;
    private String description;

    private Long genderId;

    private Long breedId;

    private Long cityId;

}
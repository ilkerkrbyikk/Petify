package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.Pet;
import com.Ilker.Petify.request.pet.AddPetRequest;
import com.Ilker.Petify.request.pet.UpdatePetRequest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PetService {

    List<Pet> getAll();
    Pet add(AddPetRequest request) throws IOException;
    Pet update(UpdatePetRequest request, Long id);
    void delete(Long id);

    Optional<List<Pet>> getByGender(Long id);
    Optional<List<Pet>> getByBreed(Long id);
    Optional<List<Pet>> getByCity(Long id);
}

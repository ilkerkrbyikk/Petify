package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.Breed;
import com.Ilker.Petify.request.breed.AddBreedRequest;
import com.Ilker.Petify.request.breed.UpdateBreedRequest;

import java.util.List;

public interface BreedService {

    List<Breed> getAll();
    Breed add(AddBreedRequest request);
    Breed update(UpdateBreedRequest request, Long id);
    void delete(Long id);

}

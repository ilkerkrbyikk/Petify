package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.PetSitter;

import java.util.List;
import java.util.Optional;

public interface PetSitterService {

    List<PetSitter> getAll();
    Optional<List<PetSitter>> getByCity(Long cityId);
}

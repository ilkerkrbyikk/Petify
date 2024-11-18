package com.Ilker.Petify.repository;

import com.Ilker.Petify.entity.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedRepository extends JpaRepository<Breed,Long> {
    Breed getBreedById(Long id);

    Breed getBreedByName(String name);
}

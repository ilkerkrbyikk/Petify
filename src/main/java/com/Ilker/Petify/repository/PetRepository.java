package com.Ilker.Petify.repository;

import com.Ilker.Petify.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet,Long> {
    Pet getPetById(Long id);

    List<Pet> findByGenderId(Long id);

    List<Pet> findByCityId(Long id);

    List<Pet> findByBreedId(Long id);

    Optional<Pet> findPetById(Long id);
}

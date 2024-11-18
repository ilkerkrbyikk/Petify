package com.Ilker.Petify.repository;

import com.Ilker.Petify.entity.City;
import com.Ilker.Petify.entity.PetSitter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetSitterRepository extends JpaRepository<PetSitter, Long> {
    Optional<PetSitter> getPetSitterByEmail(String email);

    PetSitter findPetSitterByEmail(String email);

    List<PetSitter> findByCity(City city);


}

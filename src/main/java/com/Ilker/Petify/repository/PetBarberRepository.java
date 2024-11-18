package com.Ilker.Petify.repository;

import com.Ilker.Petify.entity.PetBarber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetBarberRepository extends JpaRepository<PetBarber,Long> {
    List<PetBarber> findByCityId(Long cityId);
}

